package fi.johannes.clients;

import fi.johannes.entity.Todo;
import fi.johannes.entity.Todos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.RequestContext;

import java.util.ArrayList;
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
    public List<Todo> getCurrentWeek(HttpHeaders headers){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> request = new HttpEntity<Object>(headers);
        String url = backend + currentWeekMethod;
        // FIXME Crashes as it won't pass the credentials forward
        //Todos todos = restTemplate.getForObject(url, request, Todos.class);
        String resp = restTemplate.exchange(url, HttpMethod.GET, request, String.class).getBody();

        // TODO just return the Todos
        //return todos.getTodos();
        return new ArrayList<Todo>();
    }

}
