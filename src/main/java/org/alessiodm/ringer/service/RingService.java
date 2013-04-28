package org.alessiodm.ringer.service;

import java.util.List;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RingDao;
import org.alessiodm.ringer.domain.Ring;
import org.alessiodm.ringer.util.RingerAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RingService {

    @Autowired
    private RingDao ringDao;
    
    @Transactional
    public Ring showRingDetails(Long ringId){
        return ringDao.findById(ringId);
    }
    
    /**
     * Creation of a Ring and its content.
     * 
     * @param content Ring content
     * @param userId  User ringing
     * @return The Ring
     */
    @Transactional
    public Ring createRing(String content, Long userId){
        Ring r =  ringDao.createRing(userId);
        ringDao.createRingContent(r.getId(), content);
        r.setContent(content);
        return r;
    }
    
    
    /**
     * Delete a ring only if it belongs to the user.
     * 
     * @param ringId Ring to delete
     * @param userId User who should ringed out the ring
     * @return Delete result
     */
    @Transactional
    public int deleteUserRing(Long ringId, Long userId){
        if (!ringDao.belongsToUser(ringId, userId)){
            throw RingerAPIException.NOT_USERS_RING;
        }
        ringDao.deleteRingContent(ringId);
        return ringDao.deleteRing(ringId);
    }
    
    @Transactional
    public List<Ring> getRingsList(Long userId, String keyword, int page, int perPage){
        return ringDao.listRings(userId, keyword, page, perPage);
    }
}
