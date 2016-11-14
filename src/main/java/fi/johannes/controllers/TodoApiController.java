package fi.johannes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fi.johannes.dto.TodoDto;
import fi.johannes.entity.Todo;
import fi.johannes.entity.User;
import fi.johannes.randombeans.MockTodo;
import fi.johannes.services.ITodoService;

@RestController
@RequestMapping(path="/todo")
public class TodoApiController {
	
	@Autowired
	ITodoService todoService;
	
	// TODO This needs to be stored somewhere
	private User user;
	
	@RequestMapping(path="/store", method=RequestMethod.POST)
	public ResponseEntity<Todo> storeTodo(@RequestBody TodoDto todo){
		// TODO Authentication
		if(todo != null) {
			Todo stored = todoService.store(todo);
			return ResponseEntity.ok(stored);
		}
		else {
			return ResponseEntity.badRequest().body(new Todo());
		}
	}
	
	@RequestMapping(path="/getTodos", method=RequestMethod.POST) 
	public List<Todo> getToDueToday(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number){
		return todoService.getTodoDueToday(user);
	}
	
	@RequestMapping(path="/currentWeek", method=RequestMethod.GET) 
	public List<Todo> getToDueCurrentWeek(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number){
		return todoService.getTodoDueCurrentWeek(user);
	}
	
	@RequestMapping(path="/todos", method=RequestMethod.POST) 
	public List<Todo> getTodos(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number){
		return todoService.getLatest(number, user);	
	}
	@RequestMapping(path="/all", method=RequestMethod.GET)
	public List<Todo> getTodos(){
		return todoService.allTodos();
	}
	@RequestMapping(path="/update", method=RequestMethod.POST)
	public Todo update(@RequestBody Todo todo){
		Todo updated = todoService.update(todo);
		return updated;
	}
	
	@RequestMapping(path="/mockups")
	List<Todo> mocking(){
		// TODO Move to tests
		for(int i=0; i<10; i++){
			Todo td = MockTodo.giveOne();
			td.getCreator();
			td.setKeywords(null);
			todoService.storeTodo(td);
		}
		return todoService.allTodos();
	}
	
}
