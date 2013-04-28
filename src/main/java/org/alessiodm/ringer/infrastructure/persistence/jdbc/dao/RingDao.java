package org.alessiodm.ringer.infrastructure.persistence.jdbc.dao;

import java.util.List;
import org.alessiodm.ringer.domain.Ring;

public interface RingDao {
    
    public int createRingContent(Long ringId, String content);
    public Ring createRing(Long userId);
    public Ring findById(Long ringId);
    public int deleteRing(Long ringId);
    public int deleteRingContent(Long ringId);
    public boolean belongsToUser(Long ringId, Long userId);
    public List<Ring> listRings(Long userId, String keyword, int page, int perPage);
    
}
