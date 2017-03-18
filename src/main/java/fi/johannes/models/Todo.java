package fi.johannes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fi.johannes.dto.TodoCreationForm;
import fi.johannes.serialization.CustomDateDeserializer;
import fi.johannes.serialization.CustomDateSerializer;
import fi.johannes.util.DateUtils;
import fi.johannes.util.collectors.KeywordCollector;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
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

	// TODO Add custom serializer for this so that only username & id is given to front
	@ManyToOne(optional = false)
	User creator;
	
	String entry;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	LocalDateTime created;

	@JsonDeserialize(using = CustomDateDeserializer.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	LocalDateTime deadline;

	Boolean done;

	@OneToOne(optional = true, cascade = CascadeType.ALL)
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


}
