package fi.johannes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fi.johannes.common.Strings;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
public class User {


    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;
    private String firstName;
    private String lastName;

    @JsonIgnore
    private String login;

    @JsonIgnore
    private String passwordHash;

    @OneToMany
    private Set<Role> roles;

    public User() {
        roles = new HashSet<>();
        firstName = Strings.emptyTextField;
        lastName = Strings.emptyTextField;
        login = Strings.emptyTextField;
        passwordHash = Strings.emptyTextField;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public Collection<String> getRolesAsStringsCol() {
        return roles.stream().map(Role::getShortenedName).collect(Collectors.toList());
    }
    public String[] getRolesAsStringsArr() {
        Collection<String> roles = getRolesAsStringsCol();
        return roles.toArray(new String[roles.size ()]);
    }
}
