package fi.johannes.services.impl;

import fi.johannes.models.User;
import fi.johannes.models.CustomUserDetails;
import fi.johannes.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * johanness on 04/03/2017.
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;


    @Autowired
    public CustomUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", email)));
        return new CustomUserDetails(user);
    }


}

