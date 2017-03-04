package fi.johannes.services.repositories;

import fi.johannes.dto.UserCreateForm;
import fi.johannes.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByLogin(String login);
	public User findByEmail(String email);
	public User findById(long id);
	Collection<User> getAllUsers();
	User create(UserCreateForm form);
}
