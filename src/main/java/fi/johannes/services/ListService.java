package fi.johannes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

@Service
public class ListService {

	String host = "http://localhost:8081/";
	String genUrl = "gen/";
	String randStringSubUrl = "rand/string100";
	public ListService(){
		
	}
	public List<String> giveTen(){
		List<String> strings = new ArrayList<>();
		RestTemplate rt = new RestTemplate();
		for(int i = 0; i<10; i++) {
			String url = host + genUrl + randStringSubUrl;
			String result = rt.getForObject(url, String.class);
			strings.add(result);

		}
		return strings;
		
	}
}
