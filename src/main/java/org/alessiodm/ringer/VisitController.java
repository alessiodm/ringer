package org.alessiodm.ringer;

import org.alessiodm.ringer.model.Foo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class VisitController {
    
    @RequestMapping(value = "/hello", produces = "application/xml")
    public @ResponseBody Foo hello(){
        return new Foo();
    }
}
