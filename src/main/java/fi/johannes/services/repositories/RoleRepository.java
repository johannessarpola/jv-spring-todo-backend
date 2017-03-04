package fi.johannes.services.repositories;

import fi.johannes.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * johanness on 03/03/2017.
 */

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
