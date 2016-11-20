package fi.johannes.entity;

import java.util.List;

/**
 * Created by johanness on 18/11/2016.
 */
public class Todos {

    private List<Todo> todos;

    public Todos(){

    }
    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}
