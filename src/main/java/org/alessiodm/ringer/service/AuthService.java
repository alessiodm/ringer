package org.alessiodm.ringer.service;

import org.alessiodm.ringer.domain.User;
import org.springframework.stereotype.Service;

/**
 * Simple auth service for Ringer application.
 * 
 * @author alessio
 */
@Service
public interface AuthService {
    
    public String createTokenForUser(String username, String password);
    public void retireToken(String token);
    public User validateToken(String token);
    public String getUserToken(Long id);
    
}
