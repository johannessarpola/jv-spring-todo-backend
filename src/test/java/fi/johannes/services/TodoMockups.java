package fi.johannes.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import fi.johannes.models.Todo;
import fi.johannes.models.User;

public class TodoMockups {

/*	public static User createUser(){
		User a = new User();
		a.setEmail("bob@bob.fi");
		a.setLogin("boblee");
		a.setName("Bob Lee");
		return a;
	}
*/
	public static Todo createTestTodo() {
		User a = null;
		Todo todo = new Todo();
		todo.setCreator(a);
		todo.setDeadline(LocalDateTime.now().plus(100, ChronoUnit.DAYS));
		todo.setDone(false);
		todo.setEntry("Wash dishes");
		return todo;
	}
	
	

}
