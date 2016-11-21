package fi.johannes.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // FIXME Figure out way to do CSFR
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/")
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/logout");
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.ldapAuthentication()
				.userDnPatterns("uid={0},ou=people")
				.groupSearchBase("ou=groups")
					.contextSource()
						.ldif("classpath:test-server.ldif");
		auth
			.ldapAuthentication()
				//.userDnPatterns("uid={0},ou=people")
				//.groupSearchBase("ou=groups")
				.userSearchFilter("uid={0}")
				//.userSearchBase("ou=scientists")
				
				// pulls from resources test ldif file
				.contextSource()
					//.ldif("classpath:test-server.ldif")
					.url("ldap://ldap.forumsys.com:389/dc=example,dc=com")
						.managerDn("cn=read-only-admin,dc=example,dc=com")
						.managerPassword("password");
			
	}
}