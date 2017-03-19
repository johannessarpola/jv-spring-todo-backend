package fi.johannes.services.repositories;

import fi.johannes.dto.UserCreateForm;
import fi.johannes.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findOneByEmail(String email);
	Optional<User> findOneByLogin(String login);
	Optional<User> findOneById(long id);
}
