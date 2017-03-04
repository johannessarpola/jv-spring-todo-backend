package fi.johannes.services.repositories;

import fi.johannes.models.Todo;
import fi.johannes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	// Pretty fancy
	public List<Todo> findByCreator(User todoUser);
	public List<Todo> findByDeadline(LocalDateTime deadline, User todoUser);
	public List<Todo> findByDeadlineBetweenAndCreator(LocalDateTime first, LocalDateTime second, User todoUser);
	public List<Todo> findByIdGreaterThanEqual(long id, User todoUser);
	public Todo findById(long id);
}
