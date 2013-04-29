package org.alessiodm.ringer.test.unit.interfaces.web.api.v1.controller;

import java.util.HashMap;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.UserDao;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.domain.RingerException;
import org.alessiodm.ringer.domain.repository.UserRepository;
import org.alessiodm.ringer.interfaces.web.api.v1.controller.RelationController;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.ListOfUserDetail;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.SimpleResult;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RelationControllerTest extends AbstractUnitControllerTest {
    
    private final static Logger log = Logger.getLogger(RelationControllerTest.class);
    
    @Autowired
    private RelationController relController;
    
    @Autowired
    private UserRepository userRepository;
    
    private User u1;
    
    @Before
    public void setUp(){
        u1 = userRepository.findUserById(1L);
    }
    
    @Test
    public void testGetFollowers(){
        ListOfUserDetail f = relController.followersList(0, 2, u1);
        assertEquals(2, f.getUsers().size());
        assertEquals(2L, f.getUsers().get(0).getId().longValue());
        assertEquals(3L, f.getUsers().get(1).getId().longValue());
        
        f = relController.followersList(1, 2, u1);
        assertEquals(1, f.getUsers().size());
        assertEquals(5L, f.getUsers().get(0).getId().longValue());
    }
    
    @Test
    public void testGetFollowing(){
        ListOfUserDetail f = relController.followingList(0, 2, u1);
        assertEquals(2, f.getUsers().size());
        assertEquals(2L, f.getUsers().get(0).getId().longValue());
        assertEquals(3L, f.getUsers().get(1).getId().longValue());
        
        f = relController.followingList(1, 2, u1);
        assertEquals(1, f.getUsers().size());
        assertEquals(4L, f.getUsers().get(0).getId().longValue());
    }
    
    @Test 
    public void testFollow(){
        assertEquals(SimpleResult.ResultType.OKEY, relController.follow(5L, u1).getResult());
        Integer i = jt.queryForObject("select count(*) from T_RELATION where follower_id = 1", new HashMap(), Integer.class);
        assertEquals("Number of rings expected different", 4, i.intValue());
    }
    
    @Test(expected=RingerException.class)
    public void testFollowAlreadyFollow(){
        assertEquals(SimpleResult.ResultType.ERROR, relController.follow(2L, u1).getResult());
    }
    
    @Test 
    public void testUnfollow(){
        assertEquals(SimpleResult.ResultType.OKEY, relController.unfollow(4L, u1).getResult());
        Integer i = jt.queryForObject("select count(*) from T_RELATION where follower_id = 1", new HashMap(), Integer.class);
        assertEquals("Number of rings expected different", 2, i.intValue());
    }
    
    @Test(expected=RingerException.class)
    public void testUnfollowNotFollowing(){
        relController.unfollow(5L, u1).getResult();
    }
}
