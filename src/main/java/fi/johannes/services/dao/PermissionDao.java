package fi.johannes.services.dao;

import fi.johannes.models.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * johanness on 03/03/2017.
 */
@Repository
public interface PermissionDao extends CrudRepository<Permission, Long>{
}
