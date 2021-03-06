package fi.johannes.models;

import org.springframework.core.env.Environment;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import java.util.Set;

/**
 * johanness on 04/03/2017.
 */
public class CustomUserDetails extends org.springframework.security.core.userdetails.User{
    private User user;

    public CustomUserDetails(fi.johannes.models.User user) {
        super(user.getLogin(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRolesAsStringsArr()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Set<Role> getRole() {
        return user.getUserRoles();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }

}
