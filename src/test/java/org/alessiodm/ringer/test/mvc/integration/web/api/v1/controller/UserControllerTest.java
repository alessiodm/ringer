package org.alessiodm.ringer.test.mvc.integration.web.api.v1.controller;

import java.util.HashMap;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    
}
