package org.alessiodm.ringer.dao;

import org.alessiodm.ringer.model.User;

public interface UserDao {
    
    public User findById(Long id);
    public User createUser(String username, String password);
    public boolean deleteUser(Long id);
    
}
