package org.alessiodm.ringer.test.mvc.integration.web.api.v1.controller;

import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class UserControllerTest extends AbstractMvcIntegrationControllerTest {
    
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
                 .andExpect(status().is(HttpStatus.CONFLICT.value()));
    }
}
