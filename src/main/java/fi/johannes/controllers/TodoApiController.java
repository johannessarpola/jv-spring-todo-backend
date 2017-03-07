package fi.johannes.controllers;

import fi.johannes.dto.Todos;
import fi.johannes.models.Todo;
import fi.johannes.models.User;
import fi.johannes.services.impl.UsersServiceImpl;
import fi.johannes.services.interfaces.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(path = "/todos/api", produces = "application/json")
public class TodoApiController {

    private final TodoService todoService;
    private final UsersServiceImpl userService;

    // TODO This needs to be stored somewhere
    private User todoUser;

    @Autowired
    public TodoApiController(UsersServiceImpl userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(path = "/store/single", method = RequestMethod.POST)
    public ResponseEntity<Todo> storeTodo(@RequestBody Todo todo) {
        // TODO Authentication
        if (todo != null) {
            Todo stored = todoService.store(todo);
            return ResponseEntity.ok(stored);
        } else {
            return ResponseEntity.badRequest().body(new Todo());
        }
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(path = "/store/multiple", method = RequestMethod.POST)
    public ResponseEntity<List<Todo>> storeTodos(@RequestBody Todo[] todos) {
        // TODO Authentication
        if (todos != null) {
            List<Todo> todosList = new ArrayList<>();
            Arrays.stream(todos).forEach(todo -> todosList.add(todoService.store(todo)));
            return ResponseEntity.ok(todosList);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(path = "/today", method = RequestMethod.POST)
    public Todos getToDueToday(Principal principal) {
        User todoUser = userService.getUserByLogin(principal.getName()).get(); // FIXME
        return new Todos(todoService.getTodoDueToday(todoUser));
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(path = "/week", method = RequestMethod.GET)
    public Todos getToDueCurrentWeek(@RequestParam(name = "num", defaultValue = 10 + "", required = false) Integer number, Principal principal) {
        User todoUser = userService.getUserByLogin(principal.getName()).get(); // FIXME
        List<Todo> listtodo = todoService.getTodoDueCurrentWeek(todoUser);
        Todos todos = new Todos(listtodo);
        return todos;
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(path = "/todos", method = RequestMethod.POST)
    public List<Todo> getTodos(@RequestParam(name = "num", defaultValue = 10 + "", required = false) Integer number) {
        return todoService.getLatest(number, todoUser);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Todo> getTodos() {
        return todoService.allTodos();
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public Todo update(@RequestBody Todo todo) {
        Todo updated = todoService.update(todo);
        return updated;
    }


}
