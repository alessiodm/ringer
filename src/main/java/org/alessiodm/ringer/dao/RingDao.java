package org.alessiodm.ringer.dao;

import java.util.List;
import org.alessiodm.ringer.model.Ring;

/**
 *
 * @author alessio
 */
public interface RingDao {
    
    public Long createRing(Long userId, String content);
    public Ring findById(Long ringId);
    public boolean deleteRing(Long ringIg);
    public List<Ring> listRings(Long userId, String keyword);
    
}
