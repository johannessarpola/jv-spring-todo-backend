package fi.johannes.services.impl;

import fi.johannes.TodoApp;
import fi.johannes.dto.TodoCreationForm;
import fi.johannes.models.Todo;
import fi.johannes.models.User;
import fi.johannes.services.interfaces.TodoService;
import fi.johannes.services.repositories.TodoRepository;
import fi.johannes.services.repositories.UserRepository;
import fi.johannes.services.repositories.WordRepository;
import fi.johannes.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Johannes on 16.3.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TodoServiceImplTest {

    // TODO Inject mockup repositories
    @Test
    public void store() throws Exception {

    }
    @Autowired
    private TodoServiceImpl todoService;

    @Test
    public void todosForUser() throws Exception {

    }

    @Test
    public void allTodos() throws Exception {

    }

    @Test
    public void getLatest() throws Exception {

    }

    @Test
    public void getTodoDueToday() throws Exception {

    }

    @Test
    public void getTodoDueCurrentWeek() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void fromForm() throws Exception {
        TodoCreationForm form = new TodoCreationForm();
        form.setDeadline(DateUtils.localDateTimeToString(LocalDateTime.now().plusDays(2)));
        form.setDone(false);
        form.setEntry("Abc");
        String[] arr = {"A", "B", "C"};
        form.setKeywords(arr);
        Todo todo =  todoService.fromForm(form, new User());
        assertThat(todo.getDone(), is(form.getDone()));
        assertThat(todo.getEntry(), is(form.getEntry()));
        assertThat(todo.getDeadline(), is(DateUtils.stringToLocalDateTime(form.getDeadline())));
        assertThat(todo.getKeywords().getWords().size(), is(arr.length));

    }



}