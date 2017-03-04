package fi.johannes.services.repositories;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.models.Keywords;

public interface KeywordsRepository extends CrudRepository<Keywords, Long> {

	// TODO Evaluate is this pointless
}
