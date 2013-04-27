package org.alessiodm.ringer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.alessiodm.ringer.dao.UserDao;
import org.alessiodm.ringer.model.User;
import org.alessiodm.ringer.util.MD5Util;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends NamedParameterJdbcDaoSupport implements UserDao {

    private RowMapper<User> userFullMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setEncPassword(rs.getString("enc_password"));
                return user;
            }
        };
    
    @Override
    public User findById(Long id) {
        String sql = "select id, username, enc_password from T_USER where id = :id";
      
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("id", id);
            
        List<User> list = getNamedParameterJdbcTemplate().query(sql, parameters, userFullMapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, enc_password from T_USER where username = :username";

        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("username", username);
            
        List<User> list = getNamedParameterJdbcTemplate().query(sql, parameters, userFullMapper);
        return list.isEmpty() ? null : list.get(0);
    }
    
    @Override
    public User lookupUserByCredentials(String username, String password) {
        String sql = "select id, username, enc_password from T_USER where username = :username and enc_password = :encPassword";
        String encPassword = MD5Util.encrypt(password, username);
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("username", username);
        parameters.put("encPassword", encPassword);
            
        List<User> list = getNamedParameterJdbcTemplate().query(sql, parameters, userFullMapper);
        return list.isEmpty() ? null : list.get(0);
    }
    
    @Override
    public User createUser(String username, String password) {
        // Username as salt
        String sql = "insert into T_USER (username, enc_password) values (:username, :encPassword)";
        String encPassword = MD5Util.encrypt(password, username);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                                            .addValue("username", username)
                                            .addValue("encPassword", encPassword);
        
        getNamedParameterJdbcTemplate().update(sql, parameters, keyHolder);
        
        User u = new User();
        u.setId(keyHolder.getKey().longValue());
        u.setUsername(username);
        u.setEncPassword(encPassword);
        return u;
    }

    @Override
    public int deleteUser(Long id) {
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("id", id);
        return getNamedParameterJdbcTemplate().update("delete from T_USER where id = :id", parameters);
    }
    
}
