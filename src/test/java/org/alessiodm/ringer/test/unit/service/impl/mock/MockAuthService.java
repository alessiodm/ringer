package org.alessiodm.ringer.test.unit.service.impl.mock;

import javax.annotation.PostConstruct;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.service.AuthService;

public class MockAuthService implements AuthService {

    private User validateUser;
    private String token;
    
    public MockAuthService(){
    }
    
    @PostConstruct
    public void initialize() {
       reset();
    }
    
    public final void reset(){
        token = "abcdef";
        User u = new User();
        u.setId(10L);
        u.setUsername("user");
        u.setEncPassword("xxx");
        this.validateUser = u;
    }
    
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
    
    @Override
    public String getUserToken(Long id) {
        return token;
    }
    
    public User getValidateUser() {
        return validateUser;
    }

    public void setValidateUser(User validateUser) {
        this.validateUser = validateUser;
    }

    public String getToken(){
        return token;
    }
    
    public void setToken(String token){
        this.token = token;
    }
    
}
