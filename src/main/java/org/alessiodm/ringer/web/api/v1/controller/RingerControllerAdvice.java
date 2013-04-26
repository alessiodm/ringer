package org.alessiodm.ringer.web.api.v1.controller;

import javax.servlet.http.HttpServletRequest;
import org.alessiodm.ringer.web.api.v1.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Controller Advice is useful for handling exceptions in one place for 
 * all application controllers and to have global @ModelAttribute and 
 * @InitBinding stuff.
 * 
 * We're using it in order to get the userId based on the token passed to
 * the API, in a OAuth-like manner.
 * 
 * @author alessio
 */
@ControllerAdvice
public class RingerControllerAdvice {
    
    @Autowired
    private IAuthService authService;
    
    /**
     * Get the ID of the user the token belongs to.
     * 
     * @param request   HTTP request
     * @param model     Current model
     */
    @ModelAttribute
    public void getUserId(HttpServletRequest request, Model model){
        String token = request.getParameter("token");
        Integer id = authService.validateToken(token);
        
        if(token != null){
            model.addAttribute("token", token);
        }
        if(id != null){
            model.addAttribute("userId", id);
        } 
    }
    
}
