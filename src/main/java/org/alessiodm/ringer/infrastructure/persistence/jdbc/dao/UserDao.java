package org.alessiodm.ringer.infrastructure.persistence.jdbc.dao;

import org.alessiodm.ringer.domain.User;

public interface UserDao {
    
    public User findById(Long id);
    public User findByUsername(String username);
    public User lookupUserByCredentials(String username, String password);
    public User createUser(String username, String password);
    public int deleteUser(Long id);
    
}
