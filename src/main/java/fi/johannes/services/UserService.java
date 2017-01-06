package fi.johannes.services;

import fi.johannes.dao.IUserDao;
import fi.johannes.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	//TODO Search by email
    @Autowired
    IUserDao userDao;

    public UserService() {

    }

    public User findByUsername(String name){
        return userDao.findByLogin(name);
    }

    // TODO Convert Principal to user



}
