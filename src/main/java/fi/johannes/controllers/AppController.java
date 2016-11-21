package fi.johannes.controllers;

import fi.johannes.clients.TodoClient;
import fi.johannes.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContext;

import java.util.List;

/**
 * Created by johanness on 18/11/2016.
 */
@Controller
public class AppController {

    @Autowired
    private TodoClient todoClient;

    @RequestMapping("/")
    public String index(){
        // TODO welcome page
        return "inde    x";
    }
    @RequestMapping("/week")
    public String weekTodos(Model model, @RequestHeader HttpHeaders headers){
        List<Todo> todos = todoClient.getCurrentWeek(headers);
        model.addAttribute("todos", todos);
        return "week";
    }

}
