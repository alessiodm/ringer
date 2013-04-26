package org.alessiodm.ringer.test.dao.impl;

import org.alessiodm.ringer.dao.UserDao;
import org.alessiodm.ringer.model.User;
import org.alessiodm.ringer.test.AbstractRingerSpringTest;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImplTest extends AbstractRingerSpringTest {
    
    @Autowired
    private UserDao userDao;
    
    @Test
    public void testFindUserById(){
        User user = userDao.findById(1L);
        assertNotNull("No user found", user);
        assertEquals("Username different", "user1", user.getUsername());
    }
    
}
