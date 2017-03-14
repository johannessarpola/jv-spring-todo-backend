package fi.johannes.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Keywords {

	@OneToOne
    private Todo parent;
	
	@Id
	@JsonIgnore
	@GeneratedValue
	long id;
	// TODO This could be designed to use less of a memory footprint by storing strings to separate
	// table and link ids of todo and the stirng in a separate entity
	@ManyToMany(cascade = CascadeType.MERGE)
    private Set<Word> words = new HashSet<>();
	
	public Keywords() {
		words = new HashSet<>();
	}
	public Todo getTodo() {
		return parent;
	}
	public void setTodo(Todo todo) {
		this.parent = todo;
	}

	public Set<Word> getWords() {
		return words;
	}
	// TODO Add to kw method
	public void setWords(Set<Word> words) {
		this.words = words;
	}

	// TODO This needs to handle the duplicate kws so that it first looks for one in db
	public void add(String kw) {
		words.add(new Word(kw));
	}

}
