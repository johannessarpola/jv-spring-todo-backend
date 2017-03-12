package fi.johannes.util;

import fi.johannes.models.CustomUserDetails;
import fi.johannes.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import java.security.Principal;

/**
 * johanness on 07/03/2017.
 */
public class UserUtils {

    public static UserDetails getUserDetails(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public static CustomUserDetails getCustomUserDetails(){
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static User getCustomUser(){
        CustomUserDetails userDetails = getCustomUserDetails();
        return userDetails.getUser();
    }
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
    private static Object getUserObj(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    public static boolean isLdapAuthenticated(){
        return getUserObj() instanceof LdapUserDetails;
    }
    public static boolean isInternallyAuthenticated(){
        return !isLdapAuthenticated();
    }

}
