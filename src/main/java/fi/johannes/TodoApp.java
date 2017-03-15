package fi.johannes;

import fi.johannes.models.*;
import fi.johannes.services.repositories.TodoRepository;
import fi.johannes.services.repositories.UserRepository;
import fi.johannes.services.repositories.WordRepository;
import org.h2.server.web.WebServlet;
import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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
    public CommandLineRunner addSuperUser(UserRepository userRepository) {
        return (args) -> {
            User superUser = new User();
            superUser.setLogin("johannes");
            superUser.addRole(Role.SUPER_ADMIN);
            superUser.setEmail("johannes.sarpola@gmail.com");
            superUser.setPasswordHash(new BCryptPasswordEncoder().encode("1234abcd")); // FIXME
            superUser.setFirstName("Johannes");
            superUser.setLastName("Sarpola");
            userRepository.save(superUser);
        };

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


    @Bean
    public CommandLineRunner populateWeek(TodoRepository todoRepository, UserRepository userRepository, WordRepository wordRepository) {
        // Populates db with couple todos
        return (String... args) -> {
            String[] entries = {"Wash dishes", "See doctor", "Take out garbage", "Walk the dog",
                    "Study stuff", "Be smart", "Don't die", "Pay rent", "Upgrade lives of other people"};
            String[] logins = {"johnny", "ben", "einstein"};
            String[] emails = {"johnny@mail.ru", "ben@bensmail.com", "one@ones.com"};
            String[] names = {"Johnny Alamo", "Ben Rubenstein", "Albert Einstein"};
            Random random = new Random(123L);
            for (int i = 0; i < 10; i++) {
                Todo todo = new Todo();
                User todoUser = userRepository.findOneByLogin("johannes");
                int entryindex = random.nextInt(entries.length);
                LocalDateTime now = LocalDateTime.now().withHour(6);
                int secondstoadd = random.nextInt(7 * 24 * 60 * 60);
                int secondstoaddcreated = random.nextInt(12 * 60 * 60);
                todo.setCreator(todoUser);
                todo.setDone(random.nextBoolean());
                todo.setEntry(entries[entryindex]);
                todo.setDeadline(now.plusSeconds(secondstoadd));
                todo.setCreated(now.plusSeconds(secondstoaddcreated));

                Keywords keywords = new Keywords();
                List<String> kws = Arrays.asList("Word", "Second word");
                keywords.setWords(kws.stream()
                        .map(s -> {
                            Word f = wordRepository.findOneByWordStr(s).orElseGet(() -> wordRepository.save(new Word(s)));
                            return f;
                        })
                        .collect(Collectors.toSet()));
                keywords.setTodo(todo);
                todo.setKeywords(keywords);

                todoRepository.save(todo);
            }
        };
    }
}
