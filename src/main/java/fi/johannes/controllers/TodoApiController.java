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
import fi.johannes.services.ITodoService;

@RestController
@RequestMapping(path="/todo")
public class TodoApiController {
	
	@Autowired
	ITodoService todoService;
	
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
		return todoService.getTodoDueToday();
	}
	
	@RequestMapping(path="/currentWeek", method=RequestMethod.POST) 
	public List<Todo> getToDueCurrentWeek(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number){
		return todoService.getTodoDueCurrentWeek();
		
	}
	
	@RequestMapping(path="/todos", method=RequestMethod.POST) 
	public List<Todo> getTodos(@RequestParam(name="num", defaultValue=10+"", required=false) Integer number){
		return todoService.getLatest(number);
		
	}
}
