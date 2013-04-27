package org.alessiodm.ringer.web.api.v1.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.alessiodm.ringer.web.api.v1.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    private AuthService authService;
    
    @ExceptionHandler({DataAccessException.class, IOException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error in accessing data")
    public void dataAccessExceptionHandler(RuntimeException ex, HttpServletResponse response) {
        // log...
    }
    
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
