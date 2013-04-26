package org.alessiodm.ringer.dao.impl;

import java.util.List;
import org.alessiodm.ringer.dao.RingDao;
import org.alessiodm.ringer.model.Ring;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class RingDaoImpl extends NamedParameterJdbcDaoSupport implements RingDao {

    @Override
    public Long createRing(Long userId, String content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Ring findById(Long ringId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int deleteRing(Long ringIg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Ring> listRings(Long userId, String keyword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
