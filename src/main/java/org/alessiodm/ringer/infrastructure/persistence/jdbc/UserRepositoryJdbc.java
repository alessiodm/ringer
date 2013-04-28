package org.alessiodm.ringer.infrastructure.persistence.jdbc;

import java.util.List;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.domain.repository.UserRepository;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RelationDao;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserRepositoryJdbc implements UserRepository {

    @Autowired protected UserDao userDao;
    @Autowired protected RelationDao relationDao;
    
    @Override
    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional public int deleteUser(User user) {
        return userDao.deleteUser(user.getId());
    }

    @Override
    public List<User> getFollowers(User u, int page, int perPage) {
        return relationDao.listFollowers(u.getId(), page, perPage);
    }

    @Override
    public List<User> getFollowing(User u, int page, int perPage) {
        return relationDao.listFollowing(u.getId(), page, perPage);
    }

    @Override
    @Transactional public User createUser(String username, String password) {
        return userDao.createUser(username, password);
    }
    
}
