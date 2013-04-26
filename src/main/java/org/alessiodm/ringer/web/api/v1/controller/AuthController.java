package org.alessiodm.ringer.web.api.v1.controller;

import org.alessiodm.ringer.web.api.v1.auth.IAuthService;
import org.alessiodm.ringer.web.api.v1.dto.AuthToken;
import org.alessiodm.ringer.web.api.v1.dto.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for getting valid token. OAuth inspired.
 * 
 * @author alessio
 */
@Controller
public class AuthController {
    
    @Autowired
    private IAuthService authService;
    
    @RequestMapping(value = "/api/v1/auth/token", produces = {"application/json", "application/xml"})
    public @ResponseBody AuthToken getToken(@RequestBody UserCredentials u){
        String token = authService.createTokenForUser(u.getUsername(), u.getPassword());
        return new AuthToken(token);
    }
    
    @RequestMapping(value = "/api/v1/auth/invalidate_token")
    public boolean invalidateToken(@RequestBody AuthToken token){
        authService.retireToken(token.getToken());
        return true;
    }
    
}
