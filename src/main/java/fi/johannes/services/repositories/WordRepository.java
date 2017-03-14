package fi.johannes.services.repositories;

import fi.johannes.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * johanness on 14/03/2017.
 */
public interface WordRepository extends JpaRepository<Word, Long> {

    Word findOneByWordStr(String wordStr);
}
