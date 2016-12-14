package fi.johannes.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import fi.johannes.dao.ITodoDao;
import fi.johannes.dao.IUserDao;
import fi.johannes.entity.Todo;
import fi.johannes.entity.User;
import fi.johannes.misc.Mockup;
import fi.johannes.util.DateUtils;

@Service
public class TodoService implements ITodoService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ITodoDao tododao;
	
	@Autowired
	IUserDao userdao;
	
	@Override
	public  Todo store(Todo todo){
		User u =todo.getCreator();
		User saved;
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
	}
	
	/* (non-Javadoc)
	 * @see fi.johannes.services.ITodoService#todosForUser(fi.johannes.services.User)
	 */
	@Override
	public List<Todo> todosForUser(User user) {
		List<Todo> todos = tododao.findByCreator(user);
		if(todos == null || todos.isEmpty()) {
			log.info("No todos found for user "+user.getLogin());
			return new ArrayList<Todo>();
		}
		else {
			return todos;
		}
	}
	/* (non-Javadoc)
	 * @see fi.johannes.services.ITodoService#allTodos()
	 */
	@Override
	public List<Todo> allTodos(){
		return (List<Todo>) tododao.findAll();
	}
	@Override
	public List<Todo> getLatest(Integer number, User user){
		long lastid = tododao.count();
		long neg = lastid - number.longValue();
		List<Todo> todos = tododao.findByIdGreaterThanEqual(neg, user);
		return todos;
	}
	
	@Override
	public List<Todo> getTodoDueToday(User user){
		List<Todo> toDue = tododao.findByDeadline(LocalDateTime.now(), user);
		return toDue;
	}
	
	@Override
	public List<Todo> getTodoDueCurrentWeek(User user){
		Pair<LocalDateTime, LocalDateTime> week= DateUtils.getCurrentWeek();
		List<Todo> toDue = tododao.findByDeadlineBetween(week.getFirst(), week.getSecond(), user);
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
