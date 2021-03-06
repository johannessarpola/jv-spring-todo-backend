package fi.johannes.services.interfaces;

import java.util.List;

import fi.johannes.dto.TodoCreationForm;
import fi.johannes.models.Todo;
import fi.johannes.models.User;

public interface TodoService {


	List<Todo> todosForUser(User todoUser);

	List<Todo> allTodos();

	List<Todo> getTodoDueCurrentWeek(User todoUser);

	List<Todo> getLatest(Integer number, User todoUser);

	List<Todo> getTodoDueToday(User todoUser);

	Todo update(Todo todo);

	Todo save(TodoCreationForm todo);

}