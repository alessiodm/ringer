package org.alessiodm.ringer.infrastructure.persistence.jdbc;

import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.domain.repository.RelationRepository;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RelationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class RelationRepositoryJdbc implements RelationRepository {

    protected @Autowired RelationDao relationDao;
    
    @Override
    @Transactional public int createRelation(User u1, User u2) {
        return relationDao.createRelation(u1.getId(), u2.getId());
    }

    @Override
    @Transactional public int deleteRelation(User u1, User u2) {
        return relationDao.deleteRelation(u1.getId(), u2.getId());
    }
    
}
