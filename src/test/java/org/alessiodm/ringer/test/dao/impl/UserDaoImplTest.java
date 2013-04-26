package org.alessiodm.ringer.test.dao.impl;

import org.alessiodm.ringer.dao.UserDao;
import org.alessiodm.ringer.model.User;
import org.alessiodm.ringer.test.AbstractRingerSpringTest;
import org.alessiodm.ringer.util.MD5Util;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImplTest extends AbstractRingerSpringTest {
    
    private final static Logger log = Logger.getLogger(UserDaoImplTest.class);
    
    @Autowired
    private UserDao userDao;
    
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
    public void testDeleteUser(){
        int val = userDao.deleteUser(1L);
        assertEquals("Deleted itemes different", 1, val);
        User user = userDao.findById(1L);
        assertNull("User not deleted", user);
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
