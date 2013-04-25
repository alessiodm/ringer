package org.alessiodm.ringer.web.auth;

/**
 * Simple auth service for Ringer application.
 * 
 * @author alessio
 */
public interface IAuthService {
    
    public String getToken(Integer a);
    public void retireToken(String token);
    public Integer validateToken(String token);
    
}
