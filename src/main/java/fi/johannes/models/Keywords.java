package fi.johannes.models;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Keywords {
	
	@Id
	@JsonIgnore
	@GeneratedValue
	long id;

	@ManyToMany(cascade = CascadeType.MERGE)
    private Set<Word> words = new HashSet<>();
	
	public Keywords() {
		words = new HashSet<>();
	}

	public Set<Word> getWords() {
		return words;
	}
	public void setWords(Set<Word> words) {
		this.words = words;
	}
	public void add(String kw) {
		words.add(new Word(kw));
	}

	public static Keywords empty() {
	    Keywords kws = new Keywords();
	    kws.setWords(Collections.emptySet());
	    return kws;
    }
}
