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
import fi.johannes.dto.TodoDto;
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
	
	/* (non-Javadoc)
	 * @see fi.johannes.services.ITodoService#store(fi.johannes.services.Todo)
	 */
	@Override
	public Todo store(TodoDto todo) {
		Todo todoEnt = dtoToEnt(todo);
		userdao.save(todoEnt.getCreator());
		tododao.save(todoEnt);
		return tododao.findOne(todoEnt.getId());
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
	
	public List<Todo> getLatest(Integer number){
		long lastid = tododao.count();
		long neg = lastid - number.longValue();
		List<Todo> todos = tododao.findByIdGreaterThanEqual(neg);
		return todos;
	}
	@Override
	public List<Todo> getTodoDueCurrentWeek(){
		Pair<LocalDateTime, LocalDateTime> week= DateUtils.getCurrentWeek();
		List<Todo> toDue = tododao.findByDeadlineBetween(week.getFirst(), week.getSecond());
		return toDue;
	}
	private Todo dtoToEnt(TodoDto dto) {
		Todo ent = new Todo();
		// FIXME To userdao search
		ent.setCreator(Mockup.createUser());
		// FIXME Actual date from string
		ent.setDeadline(LocalDateTime.now());
		ent.setDone(dto.getDone());
		ent.setEntry(dto.getEntry());
		return ent;
	}
	
	
	// TODO Removal
}
