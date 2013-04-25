package org.alessiodm.ringer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class VisitController {
    
    @RequestMapping("/hello")
    public @ResponseBody String hello(){
        return "World!";
    }
}
