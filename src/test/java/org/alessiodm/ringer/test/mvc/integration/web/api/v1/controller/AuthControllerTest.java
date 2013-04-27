package org.alessiodm.ringer.test.mvc.integration.web.api.v1.controller;

import org.alessiodm.ringer.test.unit.web.api.v1.mock.MockAuthService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest extends AbstractMvcIntegrationControllerTest {
    
    @Autowired
    MockAuthService authService;
    
    @Test
    public void testTokenInvalidation() throws Exception {
        authService.setValidateUser(10);
        this.mvc.perform(get("/api/v1/secure/auth/invalidateToken").param("token", "123345")).andExpect(status().is(200));
    }
    
    @Test
    public void testTokenInvalidationTokenNotPassed() throws Exception {
        authService.setValidateUser(null);
        this.mvc.perform(get("/api/v1/secure/auth/invalidateToken")).andExpect(status().is(401));
    }
}
