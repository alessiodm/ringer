package org.alessiodm.ringer.domain.repository;

import java.util.List;
import org.alessiodm.ringer.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    
    public User findUserById(Long id);
    public User findUserByUsername(String username);
    public int deleteUser(User user);
    public User createUser(String username, String password);
    public List<User> getFollowers(User u, int page, int perPage);
    public List<User> getFollowing(User u, int page, int perPage);
    public int createRelation(User u1, User u2);
    public int deleteRelation(User u1, User u2);
    public boolean follows(User u1, User u2);

}
