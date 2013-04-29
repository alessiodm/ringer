package org.alessiodm.ringer.service;

import org.alessiodm.ringer.domain.model.User;

/**
 * Not a very useful service, simply introducing some structure to the code
 * and transactional services.
 * 
 * @author alessio
 */
public interface UserService {

    public User getUserById(Long id);    
    public User getUserDetails(String username);
    public User registerUser(String username, String password);   
    public int deleteUser(User u);
    
}
