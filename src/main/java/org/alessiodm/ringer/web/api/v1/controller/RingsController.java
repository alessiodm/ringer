package org.alessiodm.ringer.web.api.v1.controller;

import org.alessiodm.ringer.web.api.v1.dto.Foo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RingsController {
    
    @RequestMapping(value = "/api/v1/secure/hello", produces = {"application/json", "application/xml"})
    public @ResponseBody Foo helloSecure(){
        return new Foo();
    }

    @RequestMapping(value = "/api/v1/hello", produces = {"application/json", "application/xml"})
    public @ResponseBody Foo hello(@ModelAttribute("userId") Integer userId){
        Foo foo = new Foo();
        foo.setBu(userId);
        return foo;
    }
    
}
