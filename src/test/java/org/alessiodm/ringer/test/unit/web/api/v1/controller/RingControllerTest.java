package org.alessiodm.ringer.test.unit.web.api.v1.controller;

import java.util.HashMap;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.UserDao;
import org.alessiodm.ringer.domain.Ring;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.util.RingerAPIException;
import org.alessiodm.ringer.web.api.v1.controller.RingController;
import org.alessiodm.ringer.web.api.v1.dto.ListOfRings;
import org.alessiodm.ringer.web.api.v1.dto.RingContent;
import org.alessiodm.ringer.web.api.v1.dto.SimpleResult;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RingControllerTest extends AbstractUnitControllerTest {
    
    private final static Logger log = Logger.getLogger(RingControllerTest.class);
    
    @Autowired
    private RingController ringController;
    
    @Autowired
    private UserDao userDao;
    
    private User u1;
    
    @Before
    public void setUp(){
        u1 = userDao.findById(1L);
    }
    
    @Test
    public void testRingListWithoutKeyword(){
        ListOfRings list = ringController.listRings(null, null, null, u1);
        assertEquals(10, list.getRings().size());
        for (int i = 0; i < 10; i++){
            assertEquals(12 - i, list.getRings().get(i).getId().longValue());
        }
    }
    
    @Test
    public void testShowRing(){
        Ring ring = ringController.showRing(2L, u1);
        assertEquals("Ring 2 User 1", ring.getContent());
    }
    
    @Test(expected = RingerAPIException.class)
    public void testDeleteRingDoesntExist(){
        ringController.deleteRing(72L, u1);
    }
    
    @Test(expected = RingerAPIException.class)
    public void testDeleteRingNotBelongingToUser(){
        ringController.deleteRing(7L, u1);
    }
    
    @Test
    public void testDeleteRing(){
        // Ring exists and belongs to user
        assertEquals(SimpleResult.ResultType.OKEY, ringController.deleteRing(1L, u1).getResult());
        Integer i = jt.queryForObject("select count(*) from T_RING where user_id = 1", new HashMap(), Integer.class);
        assertEquals("Number of rings expected different", 2, i.intValue());
    }
    
    @Test
    public void testCreateRing(){
        String content = "My new content";
        // Ring exists and belongs to user
        assertEquals(content, ringController.createRing(new RingContent(content), u1).getContent());
        Integer i = jt.queryForObject("select count(*) from T_RING where user_id = 1", new HashMap(), Integer.class);
        assertEquals("Number of rings expected different", 4, i.intValue());
    }
}
