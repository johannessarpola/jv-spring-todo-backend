package fi.johannes.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.entity.Todo;
import fi.johannes.entity.User;

public interface ITodoDao extends CrudRepository<Todo, Long> {

	// Pretty fancy
	public List<Todo> findByCreator(User user);
	public List<Todo> findByDeadline(LocalDateTime deadline);
	public List<Todo> findByDeadlineBetween(LocalDateTime start, LocalDateTime end);
	public List<Todo> findByIdGreaterThanEqual(long id);
}
