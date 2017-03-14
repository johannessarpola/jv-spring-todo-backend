package fi.johannes.conf;

import fi.johannes.models.CustomUserDetails;
import fi.johannes.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final UserDetailsService userDetailsService;

    @Autowired
    private Environment env;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("login")
                .permitAll()
                .defaultSuccessUrl("/")

                .and()
                .logout()
                .deleteCookies("remember-me")
                .logoutSuccessUrl("/")
                .permitAll()

                .and()
                .rememberMe();

        http
                .httpBasic();

        http
                .csrf()
                .disable(); // FIXME
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*auth
                .ldapAuthentication()
                .userDetailsContextMapper(userDetailsContextMapper())
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource()
                .ldif("classpath:test-server.ldif");
        auth
                .ldapAuthentication()
                .userDetailsContextMapper(userDetailsContextMapper())
                .userSearchFilter("uid={0}")
                .contextSource()
                .url("ldap://ldap.forumsys.com:389/dc=example,dc=com")
                .managerDn("cn=read-only-admin,dc=example,dc=com")
                .managerPassword("password");*/
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());


    }

    @Bean
    public UserDetailsContextMapper userDetailsContextMapper() {
        // TODO This is not working so find a way to make it happen maybe
        // http://stackoverflow.com/questions/35481427/ldap-authentication-with-roles-from-database-in-spring-boot-jhipster
        return new LdapUserDetailsMapper() {
            @Override
            public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
                UserDetails details = super.mapUserFromContext(ctx, username, authorities);
                return CustomUserDetails.buildWithLdap((LdapUserDetails) details);
            }
        };
    }
}