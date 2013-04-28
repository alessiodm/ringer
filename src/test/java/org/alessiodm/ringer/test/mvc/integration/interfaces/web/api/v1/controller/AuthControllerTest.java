package org.alessiodm.ringer.test.mvc.integration.interfaces.web.api.v1.controller;

import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.test.unit.service.impl.mock.MockAuthService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthControllerTest extends AbstractMvcIntegrationControllerTest {
    
    @Autowired
    MockAuthService mockAuthService;
    
    @Before
    @Override
    public void setUp(){
        super.setUp();
        mockAuthService.reset();
    }
    
    @Test
    public void testTokenInvalidation() throws Exception {
        this.mvc.perform(get("/api/v1/secure/auth/invalidateToken")).andExpect(status().is(200));
    }
    
    @Test
    public void testTokenInvalidationTokenNotPassed() throws Exception {
        mockAuthService.setValidateUser(null);
        this.mvc.perform(get("/api/v1/secure/auth/invalidateToken")).andExpect(status().is(401));
    }
    
    @Test
    public void testGetToken() throws Exception {
        this.mvc.perform(get("/api/v1/auth/token?username=ale&password=pass")).andExpect(status().is(200));
    }
    
    @Test
    public void testGetTokenFailure() throws Exception {
        mockAuthService.setToken(null);
        this.mvc.perform(get("/api/v1/auth/token?username=ale&password=pass")).andExpect(status().is(401));
    }
}
