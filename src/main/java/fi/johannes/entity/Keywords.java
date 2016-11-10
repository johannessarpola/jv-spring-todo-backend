package fi.johannes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Keywords {

	@ManyToOne
	Todo parent;
	@Id
	@GeneratedValue
	long id;
	// TODO This could be designed to use less of a memory footprint by storing strings to separate
	// table and link ids of todo and the stirng in a separate entity
	List<String> keywords;
	public Keywords() {
		keywords = new ArrayList<>();
	}
	public Todo getTodo() {
		return parent;
	}
	public void setTodo(Todo todo) {
		this.parent = todo;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	public void add(String kw) {
		keywords.add(kw);
	}
	
}
