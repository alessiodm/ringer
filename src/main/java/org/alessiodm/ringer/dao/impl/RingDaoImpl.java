package org.alessiodm.ringer.dao.impl;

import java.util.List;
import org.alessiodm.ringer.dao.RingDao;
import org.alessiodm.ringer.model.Ring;

public class RingDaoImpl extends AbstractDaoImpl implements RingDao {

    @Override
    public Long createRing(Long userId, String content) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Ring findById(Long ringId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteRing(Long ringIg) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Ring> listRings(Long userId, String keyword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
