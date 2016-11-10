package fi.johannes.services;

import java.util.List;

import fi.johannes.dto.TodoDto;
import fi.johannes.entity.Todo;
import fi.johannes.entity.User;

public interface ITodoService {

	Todo store(TodoDto todo);

	List<Todo> todosForUser(User user);

	List<Todo> allTodos();

	List<Todo> getTodoDueCurrentWeek();

	List<Todo> getLatest(Integer number);

	List<Todo> getTodoDueToday();

}