package org.alessiodm.ringer.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.alessiodm.ringer.web.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthTokenInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private IAuthService authService;
    
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        
        String token = request.getParameter("token");
        
        if (authService.validateToken(token) != null){
            return true;
        } else {
            response.sendError(401, "401 - Unauthorized");
            return false;
        }
    }
    
}
