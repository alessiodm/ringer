package org.alessiodm.ringer.dao;

import java.util.List;
import org.alessiodm.ringer.model.Ring;

/**
 *
 * @author alessio
 */
public interface RingDao {
    
    public Ring createRing(Long userId, String content);
    public Ring findById(Long ringId);
    public int deleteRing(Long ringId);
    public List<Ring> listRings(Long userId, String keyword, int page, int perPage);
    
}
