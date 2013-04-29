package org.alessiodm.ringer.infrastructure.persistence.jdbc;

import java.util.List;
import org.alessiodm.ringer.domain.model.User;
import org.alessiodm.ringer.domain.repository.UserRepository;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RelationDao;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RingDao;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryJdbc implements UserRepository {

    protected @Autowired UserDao userDao;
    protected @Autowired RelationDao relationDao;
    protected @Autowired RingDao ringDao;
    
    @Override
    public User findUserById(Long id) {
        User u = userDao.findById(id);
        return u;
    }

    @Override
    public User findUserByUsername(String username) {
        User u = userDao.findByUsername(username);
        return u;
    }

    @Override
    @Transactional public int deleteUser(User user) {
        ringDao.deleteAllUserRingContents(user.getId());
        ringDao.deleteAllUserRings(user.getId());
        relationDao.deleteAllUserRelation(user.getId());
        return userDao.deleteUser(user.getId());
    }

    @Override
    public List<User> getFollowers(User u, int page, int perPage) {
        List<User> users = relationDao.listFollowers(u.getId(), page, perPage);
        return users;
    }

    @Override
    public List<User> getFollowing(User u, int page, int perPage) {
        List<User> users = relationDao.listFollowing(u.getId(), page, perPage);
        return users;
    }

    @Override
    @Transactional public User createUser(String username, String password) {
        User u = userDao.createUser(username, password);
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

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userDao.lookupUserByCredentials(username, password);
    }
}
