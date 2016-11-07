package fi.johannes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.services.Todo;
import fi.johannes.services.User;

public interface ITodoDao extends CrudRepository<Todo, Long> {

	// Pretty fancy
	public List<Todo> findByCreator(User user);
}
