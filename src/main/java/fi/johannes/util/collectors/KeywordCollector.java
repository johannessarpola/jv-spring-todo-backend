package fi.johannes.util.collectors;

import fi.johannes.models.Word;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * johanness on 15/03/2017.
 */
public class KeywordCollector implements Collector<String, Set<Word>, Word> {
    @Override
    public Supplier<Set<Word>> supplier() {
        return () -> new HashSet<>();
    }

    @Override
    public BiConsumer<Set<Word>, String> accumulator() {
        return (set, str) -> set.add(new Word(str));
    }

    @Override
    public BinaryOperator<Set<Word>> combiner() {
        return (a, b) -> {
            a.addAll(b);
            return a;
        };
    }

    @Override
    public Function<Set<Word>, Word> finisher() {
        return null;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
