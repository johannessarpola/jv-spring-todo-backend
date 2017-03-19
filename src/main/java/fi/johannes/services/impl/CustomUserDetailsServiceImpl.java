package fi.johannes.services.impl;

import fi.johannes.models.User;
import fi.johannes.models.CustomUserDetails;
import fi.johannes.services.interfaces.UserService;
import fi.johannes.services.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * johanness on 04/03/2017.
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findOneByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email=%s was not found", login)));
        return new CustomUserDetails(user);
    }


}

