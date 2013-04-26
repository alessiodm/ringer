package org.alessiodm.ringer.web.api.v1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RelationController {
    
    @RequestMapping(value = "/api/v1/secure/relations/followers/list", produces = {"application/json", "application/xml"})
    public @ResponseBody Object followersList(@ModelAttribute("userId") Integer userId){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    @RequestMapping(value = "/api/v1/secure/relations/following/list", produces = {"application/json", "application/xml"})
    public @ResponseBody Object followingList(@ModelAttribute("userId") Integer userId){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @RequestMapping(value = "/api/v1/secure/relations/following/add/{fId}", produces = {"application/json", "application/xml"})
    public @ResponseBody Object follow(@ModelAttribute("userId") Integer userId){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    @RequestMapping(value = "/api/v1/secure/relations/following/remove/{fId}", produces = {"application/json", "application/xml"})
    public @ResponseBody Object unfollow(@ModelAttribute("userId") Integer userId){
        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
