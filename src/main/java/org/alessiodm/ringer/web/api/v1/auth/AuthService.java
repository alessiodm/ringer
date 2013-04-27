package org.alessiodm.ringer.web.api.v1.auth;

import org.alessiodm.ringer.model.User;

/**
 * Simple auth service for Ringer application.
 * 
 * @author alessio
 */
public interface AuthService {
    
    public String createTokenForUser(String username, String password);
    public void retireToken(String token);
    public User validateToken(String token);
    
}
