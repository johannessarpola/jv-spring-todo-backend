package fi.johannes.dao;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.services.User;

public interface IUserDao extends CrudRepository<User, Long> {

}
