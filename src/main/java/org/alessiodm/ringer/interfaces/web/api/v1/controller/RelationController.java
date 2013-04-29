package org.alessiodm.ringer.interfaces.web.api.v1.controller;

import java.util.List;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.ListOfUserDetail;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.SimpleResult;
import org.alessiodm.ringer.service.UserService;
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
    
    private @Autowired UserService userService;
    
    @RequestMapping(value = "/api/v1/secure/relations/followers/list", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody ListOfUserDetail followersList(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "perPage", required = false) Integer perPage,
                                                  @ModelAttribute("user") User user){
        page = page == null ? 0 : page;
        perPage = perPage == null ? 10 : perPage;
        
        List<User> followers = user.getFollowers(page, perPage);
        
        ListOfUserDetail list = new ListOfUserDetail();
        list.convertFromUserList(followers);
        return list;
    }
    
    @RequestMapping(value = "/api/v1/secure/relations/following/list", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody ListOfUserDetail followingList(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "perPage", required = false) Integer perPage,
                                                  @ModelAttribute("user") User user){
        page = page == null ? 0 : page;
        perPage = perPage == null ? 10 : perPage;
        
        List<User> followers = user.getFollowing(page, perPage);
                
        ListOfUserDetail list = new ListOfUserDetail();
        list.convertFromUserList(followers);
        return list;
    }

    @RequestMapping(value = "/api/v1/secure/relations/following/add/{fId}", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public @ResponseBody SimpleResult follow(@PathVariable Long fId, @ModelAttribute("user") User user){
        User followed = userService.getUserById(fId);
        user.startFollowing(followed);
        return new SimpleResult(SimpleResult.ResultType.OKEY);
    }
    
    @RequestMapping(value = "/api/v1/secure/relations/following/remove/{fId}", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public @ResponseBody SimpleResult unfollow(@PathVariable Long fId, @ModelAttribute("user") User user){
        User followed = userService.getUserById(fId);
        user.stopFollowing(followed);
        return new SimpleResult(SimpleResult.ResultType.OKEY);
    }

}
