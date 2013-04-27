package org.alessiodm.ringer.web.api.v1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.alessiodm.ringer.web.api.v1.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthTokenInterceptor extends HandlerInterceptorAdapter {
    
    @Autowired
    private AuthService authService;
    
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        
        String token = request.getParameter("token");
        
        if (authService.validateToken(token) != null){
            return true;
        } else {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
            return false;
        }
    }
    
}
