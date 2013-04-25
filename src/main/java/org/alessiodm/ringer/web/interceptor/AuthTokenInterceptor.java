package org.alessiodm.ringer.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthTokenInterceptor extends HandlerInterceptorAdapter {
    
    public static final String TOKEN = "valido";
    
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {
        
        String token = request.getParameter("token");
        if (token != null && token.equals(TOKEN)) {
            return true;
        } else {
            response.sendError(401, "401 - Unauthorized");
            return false;
        }
    }
    
}
