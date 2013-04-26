package org.alessiodm.ringer.dao.impl;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractDaoImpl {
    
    protected NamedParameterJdbcTemplate npjt;
 
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.npjt = new NamedParameterJdbcTemplate(dataSource);
    }
    
}
