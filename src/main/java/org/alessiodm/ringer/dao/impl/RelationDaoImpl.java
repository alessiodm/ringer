package org.alessiodm.ringer.dao.impl;

import java.util.List;
import org.alessiodm.ringer.dao.RelationDao;
import org.alessiodm.ringer.model.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class RelationDaoImpl extends NamedParameterJdbcDaoSupport implements RelationDao {

    @Override
    public int createRelation(Long followerId, Long followedId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int deleteRelation(Long followerId, Long followedId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> listFollowers(Long followedId, int page, int perPage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> listFollowing(Long followerId, int page, int perPage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
