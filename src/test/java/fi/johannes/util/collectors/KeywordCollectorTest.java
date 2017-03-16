package fi.johannes.util.collectors;

import fi.johannes.models.Word;
import fi.johannes.services.repositories.WordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class KeywordCollectorTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WordRepository wordRepository;

    @Test
    public void testCollector() throws Exception {
        this.entityManager.persist(new Word("C"));
        assertTrue(wordRepository.findOneByWordStr("C") != null);
        KeywordCollector kw = new KeywordCollector(wordRepository);
        List<String> strings = Arrays.asList("A", "B", "C");
        Collector<String, HashSet<Word>, HashSet<Word>> kwCollector = kw.getCollector();
        HashSet<Word> wordHashSet = strings.stream().collect(kwCollector);
        assertEquals(strings.size(), wordHashSet.size());
        assertTrue(wordRepository.findAll().stream().anyMatch(word -> strings.contains(word.getWordStr())));
    }

}