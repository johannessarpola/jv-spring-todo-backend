package fi.johannes.services.interfaces;

import fi.johannes.dto.UserCreateForm;
import fi.johannes.models.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Collection;
import java.util.Optional;

/**
 * johanness on 04/03/2017.
 */

@Secured({"ADMIN", "SUPERADMIN"})
public interface UserService {
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserById(long id);
    Optional<User> getUserByEmail(String email);
    Collection<User> getAllUsers();
    User create(UserCreateForm form);

}
