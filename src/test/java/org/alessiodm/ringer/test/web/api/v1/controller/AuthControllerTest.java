package org.alessiodm.ringer.test.web.api.v1.controller;

import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest extends AbstractControllerTest {
    
    @Test
    public void testTokenInvalidation() throws Exception {
        this.mvc.perform(get("/api/v1/auth/invalidateToken").param("token", "123345")).andExpect(status().is(200));
    }
    
    @Test
    public void testTokenInvalidationTokenNotPassed() throws Exception {
        this.mvc.perform(get("/api/v1/auth/invalidateToken")).andExpect(status().is(400));
    }
}
