package fi.johannes.controllers;

import fi.johannes.dto.Todos;
import fi.johannes.models.Todo;
import fi.johannes.models.User;
import fi.johannes.services.impl.UsersService;
import fi.johannes.services.interfaces.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="/todos/api", produces = "application/json")
public class TodoApiController {
	
	private final ITodoService todoService;
	private final UsersService userService;
	
	// TODO This needs to be stored somewhere
	private User todoUser;

	@Autowired
	public TodoApiController(UsersService userService, ITodoService todoService) {
		this.userService = userService;
		this.todoService = todoService;
	}


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

	@RequestMapping(path="/today", method=RequestMethod.POST)
	public Todos getToDueToday(Principal principal){
		User todoUser = userService.findByUsername(principal.getName());
		return new Todos(todoService.getTodoDueToday(todoUser));
	}
	
	@RequestMapping(path="/week", method=RequestMethod.GET)
	public Todos getToDueCurrentWeek(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number, Principal principal){
		User todoUser = userService.findByUsername(principal.getName());
		List<Todo> listtodo = todoService.getTodoDueCurrentWeek(todoUser);
        Todos todos = new Todos(listtodo);
        return todos;
	}

	@RequestMapping(path="/todos", method=RequestMethod.POST)
	public List<Todo> getTodos(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number){
		return todoService.getLatest(number, todoUser);
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


}
