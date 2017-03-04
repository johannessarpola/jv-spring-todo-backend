package fi.johannes.dto;

import fi.johannes.models.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserCreateForm {

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String firstName="";

    @NotEmpty
    private String lastName="";

    @NotEmpty
    private String passwordRepeated = "";

    @NotEmpty
    private String login = "";

    @NotNull
    private Role role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public Role getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }
}