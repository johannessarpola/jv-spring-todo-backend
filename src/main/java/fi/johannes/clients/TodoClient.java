package fi.johannes.clients;

import fi.johannes.entity.Todo;
import fi.johannes.entity.Todos;
import org.apache.mina.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    String username;
    String password;

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
    private HttpHeaders createRequestHeaders(String username, String password){
        String base64Creds = createB64(username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return headers;
    }

    private String doAuthenticatedRequest(String url, HttpMethod method){
        HttpHeaders httpHeaders = createRequestHeaders(username, password);
        HttpEntity<String> request = new HttpEntity<String>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,
                method, request, String.class);
        return response.getBody();
    }

    private String createB64(String username, String password) {
        String plainCreds = username+":"+password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);
        return base64Creds;
    }
}
