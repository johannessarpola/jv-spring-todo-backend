package fi.johannes.services.repositories;

import fi.johannes.dto.UserCreateForm;
import fi.johannes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findOneByEmail(String email);
	User findOneByLogin(String login);
	User findOneById(long id);
}
