package fi.johannes.services.repositories;

import fi.johannes.models.Todo;
import fi.johannes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

	List<Todo> findLast10ByCreator(User creator);
	List<Todo> findByCreator(User todoUser);
	List<Todo> findByDeadline(LocalDateTime deadline, User todoUser);
	List<Todo> findByDeadlineBetweenAndCreator(LocalDateTime first, LocalDateTime second, User todoUser);
	List<Todo> findByIdGreaterThanEqual(long id, User todoUser);
	Todo findById(long id);
}
