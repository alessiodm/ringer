package org.alessiodm.ringer.test;

import javax.sql.DataSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/META-INF/spring/test-context.xml")
public abstract class AbstractRingerSpringTest {
    
    protected NamedParameterJdbcTemplate jt;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        jt = new NamedParameterJdbcTemplate(dataSource);
    }
}
