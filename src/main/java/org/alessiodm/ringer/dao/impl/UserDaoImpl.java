package org.alessiodm.ringer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.alessiodm.ringer.dao.UserDao;
import org.alessiodm.ringer.model.User;
import org.alessiodm.ringer.util.MD5Util;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends NamedParameterJdbcDaoSupport implements UserDao {

    @Override
    public User findById(Long id) {
        String sql = "select id, username, enc_password from T_USER where id = :id";
        
        RowMapper<User> mapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEncPassword(rs.getString("enc_password"));
                return user;
            }
        };

        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("id", id);
        
        return getNamedParameterJdbcTemplate().queryForObject(sql, parameters, mapper);
    }

    @Override
    public User createUser(String username, String password) {
        // Username as salt
        String encPassword = MD5Util.encrypt(password, username);
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteUser(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
