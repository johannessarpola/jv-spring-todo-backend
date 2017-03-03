package fi.johannes.services.dao;

import fi.johannes.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * johanness on 03/03/2017.
 */

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
}
