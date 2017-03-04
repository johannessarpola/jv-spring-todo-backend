package fi.johannes.services.impl;

import fi.johannes.models.User;
import fi.johannes.models.UserDetails;
import fi.johannes.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * johanness on 04/03/2017.
 */
@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {
    private final UsersService userService;


    @Autowired
    public AppUserDetailsServiceImpl(UsersService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
        return new UserDetails(user);
    }

    
}

