package fi.johannes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fi.johannes.dto.TodoCreationForm;
import fi.johannes.serialization.CustomDateDeserializer;
import fi.johannes.serialization.CustomDateSerializer;
import fi.johannes.util.DateUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fi.johannes.util.StringUtils.getValueNotNullOrEmptyStringsPredicate;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todo {

	@Id
	@GeneratedValue
	@JsonIgnore
	long id;
	
	@ManyToOne
	User creator;
	
	String entry;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	LocalDateTime created;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
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

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public static Todo fromForm(TodoCreationForm todoCreationForm, User creator){
		Todo todo = new Todo();
		todo.setCreated(LocalDateTime.now());
		todo.setDeadline(DateUtils.stringToLocalDateTime(todoCreationForm.getDeadline()));
		todo.setEntry(todoCreationForm.getEntry());
		todo.setKeywords(createKeywordsFromArr(todoCreationForm.getKeywords()), todo);
        todo.setDone(todoCreationForm.getDone());
        return todo;
	}
    private static Keywords createKeywordsFromArr(String[] arr, Todo parent){
        Set<String> strings = Stream.of(arr).filter(getValueNotNullOrEmptyStringsPredicate()).collect(Collectors.toSet());
        Keywords keywords = new Keywords();
        keywords.setKeywords(strings);
        keywords.setTodo(parent);
        return keywords;
	}

}
