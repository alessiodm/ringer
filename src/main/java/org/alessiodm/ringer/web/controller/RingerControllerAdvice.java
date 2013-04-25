package org.alessiodm.ringer.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.alessiodm.ringer.web.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RingerControllerAdvice {
    
    @Autowired
    IAuthService authService;
    
    @ModelAttribute("userId")
    public void getUserId(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        Integer id = authService.validateToken(token);
        model.addAttribute("userId", id != null ? id : -1);
    }
    
}
