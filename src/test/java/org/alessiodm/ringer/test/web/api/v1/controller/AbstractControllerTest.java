package org.alessiodm.ringer.test.web.api.v1.controller;

import org.alessiodm.ringer.test.AbstractRingerSpringTest;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/webmvc-config.xml")
public abstract class AbstractControllerTest extends AbstractRingerSpringTest {
    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mvc;
    
    @Before
    public void setUp(){
        this.mvc = webAppContextSetup(wac).build();
    }
}
