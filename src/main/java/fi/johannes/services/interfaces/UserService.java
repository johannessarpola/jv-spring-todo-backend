package fi.johannes.services.interfaces;

import fi.johannes.dto.UserCreateForm;
import fi.johannes.models.User;

import java.util.Collection;
import java.util.Optional;

/**
 * johanness on 04/03/2017.
 */
public interface UserService {

    Optional<User> getUserById(long id);
    Optional<User> getUserByLogin(String login);
    Optional<User> getUserByEmail(String email);
    Collection<User> getAllUsers();
    User create(UserCreateForm form);

}
