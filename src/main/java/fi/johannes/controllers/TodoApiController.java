package fi.johannes.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fi.johannes.dto.Todos;
import fi.johannes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fi.johannes.entity.Todo;
import fi.johannes.entity.User;
import fi.johannes.randombeans.MockTodo;
import fi.johannes.services.ITodoService;

@RestController
@RequestMapping(path="/todos/api", produces = "application/json")
public class TodoApiController {
	
	@Autowired
	ITodoService todoService;

	@Autowired
    UserService userService; // TODO Interface
	
	// TODO This needs to be stored somewhere
	private User user;


	@RequestMapping(path="/store/single", method=RequestMethod.POST)
	public ResponseEntity<Todo> storeTodo(@RequestBody Todo todo){
		// TODO Authentication
		if(todo != null) {
			Todo stored = todoService.store(todo);
			return ResponseEntity.ok(stored);
		}
		else {
			return ResponseEntity.badRequest().body(new Todo());
		}
	}
	@RequestMapping(path="/store/multiple", method=RequestMethod.POST)
	public ResponseEntity<List<Todo>> storeTodos(@RequestBody Todo[] todos){
		// TODO Authentication
		if(todos != null) {
			List<Todo> todosList = new ArrayList<>();
			Arrays.stream(todos).forEach(todo -> todosList.add(todoService.store(todo)));
			return ResponseEntity.ok(todosList);
		}
		else {
			return ResponseEntity.badRequest().body(null);
		}
	}
	// TODO Change to Todos
	@RequestMapping(path="/getTodos", method=RequestMethod.POST) 
	public List<Todo> getToDueToday(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number){
		return todoService.getTodoDueToday(user);
	}
	
	@RequestMapping(path="/week", method=RequestMethod.GET)
	public Todos getToDueCurrentWeek(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number, Principal principal){

	    System.out.println(principal);
	    User user = userService.findByUsername(principal.getName());
		List<Todo> listtodo = todoService.getTodoDueCurrentWeek(user);
        Todos todos = new Todos(listtodo);
        return todos;
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

	@RequestMapping(path="/user", method = RequestMethod.GET)
	UserDetails getUser(){
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

	@RequestMapping(path="/mockups")
	List<Todo> mocking(){
		// TODO Move to tests
		for(int i=0; i<10; i++){
			Todo td = MockTodo.giveOne();
			td.getCreator();
			td.setKeywords(null);
			todoService.store(td);
		}
		return todoService.allTodos();
	}
	
}
