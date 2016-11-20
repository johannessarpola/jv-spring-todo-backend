package fi.johannes.clients;

import fi.johannes.entity.Todo;
import fi.johannes.entity.Todos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by johanness on 18/11/2016.
 */

@Component
public class TodoClient {

    @Value("${backend.url}")
    String backend;

    @Value("${method.week}")
    String currentWeekMethod;


    public TodoClient(){}

    public List<Todo> getAll(){
        // TODO Rest Template
        return null;
    }
    public List<Todo> getCurrentWeek(){
        RestTemplate restTemplate = new RestTemplate();
        Todos todos = restTemplate.getForObject(backend + currentWeekMethod, Todos.class);
        return todos.getTodos();
    }

}
