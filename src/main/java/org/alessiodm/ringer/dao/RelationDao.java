package org.alessiodm.ringer.dao;

import java.util.List;
import org.alessiodm.ringer.model.User;

/**
 *
 * @author alessio
 */
public interface RelationDao {
    
    public int createRelation(Long followerId, Long followedId);
    public int deleteRelation(Long followerId, Long followedId);
    public List<User> listFollowers(Long followedId, int page, int perPage);
    public List<User> listFollowing(Long followerId, int page, int perPage);

}
