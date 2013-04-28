package org.alessiodm.ringer.domain.repository;

import org.alessiodm.ringer.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository {
    
    public int createRelation(User u1, User u2);
    public int deleteRelation(User u1, User u2);
    
}
