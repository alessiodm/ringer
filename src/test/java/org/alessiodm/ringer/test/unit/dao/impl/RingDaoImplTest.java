package org.alessiodm.ringer.test.unit.dao.impl;

import java.util.List;
import org.alessiodm.ringer.dao.RingDao;
import org.alessiodm.ringer.model.Ring;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RingDaoImplTest extends AbstractDaoTest {
    
    @Autowired
    private RingDao ringDao;
    
    @Test 
    public void testFindRingById(){
        Ring ring = ringDao.findById(4L);
        assertNotNull("No user found", ring);
        assertEquals("Content not expected", "Ring 1 User 2", ring.getContent());
    }
    
    @Test
    public void testCreateRing(){
        final String content = "Try creating a new ring, yuppy!";
        Ring ring = ringDao.createRing(3L, content);
        assertNotNull("Ring null", ring);
        assertNotNull("Timestamp null", ring.getTimestamp());
        assertEquals("Content not expected", content, ring.getContent());
        assertEquals("User not expected", 3L, ring.getUserId().longValue());
        Ring ring1 = ringDao.findById(ring.getId());
        assertEquals("Rings are not the same", ring, ring1);
    }
    
    @Test 
    public void testDeleteRing(){
        ringDao.deleteRing(1L, 1L);
        Ring ring = ringDao.findById(1L);
        assertNull("Expected null", ring);
    }
    
    @Test 
    public void testListRingPaginated(){
        List<Ring> rings = ringDao.listRings(1L, null, 0, 100);
        assertEquals("Number of rings not expected", 12, rings.size());
        for (int i = 12; i > 0; i--){
            assertEquals("Order broken " + i, (long) i, rings.get(12 - i).getId().longValue());
        }
    }
    
    @Test 
    public void testListRingPaginated2(){
        List<Ring> rings = ringDao.listRings(1L, null, 1, 5);
        assertEquals("Number of rings not expected", 5, rings.size());
        for (int i = 5; i > 0; i--){
            int j = i + 2;
            assertEquals("Order broken " + j, (long) j, rings.get(5 - i).getId().longValue());
        }
    }
    
    @Test(expected=RuntimeException.class)
    public void testHSQLDBExceptionForListingRingWithKeyword(){
        ringDao.listRings(1L, "User 2", 0, 100);
    }
}
