package fi.johannes.util.runnables;

import fi.johannes.dto.TodoCreationForm;
import fi.johannes.models.Todo;
import fi.johannes.services.interfaces.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;
import java.util.stream.Collectors;

/**
 * johanness on 18/03/2017.
 */
public class DoSaveTodos implements Runnable {
    private final Logger log = LoggerFactory.getLogger(getClass());

    final private TodoService todoService;

    private Collection<TodoCreationForm> forms;
    private List<Todo> saved = new ArrayList<>();

    @Autowired
    public DoSaveTodos(TodoService todoService) {
        this.todoService = todoService;
    }

    public DoSaveTodos addForms(Collection<TodoCreationForm> forms){
        this.forms = forms;
        return this;
    }
    @Override
    public void run() {
        if(forms != null) {
            saved = forms.stream().filter(TodoCreationForm::isValid).map(todoService::save).collect(Collectors.toList());
        }
        else {
            log.warn("no forms received on the job");
        }
    }
    public boolean isDone(){
        return saved != null;
    }
    public List<Todo> getSaved(){
        return saved;
    }
}
