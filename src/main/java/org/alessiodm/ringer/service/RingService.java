package org.alessiodm.ringer.service;

import java.util.List;
import org.alessiodm.ringer.domain.Ring;
import org.alessiodm.ringer.domain.RingerException;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.domain.repository.RingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RingService {

    private @Autowired RingRepository ringRepository;
    
    @Transactional
    public Ring showRingDetails(Long ringId){
        return ringRepository.findRingById(ringId);
    }
    
    /**
     * Creation of a Ring and its content.
     * 
     * @param content Ring content
     * @param userId  User ringing
     * @return The Ring
     */
    @Transactional
    public Ring createRing(User u, String content){
        return ringRepository.createRing(u, content);
    }
    
    
    /**
     * Delete a ring only if it belongs to the user.
     * 
     * @param ringId Ring to delete
     * @param userId User who should ringed out the ring
     * @return Delete result
     */
    @Transactional
    public int deleteUserRing(Ring r, User u){
        if (!r.belongsTo(u)){
            throw RingerException.NOT_USERS_RING;
        }
        return ringRepository.deleteRing(r);
    }
    
    @Transactional
    public List<Ring> getRingsList(User u, String keyword, int page, int perPage){
        return ringRepository.getRingsList(u, keyword, page, perPage);
    }
}
