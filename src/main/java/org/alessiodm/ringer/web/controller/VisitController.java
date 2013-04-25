package org.alessiodm.ringer.web.controller;

import org.alessiodm.ringer.model.Foo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class VisitController {
    
    @RequestMapping(value = "/api/v1/secure/hello", produces = "application/xml")
    public @ResponseBody Foo helloSecure(){
        return new Foo();
    }
    
    @RequestMapping(value = "/api/v1/hello", produces = "application/xml")
    public @ResponseBody Foo hello(){
        return new Foo();
    }
}
