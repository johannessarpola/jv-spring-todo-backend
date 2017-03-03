package fi.johannes.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.johannes.common.Strings;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
public class User extends org.springframework.security.core.userdetails.User {


    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private String login;

    @OneToMany
    private Set<Role> roles;

    public User(String username,
                String password,
                boolean enabled,
                boolean accountNonExpired,
                boolean credentialsNonExpired,
                boolean accountNonLocked,
                Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        roles = new HashSet<>();
        firstName = Strings.emptyTextField;
        lastName = Strings.emptyTextField;
        login = Strings.emptyTextField;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Role> getUserRoles() {
        return roles;
    }
    public void addRole(Role role){
        this.roles.add(role);
    }
}
