package org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.impl;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractDaoImpl {
    
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public final NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
        return jdbcTemplate;
    }
    
}
