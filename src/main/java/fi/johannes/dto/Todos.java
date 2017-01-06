package fi.johannes.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fi.johannes.entity.Todo;

import java.util.List;

/**
 * Created by johanness on 18/11/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Todos {

    private List<Todo> todos;

    public Todos(){

    }

    public Todos(List<Todo> todos) {
        this.todos = todos;
    }
    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
