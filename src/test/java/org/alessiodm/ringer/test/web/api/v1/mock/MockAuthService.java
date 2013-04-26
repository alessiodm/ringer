package org.alessiodm.ringer.test.web.api.v1.mock;

import org.alessiodm.ringer.web.api.v1.auth.IAuthService;

public class MockAuthService implements IAuthService {

    private Integer validateUser;

    @Override
    public String createTokenForUser(String username, String password) {
        return "abcdef";
    }

    @Override
    public void retireToken(String token) {
        
    }

    @Override
    public Integer validateToken(String token) {
        return validateUser;
    }
    
    public Integer getValidateUser() {
        return validateUser;
    }

    public void setValidateUser(Integer validateUser) {
        this.validateUser = validateUser;
    }
    
}
