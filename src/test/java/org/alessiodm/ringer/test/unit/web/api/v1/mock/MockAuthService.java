package org.alessiodm.ringer.test.unit.web.api.v1.mock;

import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.web.api.v1.auth.AuthService;

public class MockAuthService implements AuthService {

    private User validateUser;
    private String token = "abcdef";
    
    @Override
    public String createTokenForUser(String username, String password) {
        return token;
    }

    @Override
    public void retireToken(String token) {
        
    }

    @Override
    public User validateToken(String token) {
        return validateUser;
    }
    
    public User getValidateUser() {
        return validateUser;
    }

    public void setValidateUser(User validateUser) {
        this.validateUser = validateUser;
    }

    @Override
    public String getUserToken(Long id) {
        return token;
    }
    
}
