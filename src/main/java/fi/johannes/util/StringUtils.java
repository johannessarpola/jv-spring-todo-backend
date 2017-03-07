package fi.johannes.util;

import java.util.Map;
import java.util.function.Predicate;

/**
 * johanness on 07/03/2017.
 */
public class StringUtils {

    static final Predicate<Map.Entry<?, String>> valueNotNullOrEmptyMapPredicate
    = e -> e.getValue() != null && !e.getValue().isEmpty();

    static final Predicate<String> valueNotNullOrEmptyStringsPredicate
    = e -> e != null && !e.isEmpty();


    public static Predicate<Map.Entry<?, String>> getValueNotNullOrEmptyMapPredicate() {
        return valueNotNullOrEmptyMapPredicate;
    }

    public static Predicate<String> getValueNotNullOrEmptyStringsPredicate() {
        return valueNotNullOrEmptyStringsPredicate;
    }
}
