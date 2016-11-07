package fi.johannes.misc;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import fi.johannes.entity.Todo;
import fi.johannes.entity.User;

// FIXME This is unnecessary bloat
public class Mockup {

	public static User createUser(){
		User a = new User();
		a.setEmail("bob@bob.fi");
		a.setLogin("boblee");
		a.setName("Bob Lee");
		return a;
	}
	
	public static Todo createTestTodo() {
		User a = createUser();
		Todo todo = new Todo();
		todo.setCreator(a);
		todo.setDeadline(LocalDateTime.now().plus(100, ChronoUnit.DAYS));
		todo.setDone(false);
		todo.setEntry("Wash dishes");
		return todo;
	}
	
	

}
