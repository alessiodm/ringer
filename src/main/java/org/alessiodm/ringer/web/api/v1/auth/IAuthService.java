package org.alessiodm.ringer.web.api.v1.auth;

/**
 * Simple auth service for Ringer application.
 * 
 * @author alessio
 */
public interface IAuthService {
    
    public String createTokenForUser(String username, String password);
    public void retireToken(String token);
    public Integer validateToken(String token);
    
}
