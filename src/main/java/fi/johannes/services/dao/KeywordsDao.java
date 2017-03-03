package fi.johannes.services.dao;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.models.Keywords;

public interface KeywordsDao extends CrudRepository<Keywords, Long> {

	// TODO Evaluate is this pointless
}
