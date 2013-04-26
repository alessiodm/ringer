package org.alessiodm.ringer.dao.impl;

import org.alessiodm.ringer.dao.UserDao;
import org.alessiodm.ringer.model.User;

public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    @Override
    public User findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User createUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteUser(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
