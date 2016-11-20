package fi.johannes.controllers;

import fi.johannes.clients.TodoClient;
import fi.johannes.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by johanness on 18/11/2016.
 */
public class AppController {
    @Autowired
    private TodoClient todoClient;

    @RequestMapping("/")
    String index(){
        // TODO welcome page
        return "index";
    }
    @RequestMapping("/week")
    String weekTodos(){

        return "";
    }

}
