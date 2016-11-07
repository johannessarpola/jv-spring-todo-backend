package fi.johannes.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.johannes.dao.ITodoDao;

@Service
public class TodoService {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	ITodoDao tododao;
	
	public Todo store(Todo todo) {
		tododao.save(todo);
		return tododao.findOne(todo.getId());
	}
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
	
}
