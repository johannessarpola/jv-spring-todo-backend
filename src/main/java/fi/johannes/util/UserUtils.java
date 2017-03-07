package fi.johannes.util;

import fi.johannes.models.CustomUserDetails;
import fi.johannes.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * johanness on 07/03/2017.
 */
public class UserUtils {

    public static User getCurrentUser(){
        CustomUserDetails userDetails =
                (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }
}
