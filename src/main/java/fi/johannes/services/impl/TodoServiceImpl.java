package fi.johannes.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fi.johannes.models.User;
import fi.johannes.services.interfaces.ITodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import fi.johannes.services.dao.TodoDao;
import fi.johannes.services.dao.UserDao;
import fi.johannes.models.Todo;
import fi.johannes.util.DateUtils;

@Service
public class TodoService implements ITodoService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TodoDao tododao;
	
	@Autowired
	UserDao userdao;
	
	@Override
	public  Todo store(Todo todo){
		User u =todo.getCreator();
		User saved;
		/*
			if(userdao.findByLogin(u.getLogin())== null) {
				saved = userdao.save(u);
			}
			else {
				saved = userdao.findByLogin(u.getLogin());
			}
			todo.setCreator(saved);
			if(tododao.findById(todo.getId()) == null){
				return tododao.save(todo);
			}
			else return null;
		*/
		return null;
	}
	
	/* (non-Javadoc)
	 * @see fi.johannes.services.interfaces.ITodoService#todosForUser(fi.johannes.services.User)
	 */
	@Override
	public List<Todo> todosForUser(User todoUser) {
		List<Todo> todos = tododao.findByCreator(todoUser);
		if(todos == null || todos.isEmpty()) {
			return new ArrayList<Todo>();
		}
		else {
			return todos;
		}
	}
	/* (non-Javadoc)
	 * @see fi.johannes.services.interfaces.ITodoService#allTodos()
	 */
	@Override
	public List<Todo> allTodos(){
		return (List<Todo>) tododao.findAll();
	}
	@Override
	public List<Todo> getLatest(Integer number, User todoUser){
		long lastid = tododao.count();
		long neg = lastid - number.longValue();
		List<Todo> todos = tododao.findByIdGreaterThanEqual(neg, todoUser);
		return todos;
	}
	
	@Override
	public List<Todo> getTodoDueToday(User todoUser){
		List<Todo> toDue = tododao.findByDeadline(LocalDateTime.now(), todoUser);
		return toDue;
	}
	
	@Override
	public List<Todo> getTodoDueCurrentWeek(User todoUser){
		Pair<LocalDateTime, LocalDateTime> week= DateUtils.getCurrentWeek();
		List<Todo> toDue = tododao.findByDeadlineBetweenAndCreator(week.getFirst(), week.getSecond(), todoUser);
		return toDue;
	}

	@Override
	public Todo update(Todo todo) {
		if(tododao.findById(todo.getId()) != null) {
			return tododao.save(todo);
		}
		else {
			return null;
		}
	}
}
