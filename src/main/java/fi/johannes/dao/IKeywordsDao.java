package fi.johannes.dao;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.entity.Keywords;

public interface IKeywordsDao extends CrudRepository<Keywords, Long> {

	// TODO Evaluate is this pointless
}
