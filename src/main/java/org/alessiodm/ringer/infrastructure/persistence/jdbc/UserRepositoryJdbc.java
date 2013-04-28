package org.alessiodm.ringer.infrastructure.persistence.jdbc;

import java.util.List;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.domain.repository.UserRepository;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RelationDao;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.transaction.annotation.Transactional;

public class UserRepositoryJdbc implements UserRepository {

    private @Autowired AutowireCapableBeanFactory beanFactory;
    
    protected @Autowired UserDao userDao;
    protected @Autowired RelationDao relationDao;
    
    @Override
    public User findUserById(Long id) {
        User u = userDao.findById(id);
        beanFactory.autowireBean(u);
        return u;
    }

    @Override
    public User findUserByUsername(String username) {
        User u = userDao.findByUsername(username);
        beanFactory.autowireBean(u);
        return u;
    }

    @Override
    @Transactional public int deleteUser(User user) {
        return userDao.deleteUser(user.getId());
    }

    @Override
    public List<User> getFollowers(User u, int page, int perPage) {
        List<User> users = relationDao.listFollowers(u.getId(), page, perPage);
        for (User _u : users){
            beanFactory.autowireBean(_u);
        }
        return users;
    }

    @Override
    public List<User> getFollowing(User u, int page, int perPage) {
        List<User> users = relationDao.listFollowing(u.getId(), page, perPage);
        for (User _u : users){
            beanFactory.autowireBean(_u);
        }
        return users;
    }

    @Override
    @Transactional public User createUser(String username, String password) {
        User u = userDao.createUser(username, password);
        beanFactory.autowireBean(u);
        return u;
    }
    
    @Override
    @Transactional public int createRelation(User u1, User u2) {
        return relationDao.createRelation(u1.getId(), u2.getId());
    }

    @Override
    @Transactional public int deleteRelation(User u1, User u2) {
        return relationDao.deleteRelation(u1.getId(), u2.getId());
    }
    
    @Override
    public boolean follows(User u1, User u2){
        return relationDao.follows(u1.getId(), u2.getId());
    }
}
