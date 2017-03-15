package fi.johannes.services.repositories;

import fi.johannes.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * johanness on 14/03/2017.
 */
public interface WordRepository extends JpaRepository<Word, Long> {

    Optional<Word> findOneByWordStr(String wordStr);
}
