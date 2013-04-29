package org.alessiodm.ringer.service.impl;

import java.util.List;
import org.alessiodm.ringer.domain.model.Ring;
import org.alessiodm.ringer.domain.model.handling.RingerException;
import org.alessiodm.ringer.domain.model.User;
import org.alessiodm.ringer.domain.repository.RingRepository;
import org.alessiodm.ringer.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RingServiceImpl implements RingService {

    private @Autowired RingRepository ringRepository;
    
    @Transactional
    @Override
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
    @Override
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
    @Override
    public int deleteUserRing(Ring r, User u){
        if (!r.belongsTo(u)){
            throw RingerException.NOT_USERS_RING;
        }
        return ringRepository.deleteRing(r);
    }
    
    @Transactional
    @Override
    public List<Ring> getRingsList(User u, String keyword, int page, int perPage){
        return ringRepository.getRingsList(u, keyword, page, perPage);
    }
}
