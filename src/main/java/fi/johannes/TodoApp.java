package fi.johannes;

import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class TodoApp {

	public static void main(String[] args) {
		SpringApplication.run(TodoApp.class, args);
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    return registration;
	}
	
	@Bean
	org.h2.tools.Server h2Server() {
	    Server server = new Server();
	    try {
	        server.runTool("-tcp");
	        server.runTool("-tcpAllowOthers");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return server;

	}

	/*@Bean
	public CommandLineRunner populateWeek(TodoService todoService){
	    // Populates db with couple todos
        return (args) -> {
            String[] entries = {"Wash dishes", "See doctor", "Take out garbage", "Walk the dog",
                    "Study stuff", "Be smart", "Don't die", "Pay rent", "Upgrade lives of other people"};
            String[] logins = {"johnny", "ben", "einstein"};
            String[] emails = {"johnny@mail.ru", "ben@bensmail.com", "one@ones.com"};
            String[] names = {"Johnny Alamo", "Ben Rubenstein", "Albert Einstein"};
            Random random = new Random(123L);
            for (int i = 0; i < 100; i++) {
                Todo todo = new Todo();
                User todoUser = new User();
                int userindex = random.nextInt(logins.length);
                int entryindex = random.nextInt(entries.length);
                LocalDateTime now = LocalDateTime.now().withHour(6);
                int secondstoadd = random.nextInt(7 * 24 * 60 * 60);
                int secondstoaddcreated = random.nextInt(12 * 60 * 60);
                todoUser.setEmail(emails[userindex]);
                todoUser.setName(names[userindex]);
                todoUser.setLogin(logins[userindex]);
                todo.setCreator(todoUser);
                todo.setDone(random.nextBoolean());
                todo.setEntry(entries[entryindex]);
                todo.setDeadline(now.plusSeconds(secondstoadd));
                todo.setCreated(now.plusSeconds(secondstoaddcreated));
                todoService.store(todo);
            }
        };
	}*/
}
