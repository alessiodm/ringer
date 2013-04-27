package org.alessiodm.ringer.web.api.v1.controller;

import java.util.List;
import org.alessiodm.ringer.model.User;
import org.alessiodm.ringer.web.api.v1.dto.ListOfUser;
import org.alessiodm.ringer.web.api.v1.dto.SimpleResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RelationController {
    
    @RequestMapping(value = "/api/v1/secure/relations/followers/list", produces = {"application/json", "application/xml"})
    public @ResponseBody ListOfUser followersList(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "perPage", required = false) Integer perPage,
                                                  @ModelAttribute("user") User user){
        page = page == null ? 0 : page;
        perPage = perPage == null ? 10 : perPage;
        
        List<User> followers = user.getMyFollowers(page, perPage);
        
        ListOfUser list = new ListOfUser();
        list.setUsers(followers);
        return list;
    }
    
    @RequestMapping(value = "/api/v1/secure/relations/following/list", produces = {"application/json", "application/xml"})
    public @ResponseBody ListOfUser followingList(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "perPage", required = false) Integer perPage,
                                                  @ModelAttribute("user") User user){
        page = page == null ? 0 : page;
        perPage = perPage == null ? 10 : perPage;
        
        List<User> followers = user.getMyFollowedUsers(page, perPage);
        
        ListOfUser list = new ListOfUser();
        list.setUsers(followers);
        return list;
    }

    @RequestMapping(value = "/api/v1/secure/relations/following/add/{fId}", produces = {"application/json", "application/xml"})
    public @ResponseBody SimpleResult follow(@PathVariable Long fId, @ModelAttribute("user") User user){
        int result = user.startFollowing(fId);
        return SimpleResult.getSimpleResultFromExpectedInt(1, result);
    }
    
    @RequestMapping(value = "/api/v1/secure/relations/following/remove/{fId}", produces = {"application/json", "application/xml"})
    public @ResponseBody SimpleResult unfollow(@PathVariable Long fId, @ModelAttribute("user") User user){
        int result = user.stopFollowing(fId);
        return SimpleResult.getSimpleResultFromExpectedInt(1, result);
    }

}
