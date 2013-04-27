package org.alessiodm.ringer.service;

import java.util.List;
import org.alessiodm.ringer.dao.RingDao;
import org.alessiodm.ringer.domain.Ring;
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
    
    @Transactional
    public Ring createRing(String content, Long userId){
        return ringDao.createRing(userId, content);
    }
    
    @Transactional
    public int deleteRing(Long ringId, Long userId){
        return ringDao.deleteRing(ringId, userId);
    }
    
    @Transactional
    public List<Ring> getRingsList(Long userId, String keyword, int page, int perPage){
        return ringDao.listRings(userId, keyword, page, perPage);
    }
}
