package fi.johannes.services.impl;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Stream;

import fi.johannes.dto.TodoCreationForm;
import fi.johannes.models.Keywords;
import fi.johannes.models.User;
import fi.johannes.models.Word;
import fi.johannes.services.repositories.WordRepository;
import fi.johannes.util.UserUtils;
import fi.johannes.util.collectors.KeywordCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import fi.johannes.services.repositories.TodoRepository;
import fi.johannes.services.repositories.UserRepository;
import fi.johannes.models.Todo;
import fi.johannes.util.DateUtils;

@Service
public class TodoServiceImpl implements fi.johannes.services.interfaces.TodoService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final TodoRepository todoRepository;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, WordRepository wordRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }

    @Override
    public Todo save(TodoCreationForm form) {
        Todo todo = fromForm(form, UserUtils.getCustomUser());
        todo = todoRepository.save(todo);
        return todo;
    }


    @Override
    public List<Todo> todosForUser(User todoUser) {
        List<Todo> todos = todoRepository.findByCreator(todoUser);
        if (todos == null || todos.isEmpty()) {
            return Collections.emptyList();
        } else {
            return todos;
        }
    }


    @Override
    public List<Todo> allTodos() {
        List<Todo> all = todoRepository.findAll();
        return all;
    }

    @Override
    public List<Todo> getLatest(Integer number, User todoUser) {
        List<Todo> todos = todoRepository.findLast10ByCreator(todoUser);
        return todos;
    }

    @Override
    public List<Todo> getTodoDueToday(User todoUser) {
        List<Todo> toDue = todoRepository.findByDeadline(LocalDateTime.now(), todoUser);
        return toDue;
    }

    @Override
    public List<Todo> getTodoDueCurrentWeek(User todoUser) {
        Pair<LocalDateTime, LocalDateTime> week = DateUtils.getCurrentWeek();
        List<Todo> toDue = todoRepository.findByDeadlineBetweenAndCreator(week.getFirst(), week.getSecond(), todoUser);
        return toDue;
    }

    @Override
    public Todo update(Todo todo) {
        if (todoRepository.findById(todo.getId()) != null) {
            return todoRepository.save(todo);
        } else {
            return null;
        }
    }

    public Todo fromForm(TodoCreationForm todoCreationForm, User creator) {
        Todo todo = new Todo();
        todo.setCreator(creator);
        todo.setCreated(LocalDateTime.now());
        todo.setDeadline(DateUtils.stringToLocalDateTime(todoCreationForm.getDeadline()));
        todo.setEntry(todoCreationForm.getEntry());
        if(todoCreationForm.getKeywords().isPresent()) {
            todo.setKeywords(createKeywordsFromArr(todoCreationForm.getKeywords().get()));
        }
        todo.setDone(todoCreationForm.getDone());
        return todo;
    }

    private Keywords createKeywordsFromArr(String[] arr) {
        KeywordCollector kwc = new KeywordCollector(wordRepository);
        Collector<String, HashSet<Word>, HashSet<Word>> wordCollector = kwc.getCollector();
        Keywords keywords = new Keywords();
        keywords.setWords(Stream.of(arr).collect(wordCollector));
        return keywords;

    }
}
