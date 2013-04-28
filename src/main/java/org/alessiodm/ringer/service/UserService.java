package org.alessiodm.ringer.service;

import java.util.List;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RelationDao;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.UserDao;
import org.alessiodm.ringer.util.RingerAPIException;
import org.alessiodm.ringer.web.api.v1.auth.AuthService;
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
    private AuthService authService;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired 
    private RelationDao relationDao;
    
    // Also this
    @Transactional
    public User getUserDetails(String username){
        User u = userDao.findByUsername(username);
        if (u == null){
            throw RingerAPIException.USERNAME_NOT_AVAILABLE;
        }
        return u;
    }
    
    // Only method that remains
    @Transactional
    public User registerUser(String username, String password){
        User u = userDao.findByUsername(username);
        if (u != null){
            throw RingerAPIException.USERNAME_NOT_AVAILABLE;
        }
        return userDao.createUser(username, password);
    }
    
    @Transactional
    public int deleteUser(Long id){
        String token = authService.getUserToken(id);
        if (token != null){
            authService.retireToken(token);
        }
        
        return userDao.deleteUser(id);
    }
     
    @Transactional
    public int startFollowing(Long id, Long newFollowed){
        if (id == newFollowed || relationDao.follows(id, newFollowed)){
            throw RingerAPIException.RELATION_ALREADY_EXISTS;
        }
        return relationDao.createRelation(id, newFollowed);
    }
    
    @Transactional
    public int stopFollowing(Long id, Long followed){
        return relationDao.deleteRelation(id, followed);
    }
    
    @Transactional
    public List<User> getFollowers(Long userId, int page, int perPage){
        return relationDao.listFollowers(userId, page, perPage);
    }
    
    @Transactional
    public List<User> getFollowing(Long userId, int page, int perPage){
        return relationDao.listFollowing(userId, page, perPage);
    }
}
