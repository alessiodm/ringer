package org.alessiodm.ringer.infrastructure.persistence.jdbc.dao;

import java.util.List;
import org.alessiodm.ringer.domain.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="prototype")
public interface RelationDao {
    
    public int createRelation(Long followerId, Long followedId);
    public int deleteRelation(Long followerId, Long followedId);
    public List<User> listFollowers(Long followedId, int page, int perPage);
    public List<User> listFollowing(Long followerId, int page, int perPage);
    public boolean follows(Long followerId, Long followedId);
    
}
