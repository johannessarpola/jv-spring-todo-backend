package fi.johannes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fi.johannes.services.ITodoService;
import fi.johannes.services.Todo;

@RestController
@RequestMapping(path="/todo")
public class TodoApiController {
	
	@Autowired
	ITodoService todoService;
	
	@RequestMapping(path="/store", method=RequestMethod.POST)
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
}
