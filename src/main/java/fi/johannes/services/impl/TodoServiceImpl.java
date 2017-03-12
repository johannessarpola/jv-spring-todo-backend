package fi.johannes.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fi.johannes.dto.TodoCreationForm;
import fi.johannes.models.User;
import fi.johannes.util.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import fi.johannes.services.repositories.TodoRepository;
import fi.johannes.services.repositories.UserRepository;
import fi.johannes.models.Todo;
import fi.johannes.util.DateUtils;

@Service
public class TodoServiceImpl implements fi.johannes.services.interfaces.TodoService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	final TodoRepository todoRepository;
	
	final UserRepository userRepository;

	@Autowired
	public TodoServiceImpl(TodoRepository todoRepository, UserRepository userRepository) {
		this.todoRepository = todoRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Todo store(TodoCreationForm form){
		Todo todo = Todo.fromForm(form, UserUtils.getCustomUser());
		todo = todoRepository.save(todo);
		return todo;
	}
	
	/* (non-Javadoc)
	 * @see fi.johannes.services.interfaces.TodoService#todosForUser(fi.johannes.services.User)
	 */
	@Override
	public List<Todo> todosForUser(User todoUser) {
		List<Todo> todos = todoRepository.findByCreator(todoUser);
		if(todos == null || todos.isEmpty()) {
			return new ArrayList<Todo>();
		}
		else {
			return todos;
		}
	}
	/* (non-Javadoc)
	 * @see fi.johannes.services.interfaces.TodoService#allTodos()
	 */
	@Override
	public List<Todo> allTodos(){
		return (List<Todo>) todoRepository.findAll();
	}
	@Override
	public List<Todo> getLatest(Integer number, User todoUser){
		long lastid = todoRepository.count();
		long neg = lastid - number.longValue();
		List<Todo> todos = todoRepository.findByIdGreaterThanEqual(neg, todoUser);
		return todos;
	}
	
	@Override
	public List<Todo> getTodoDueToday(User todoUser){
		List<Todo> toDue = todoRepository.findByDeadline(LocalDateTime.now(), todoUser);
		return toDue;
	}
	
	@Override
	public List<Todo> getTodoDueCurrentWeek(User todoUser){
		Pair<LocalDateTime, LocalDateTime> week= DateUtils.getCurrentWeek();
		List<Todo> toDue = todoRepository.findByDeadlineBetweenAndCreator(week.getFirst(), week.getSecond(), todoUser);
		return toDue;
	}

	@Override
	public Todo update(Todo todo) {
		if(todoRepository.findById(todo.getId()) != null) {
			return todoRepository.save(todo);
		}
		else {
			return null;
		}
	}
}
