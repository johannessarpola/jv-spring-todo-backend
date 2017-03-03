package fi.johannes.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Keywords {

	@OneToOne
	Todo parent;
	
	@Id
	@JsonIgnore
	@GeneratedValue
	long id;
	// TODO This could be designed to use less of a memory footprint by storing strings to separate
	// table and link ids of todo and the stirng in a separate entity
	@ElementCollection
	Set<String> keywords;
	
	public Keywords() {
		keywords = new HashSet<>();
	}
	public Todo getTodo() {
		return parent;
	}
	public void setTodo(Todo todo) {
		this.parent = todo;
	}
	public Set<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(Set<String> keywords) {
		this.keywords = keywords;
	}
	public void add(String kw) {
		keywords.add(kw);
	}
	
}
