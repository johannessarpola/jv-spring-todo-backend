package fi.johannes.services;

import java.util.List;

public interface ITodoService {

	Todo store(TodoDto todo);

	List<Todo> todosForUser(User user);

	List<Todo> allTodos();
	// TODO Removal

}