package org.alessiodm.ringer.test.unit.dao.impl;

import java.util.List;
import org.alessiodm.ringer.dao.RelationDao;
import org.alessiodm.ringer.model.User;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RelationDaoImplTest extends AbstractDaoTest {
    @Autowired
    private RelationDao relationDao;
    
    @Test
    public void testListFollowers(){
        List<User> fwers = relationDao.listFollowers(1L, 0, 50);
        assertEquals("Number of follower unexpected", 3, fwers.size());
        // Remeber, result is ordered by id...
        assertEquals("Follower unexpected 2", 2L, fwers.get(0).getId().longValue());
        assertEquals("Follower unexpected 3", 3L, fwers.get(1).getId().longValue());
        assertEquals("Follower unexpected 5", 5L, fwers.get(2).getId().longValue());
    }
    
    @Test
    public void testListFollowersPaginated(){
        List<User> fwers = relationDao.listFollowers(1L, 0, 2);
        assertEquals("Number of follower unexpected", 2, fwers.size());
        // Remeber, result is ordered by id...
        assertEquals("Follower unexpected 2", 2L, fwers.get(0).getId().longValue());
        assertEquals("Follower unexpected 3", 3L, fwers.get(1).getId().longValue());
    }
    
    @Test
    public void testListFollowing(){
        List<User> fwing = relationDao.listFollowing(1L, 0, 50);
        assertEquals("Number of following unexpected", 3, fwing.size());
        // Remeber, result is ordered by id...
        assertEquals("Following unexpected 2", 2L, fwing.get(0).getId().longValue());
        assertEquals("Following unexpected 3", 3L, fwing.get(1).getId().longValue());
        assertEquals("Following unexpected 4", 4L, fwing.get(2).getId().longValue());
    }
    
    @Test
    public void testListFollowingPaginated(){
        List<User> fwing = relationDao.listFollowing(1L, 1, 2);
        assertEquals("Number of following unexpected", 1, fwing.size());
        // Remeber, result is ordered by id...
        assertEquals("Following unexpected 2", 4L, fwing.get(0).getId().longValue());
    }
    
    @Test
    public void testCreateRelation(){
        relationDao.createRelation(4L, 5L);
        assertEquals("Expected different follower", 4L, relationDao.listFollowers(5L, 0, 1).get(0).getId().longValue());
    }
    
    @Test(expected=RuntimeException.class)
    public void testCreateRelationAlreadyExists(){
        relationDao.createRelation(1L, 2L);
    }
    
    @Test
    public void testDeleteRelation(){
        relationDao.deleteRelation(1L, 2L);
        relationDao.deleteRelation(1L, 3L);
        relationDao.deleteRelation(1L, 4L);
        List<User> fwing = relationDao.listFollowing(1L, 0, 100);
        assertEquals("Expected no following", 0, fwing.size());
    }
    
    @Test
    public void testDeleteRelationThatDoesntExist(){
        assertTrue("Expecting no deletion", relationDao.deleteRelation(1L, 5L) == 0);
    }
}
