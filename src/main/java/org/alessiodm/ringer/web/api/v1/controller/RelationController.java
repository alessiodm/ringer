package org.alessiodm.ringer.web.api.v1.controller;

import java.util.List;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.service.UserService;
import org.alessiodm.ringer.web.api.v1.dto.ListOfUsers;
import org.alessiodm.ringer.web.api.v1.dto.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RelationController extends BaseController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/api/v1/secure/relations/followers/list", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody ListOfUsers followersList(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "perPage", required = false) Integer perPage,
                                                  @ModelAttribute("user") User user){
        page = page == null ? 0 : page;
        perPage = perPage == null ? 10 : perPage;
        
        List<User> followers = userService.getFollowers(user.getId(), page, perPage);
        
        ListOfUsers list = new ListOfUsers();
        list.setUsers(followers);
        return list;
    }
    
    @RequestMapping(value = "/api/v1/secure/relations/following/list", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody ListOfUsers followingList(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "perPage", required = false) Integer perPage,
                                                  @ModelAttribute("user") User user){
        page = page == null ? 0 : page;
        perPage = perPage == null ? 10 : perPage;
        
        List<User> followers = userService.getFollowing(user.getId(), page, perPage);
        
        ListOfUsers list = new ListOfUsers();
        list.setUsers(followers);
        return list;
    }

    @RequestMapping(value = "/api/v1/secure/relations/following/add/{fId}", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public @ResponseBody SimpleResult follow(@PathVariable Long fId, @ModelAttribute("user") User user){
        int result = userService.startFollowing(user.getId(), fId);
        return SimpleResult.getSimpleResultFromExpectedInt(1, result);
    }
    
    @RequestMapping(value = "/api/v1/secure/relations/following/remove/{fId}", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public @ResponseBody SimpleResult unfollow(@PathVariable Long fId, @ModelAttribute("user") User user){
        int result = userService.stopFollowing(user.getId(), fId);
        return SimpleResult.getSimpleResultFromExpectedInt(1, result);
    }

}
