package org.alessiodm.ringer.interfaces.web.api.v1.controller;

import java.util.List;
import org.alessiodm.ringer.domain.model.Ring;
import org.alessiodm.ringer.domain.model.User;
import org.alessiodm.ringer.domain.model.handling.RingerException;
import org.alessiodm.ringer.domain.repository.RingRepository;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.ListOfRings;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.RingContent;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.RingDTO;
import org.alessiodm.ringer.interfaces.web.api.v1.dto.SimpleResult;
import org.alessiodm.ringer.service.RingService;
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
public class RingController extends BaseController {
    
    @Autowired
    private RingService ringService;
    
    @Autowired
    private RingRepository ringRepository;
    
    @RequestMapping(value = "/api/v1/secure/rings/list", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody ListOfRings listRings(@RequestParam(value = "keyword", required = false) String keyword, 
                                               @RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "perPage", required = false) Integer perPage,
                                               @ModelAttribute("user") User user){
        // Preconditions : having page and perPage
        page = page == null ? 0 : page;
        perPage = perPage == null ? 10 : perPage;
        
        if (keyword == null || keyword.trim().equals("")){
            keyword = null;
        }
        
        // Business logic
        List<Ring> rings = ringService.getRingsList(user, keyword, page, perPage);
        // Preparing data for serialization
        ListOfRings lor = new ListOfRings();
        lor.convertFromRingList(rings);
        
        return lor;
    }
    
    @RequestMapping(value = "/api/v1/secure/rings/show/{ringId}", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody RingDTO showRing(@PathVariable Long ringId, @ModelAttribute("user") User user){
        Ring r = ringService.showRingDetails(ringId);
        return new RingDTO(r);
    }

    @RequestMapping(value = "/api/v1/secure/rings/create", method = RequestMethod.POST, produces = {"application/json", "application/xml"})
    public @ResponseBody RingDTO createRing(@RequestBody RingContent ringContent, @ModelAttribute("user") User user){
        Ring r = ringService.createRing(user, ringContent.getContent());
        return new RingDTO(r);
    }
    
    @RequestMapping(value = "/api/v1/secure/rings/delete/{ringId}", method = RequestMethod.DELETE, produces = {"application/json", "application/xml"})
    public @ResponseBody SimpleResult deleteRing(@PathVariable Long ringId, @ModelAttribute("user") User user){
        Ring r = ringRepository.findRingById(ringId);
        if (r == null){
            throw RingerException.NOT_USERS_RING;
        }
        ringService.deleteUserRing(r, user);
        return new SimpleResult(SimpleResult.ResultType.OKEY);
    }
    
}
