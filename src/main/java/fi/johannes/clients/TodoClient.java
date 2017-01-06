package fi.johannes.clients;

import fi.johannes.dto.Todos;
import fi.johannes.entity.Todo;
import org.apache.mina.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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


    public TodoClient(String username,String password){
        this.username = username;
        this.password = password;
    }

    public TodoClient() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getAll(){
        // TODO Rest Template
        return null;
    }
    public List<Todo> getCurrentWeek(HttpHeaders headers){
        String url = backend + currentWeekMethod;
        Todos todos = doAuthenticatedRequest(url, HttpMethod.GET, null);
        return todos.getTodos();
    }
    private HttpHeaders createRequestHeaders(String username, String password){
        String base64Creds = createB64(username, password);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        return headers;
    }

    private Todos doAuthenticatedRequest(String url, HttpMethod method, String body){
        HttpHeaders httpHeaders = createRequestHeaders(username, password);
        HttpEntity<String> request = new HttpEntity<String>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Todos> response = restTemplate.exchange(url,
                method, request, Todos.class);
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
