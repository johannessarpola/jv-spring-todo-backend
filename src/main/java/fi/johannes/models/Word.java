package fi.johannes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * johanness on 14/03/2017.
 */
@Entity
@Data
public class Word {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;

    String wordStr;

    public Word() {
    }

    public Word(String kw) {
        this.wordStr = kw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word keyword1 = (Word) o;

        return wordStr != null ? wordStr.equals(keyword1.wordStr) : keyword1.wordStr == null;
    }

    @Override
    public int hashCode() {
        return wordStr != null ? wordStr.hashCode() : 0;
    }
}
