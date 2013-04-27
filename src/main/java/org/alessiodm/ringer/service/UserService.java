package org.alessiodm.ringer.service;

import org.alessiodm.ringer.dao.UserDao;
import org.alessiodm.ringer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Not a very useful service, simply introducing some structure to the code
 * and transactional services.
 * 
 * @author alessio
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    
    @Transactional
    public User registerUser(String username, String password){
        return userDao.createUser(username, password);
    }
    
    @Transactional
    public int deleteUser(Long id){
        return userDao.deleteUser(id);
    }
}
