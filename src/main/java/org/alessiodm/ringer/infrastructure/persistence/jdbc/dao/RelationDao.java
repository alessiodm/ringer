package org.alessiodm.ringer.infrastructure.persistence.jdbc.dao;

import java.util.List;
import org.alessiodm.ringer.domain.model.User;

public interface RelationDao {
    
    public int createRelation(Long followerId, Long followedId);
    public int deleteRelation(Long followerId, Long followedId);
    public int deleteAllUserRelation(Long userId);
    public List<User> listFollowers(Long followedId, int page, int perPage);
    public List<User> listFollowing(Long followerId, int page, int perPage);
    public boolean follows(Long followerId, Long followedId);
    
}
