package org.alessiodm.ringer.dao;

import org.alessiodm.ringer.model.User;

public interface UserDao {
    
    public User findById(Long id);
    public User findByUsername(String username);
    public User createUser(String username, String password);
    public int deleteUser(Long id);
    
}
