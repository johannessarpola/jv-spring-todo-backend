package fi.johannes.controllers;

import fi.johannes.dto.TodoCreationForm;
import fi.johannes.dto.Todos;
import fi.johannes.models.Todo;
import fi.johannes.models.User;
import fi.johannes.services.impl.UsersServiceImpl;
import fi.johannes.services.interfaces.TodoService;
import fi.johannes.util.UserUtils;
import fi.johannes.util.runnables.DoSaveTodos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;


@RestController
@RequestMapping(path = "/todos/api", produces = "application/json")
public class TodoApiController {

    private final TodoService todoService;
    private final UsersServiceImpl userService;

    @Autowired
    public TodoApiController(UsersServiceImpl userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Callable<Todos> storeTodos(@RequestBody List<TodoCreationForm> forms) {
        DoSaveTodos saveTodos = new DoSaveTodos(todoService);
        return () -> {
            // TODO Add custom deserializer for Optional so we can return it
            saveTodos.addForms(forms).run();
            return new Todos(saveTodos.getSaved());
        };
    }

    @RequestMapping(path = "/today", method = RequestMethod.POST)
    public Todos getToDueToday(Principal principal) {
        User todoUser = userService.getUserByLogin(principal.getName()).get(); // FIXME
        return new Todos(todoService.getTodoDueToday(todoUser));
    }

    @RequestMapping(path = "/week", method = RequestMethod.GET)
    public Todos getToDueCurrentWeek(@RequestParam(name = "num", defaultValue = 10 + "", required = false) Integer number, Principal principal) {
        User todoUser = userService.getUserByLogin(principal.getName()).get(); // FIXME
        List<Todo> listtodo = todoService.getTodoDueCurrentWeek(todoUser);
        Todos todos = new Todos(listtodo);
        return todos;
    }

    @RequestMapping(path = "/todos", method = RequestMethod.POST)
    public List<Todo> getTodos(@RequestParam(name = "num", defaultValue = 10 + "", required = false) Integer number) {
        return todoService.getLatest(number, UserUtils.getCustomUser());
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public Todos getTodos() {
        return new Todos(todoService.allTodos());
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Todo update(@RequestBody Todo todo) {
        Todo updated = todoService.update(todo);
        return updated;
    }


}
