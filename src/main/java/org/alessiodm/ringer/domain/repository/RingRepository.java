package org.alessiodm.ringer.domain.repository;

import java.util.List;
import org.alessiodm.ringer.domain.Ring;
import org.alessiodm.ringer.domain.User;

public interface RingRepository {
    
    public Ring findRingById(Long ringId);
    public Ring createRing(User u, String content);
    public int deleteRing(Ring r);
    public List<Ring> getRingsList(User u, String keyword, int page, int perPage);
    
}
