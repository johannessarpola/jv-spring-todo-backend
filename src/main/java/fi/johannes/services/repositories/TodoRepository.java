package fi.johannes.services.repositories;

import java.time.LocalDateTime;
import java.util.List;

import fi.johannes.models.User;
import org.springframework.data.repository.CrudRepository;

import fi.johannes.models.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {

	// Pretty fancy
	public List<Todo> findByCreator(User todoUser);
	public List<Todo> findByDeadline(LocalDateTime deadline, User todoUser);
	public List<Todo> findByDeadlineBetweenAndCreator(LocalDateTime first, LocalDateTime second, User todoUser);
	public List<Todo> findByIdGreaterThanEqual(long id, User todoUser);
	public Todo findById(long id);
}
