package fi.johannes.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.entity.Todo;
import fi.johannes.entity.User;

public interface ITodoDao extends CrudRepository<Todo, Long> {

	// Pretty fancy
	public List<Todo> findByCreator(User user);
}
