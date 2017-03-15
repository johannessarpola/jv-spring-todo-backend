package fi.johannes.util.collectors;

import fi.johannes.models.Word;
import fi.johannes.services.repositories.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * johanness on 15/03/2017.
 */
public class KeywordCollector {

    private WordRepository wordRepository;

    @Autowired
    public KeywordCollector(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    private Supplier<HashSet<Word>> supplier = HashSet::new;
    private BiConsumer<HashSet<Word>, String> accumulator = (set, str) -> set.add(saveOrGet(str));
    private BinaryOperator<HashSet<Word>> combiner = (left, right) -> { left.addAll(right); return left; };
    private Function<HashSet<Word>, HashSet<Word>> finisher = words -> words;

    private Word saveOrGet(String str){
        Word w = wordRepository.findOneByWordStr(str).orElseGet(() -> wordRepository.save(new Word(str)));
        return w;
    }

    public Collector<String, HashSet<Word>, HashSet<Word>> getCollector(){
        return new CollectorImpl<>(supplier, accumulator, combiner, finisher, Collections.emptySet());
    }

    private EnumSet<Collector.Characteristics> getDefParallelCollection(){
        return EnumSet.of(
                Collector.Characteristics.CONCURRENT,
                Collector.Characteristics.IDENTITY_FINISH);
    }
}
