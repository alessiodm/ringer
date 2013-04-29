package org.alessiodm.ringer.service.impl;

import org.alessiodm.ringer.domain.model.handling.RingerException;
import org.alessiodm.ringer.domain.model.User;
import org.alessiodm.ringer.domain.repository.UserRepository;
import org.alessiodm.ringer.service.*;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    @Override
    public User getUserById(Long id){
        User u = userRepository.findUserById(id);
        return u;
    }
    
    @Transactional
    @Override
    public User getUserDetails(String username){
        User u = userRepository.findUserByUsername(username);
        if (u == null){
            throw RingerException.USERNAME_NOT_AVAILABLE;
        }
        return u;
    }
    
    @Transactional
    @Override
    public User registerUser(String username, String password){
        User u = userRepository.findUserByUsername(username);
        if (u != null){
            throw RingerException.USERNAME_NOT_AVAILABLE;
        }
        return userRepository.createUser(username, password);
    }
    
    @Transactional
    @Override
    public int deleteUser(User u){
        String token = authService.getUserToken(u.getId());
        if (token != null){
            authService.retireToken(token);
        }
        
        return userRepository.deleteUser(u);
    }
   
}
