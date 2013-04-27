package org.alessiodm.ringer.web.api.v1.controller;

import org.alessiodm.ringer.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RingController {
    
    @RequestMapping(value = "/api/v1/secure/rings/list", produces = {"application/json", "application/xml"})
    public @ResponseBody Object listRings(@ModelAttribute("user") User user){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    @RequestMapping(value = "/api/v1/secure/rings/show/{ringId}", produces = {"application/json", "application/xml"})
    public @ResponseBody Object showRing(@ModelAttribute("user") User user){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @RequestMapping(value = "/api/v1/secure/rings/create", produces = {"application/json", "application/xml"})
    public @ResponseBody Object createRing(@ModelAttribute("user") User user){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    @RequestMapping(value = "/api/v1/secure/rings/delete/{ringId}", produces = {"application/json", "application/xml"})
    public @ResponseBody Object deleteRing(@ModelAttribute("user") User user){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
