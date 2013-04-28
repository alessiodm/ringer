package org.alessiodm.ringer.service;

import org.alessiodm.ringer.domain.User;
import org.springframework.stereotype.Service;

/**
 * Not a very useful service, simply introducing some structure to the code
 * and transactional services.
 * 
 * @author alessio
 */
@Service
public interface UserService {

    public User getUserById(Long id);    
    public User getUserDetails(String username);
    public User registerUser(String username, String password);   
    public int deleteUser(User u);
    
}
