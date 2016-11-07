package fi.johannes.dao;

import org.springframework.data.repository.CrudRepository;

import fi.johannes.entity.User;

public interface IUserDao extends CrudRepository<User, Long> {

}
