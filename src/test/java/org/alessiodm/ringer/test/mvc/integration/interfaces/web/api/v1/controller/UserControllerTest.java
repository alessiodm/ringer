package org.alessiodm.ringer.test.mvc.integration.interfaces.web.api.v1.controller;

import java.util.HashMap;
import org.alessiodm.ringer.test.unit.service.impl.mock.MockAuthService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest extends AbstractMvcIntegrationControllerTest {
    
    @Autowired
    MockAuthService mockAuthService;
    
    @Before
    @Override
    public void setUp(){
        super.setUp();
        mockAuthService.reset();
    }
    
    @Test
    public void testUserRegistration() throws Exception {
        String req = "{ \"username\": \"alessio\", \"password\": \"pass\" }";
        
        // Vefiry auth token
        this.mvc.perform(
                    post("/api/v1/user/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(req)
                 )
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.token").exists());
        
        // Verify db
        Integer i = jt.queryForObject("select count(*) from T_USER", new HashMap(), Integer.class);
        assertEquals("Number of user expected different", 6, i.intValue());
    }
    
    @Test
    public void testUserInvalidRegistration() throws Exception {
        String req = "{ \"username\": \"user1\", \"password\": \"pass\" }";
        this.mvc.perform(
                    post("/api/v1/user/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(req)
                 )
                 .andExpect(status().is(HttpStatus.OK.value()))
                 .andExpect(jsonPath("$.result").value("ERROR"));;
    }
}
