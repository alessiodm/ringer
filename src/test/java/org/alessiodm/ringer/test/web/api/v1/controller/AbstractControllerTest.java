package org.alessiodm.ringer.test.web.api.v1.controller;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/webmvc-config.xml",
                      "file:src/test/resources/META-INF/spring/test-context.xml"})
public abstract class AbstractControllerTest {
    @Autowired
    protected WebApplicationContext wac;
    protected MockMvc mvc;
    
    @Before
    public void setUp(){
        this.mvc = webAppContextSetup(wac).build();
    }
}
