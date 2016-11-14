package fi.johannes.services;

import java.util.List;

import fi.johannes.dto.TodoDto;
import fi.johannes.entity.Todo;
import fi.johannes.entity.User;

public interface ITodoService {

	Todo store(TodoDto todo);

	List<Todo> todosForUser(User user);

	List<Todo> allTodos();

	List<Todo> getTodoDueCurrentWeek(User user);

	List<Todo> getLatest(Integer number, User user);

	List<Todo> getTodoDueToday(User user);

	Todo storeTodo(Todo todo);

	Todo update(Todo todo);

}