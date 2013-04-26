package org.alessiodm.ringer.web.api.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RingController {
    
    @RequestMapping(value = "/api/v1/secure/rings/list", produces = {"application/json", "application/xml"})
    public @ResponseBody Object listRings(@ModelAttribute("userId") Integer userId){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    @RequestMapping(value = "/api/v1/secure/rings/show/{ringId}", produces = {"application/json", "application/xml"})
    public @ResponseBody Object showRing(@ModelAttribute("userId") Integer userId){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @RequestMapping(value = "/api/v1/secure/rings/create", produces = {"application/json", "application/xml"})
    public @ResponseBody Object createRing(@ModelAttribute("userId") Integer userId){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    @RequestMapping(value = "/api/v1/secure/rings/delete/{ringId}", produces = {"application/json", "application/xml"})
    public @ResponseBody Object deleteRing(@ModelAttribute("userId") Integer userId){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
