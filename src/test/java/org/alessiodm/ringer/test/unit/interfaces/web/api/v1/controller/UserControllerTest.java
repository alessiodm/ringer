package org.alessiodm.ringer.test.unit.interfaces.web.api.v1.controller;

import java.util.HashMap;
import org.alessiodm.ringer.domain.model.User;
import org.alessiodm.ringer.interfaces.web.api.v1.controller.UserController;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.AuthToken;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.SimpleResult;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.UserCredentials;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTest extends AbstractUnitControllerTest {
    
    private final static Logger log = Logger.getLogger(UserControllerTest.class);
    
    @Autowired
    UserController userController;
    
    @Test
    public void testUserRegistration() {
        UserCredentials uc = new UserCredentials();
        uc.setUsername("alessio");
        uc.setPassword("pass");
        
        AuthToken token = userController.register(uc);
        assertNotNull(token);
        log.debug("TOKEN : " + token.getToken());
        // Verify db
        Integer i = jt.queryForObject("select count(*) from T_USER", new HashMap(), Integer.class);
        assertEquals("Number of user expected different", 6, i.intValue());
    }
    
    @Test
    public void testUserDeletion() {
        User user = new User();
        user.setId(1L);
        
        SimpleResult result = userController.destroy(user);
        assertEquals(result.getResult(), SimpleResult.ResultType.OKEY);
        Integer i = jt.queryForObject("select count(*) from T_USER", new HashMap(), Integer.class);
        assertEquals("Number of user expected different", 4, i.intValue());
    }
    
}
