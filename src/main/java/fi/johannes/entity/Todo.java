package fi.johannes.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Todo {

	// TODO Open up possibility of removing a Todo without the need for the generated Id
	@Id
	@GeneratedValue
	@JsonIgnore
	long id;
	
	@ManyToOne
	User creator;
	
	String entry;
	LocalDateTime created;
	LocalDateTime deadline;
	Boolean done;
	
	@OneToOne
	Keywords keywords;
	
	public Keywords getKeywords() {
		return keywords;
	}
	public void setKeywords(Keywords keywords) {
		this.keywords = keywords;
	}
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
	public void addKeyword(String kw){
		keywords.add(kw);
	}
}
