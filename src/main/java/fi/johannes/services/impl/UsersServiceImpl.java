package fi.johannes.services.impl;

import fi.johannes.models.User;
import fi.johannes.models.Role;
import fi.johannes.services.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * johanness on 03/03/2017.
 */

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {

        //Try to find user and its roles, for example here we try to get it from database via a DAO object
        //Do not confuse this foo.bar.User with CurrentUser or spring User, this is a temporary object which holds user info stored in database
        User user = userDao.findByLogin(login);

        //Build user Authority. some how a convert from your custom roles which are in database to spring GrantedAuthority
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());

        //The magic is happen in this private method !
        return buildUserForAuthentication(user, authorities);

    }

    private User buildUserForAuthentication(User user,
                                            List<GrantedAuthority> authorities) {
        String username = user.getUsername();
        String password = user.getPassword();
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new User(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for (Role userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<>(setAuths);
    }

    public User findByUsername(String name) {
        return null;
    }
}

