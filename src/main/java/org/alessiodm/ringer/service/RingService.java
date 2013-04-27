package org.alessiodm.ringer.service;

import org.alessiodm.ringer.dao.RingDao;
import org.alessiodm.ringer.model.Ring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RingService {

    @Autowired
    private RingDao ringDao;
    
    @Transactional
    public Ring showRing(Long ringId){
        return ringDao.findById(ringId);
    }
    
}
