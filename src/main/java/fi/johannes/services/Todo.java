package fi.johannes.services;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

@Entity
public class Todo {

	@GeneratedValue
	long id;
	
	@ManyToOne
	User creator;
	
	String entry;
	LocalDateTime created;
	LocalDateTime deadline;
	Boolean done;

	public Todo() {
		created = LocalDateTime.now();
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public LocalDateTime getDeadline() {
		return deadline;
	}
	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}
	public long getId() {
		return id;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	
	public Boolean getDone() {
		return done;
	}
	public void setDone(Boolean done) {
		this.done = done;
	}
}
