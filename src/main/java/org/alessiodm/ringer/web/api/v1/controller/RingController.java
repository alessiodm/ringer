package org.alessiodm.ringer.web.api.v1.controller;

import java.util.List;
import org.alessiodm.ringer.domain.Ring;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.service.RingService;
import org.alessiodm.ringer.web.api.v1.dto.ListOfRings;
import org.alessiodm.ringer.web.api.v1.dto.RingContent;
import org.alessiodm.ringer.web.api.v1.dto.SimpleResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RingController {
    
    @Autowired
    private RingService ringService;
    
    @RequestMapping(value = "/api/v1/secure/rings/list", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody ListOfRings listRings(@RequestParam(value = "keyword", required = false) String keyword, 
                                               @RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "perPage", required = false) Integer perPage,
                                               @ModelAttribute("user") User user){
        // Preconditions : having page and perPage
        page = page == null ? 0 : page;
        perPage = perPage == null ? 10 : perPage;
        
        // Business logic
        List<Ring> userRings = ringService.getRingsList(user.getId(), keyword, page, perPage);
        
        // Preparing data for serialization
        ListOfRings lor = new ListOfRings();
        lor.setRings(userRings);
        
        return lor;
    }
    
    @RequestMapping(value = "/api/v1/secure/rings/show/{ringId}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody Ring showRing(@PathVariable Long ringId, @ModelAttribute("user") User user){
        return ringService.showRing(ringId);
    }

    @RequestMapping(value = "/api/v1/secure/rings/create", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public @ResponseBody Ring createRing(@RequestBody RingContent ringContent, @ModelAttribute("user") User user){
        return ringService.createRing(ringContent.getContent(), user.getId());
    }
    
    @RequestMapping(value = "/api/v1/secure/rings/delete/{ringId}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public @ResponseBody SimpleResult deleteRing(@PathVariable Long ringId, @ModelAttribute("user") User user){
        int result = ringService.deleteRing(ringId, user.getId());
        
        return SimpleResult.getSimpleResultFromExpectedInt(1, result);
    }
    
}
