package org.alessiodm.ringer.test.unit.infrastructure.persistence.jdbc.dao.impl;

import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.UserDao;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.util.MD5Util;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImplTest extends AbstractDaoTest {
    
    private final static Logger log = Logger.getLogger(UserDaoImplTest.class);
    
    @Autowired
    private UserDao userDao;
    
    @Test
    public void testDeleteUser(){
        userDao.deleteUser(1L);
        userDao.deleteUser(2L);
        userDao.deleteUser(3L);
        userDao.deleteUser(4L);
        int val = userDao.deleteUser(5L);
        assertEquals("Deleted itemes different", 1, val);
        User user = userDao.findById(1L);
        assertNull("User not deleted", user);
    }
    
    @Test
    public void testFindUserById(){
        User user = userDao.findById(1L);
        assertNotNull("No user found", user);
        assertEquals("Username different", "user1", user.getUsername());
    }
    
    @Test
    public void testFindUserByUsername(){
        User user = userDao.findByUsername("user3");
        assertNotNull("No user found", user);
        assertEquals("Username different", "user3", user.getUsername());
    }
    
    @Test
    public void testLookupByCredentials(){
        User user = userDao.lookupUserByCredentials("user3", "user3");
        assertNotNull("No user found", user);
        assertEquals("Username different", "user3", user.getUsername());
    }
    
    @Test
    public void testInsertUser(){
        User user = userDao.createUser("xuser", "xpassword");
        assertNotNull("User null", user);
        assertEquals("Pasword not expected", MD5Util.encrypt("xpassword", "xuser"), user.getEncPassword());
        User user1 = userDao.findByUsername("xuser");
        assertEquals("Users are not the same", user, user1);
    }
}
