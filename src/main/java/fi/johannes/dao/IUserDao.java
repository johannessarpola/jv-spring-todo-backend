package fi.johannes.dao;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.entity.User;

public interface IUserDao extends CrudRepository<User, Long> {

	public User findByLogin(String login);
	public User findByEmail(String email);
	public User findByName(String name);
	public User findById(long id);
}
