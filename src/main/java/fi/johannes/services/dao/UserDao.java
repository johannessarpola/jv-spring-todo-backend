package fi.johannes.services.dao;

import fi.johannes.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

	public User findByLogin(String login);
	public User findByEmail(String email);
	public User findById(long id);
}
