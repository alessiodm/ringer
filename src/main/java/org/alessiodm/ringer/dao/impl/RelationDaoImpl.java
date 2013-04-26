package org.alessiodm.ringer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.alessiodm.ringer.dao.RelationDao;
import org.alessiodm.ringer.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RelationDaoImpl extends NamedParameterJdbcDaoSupport implements RelationDao {

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
    public int createRelation(Long followerId, Long followedId) {
        // Username as salt
        String sql = "insert into T_RELATION (follower_id, followed_id) values (:followerId, :followedId)";
        SqlParameterSource parameters = new MapSqlParameterSource()
                                            .addValue("followerId", followerId)
                                            .addValue("followedId", followedId);
        
        return getNamedParameterJdbcTemplate().update(sql, parameters);
    }

    @Override
    public int deleteRelation(Long followerId, Long followedId) {
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("followerId", followerId);
        parameters.put("followedId", followedId);
        return getNamedParameterJdbcTemplate().update("delete from T_RELATION where follower_id = :followerId and followed_id = :followedId", parameters);
    }

    @Override
    public List<User> listFollowers(Long followedId, int page, int perPage) {
        if (page < 0){
            page = 0;
        }
        
        if (perPage < 10 || perPage > 100){
            perPage = 10;
        }
        
        String sql =  "select u.id, u.username, u.enc_password "
                    + "from T_RELATION as r "
                    + "   join T_USER as u on u.id = r.follower_id "
                    + "where r.followed_id = :followedId "
                    + "order by r.follower_id "
                    + "limit :perPage "
                    + "offset :page";

        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("followedId", followedId);
        parameters.put("perPage", perPage);
        parameters.put("page", page);
        
        return getNamedParameterJdbcTemplate().query(sql, parameters, userFullMapper);
    }

    @Override
    public List<User> listFollowing(Long followerId, int page, int perPage) {
        if (page < 0){
            page = 0;
        }
        
        if (perPage < 10 || perPage > 100){
            perPage = 10;
        }
        
        String sql =  "select u.id, u.username, u.enc_password "
                    + "from T_RELATION as r "
                    + "   join T_USER as u on u.id = r.followed_id "
                    + "where r.follower_id = :followerId "
                    + "order by r.followed_id "
                    + "limit :perPage "
                    + "offset :page";

        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("followerId", followerId);
        parameters.put("perPage", perPage);
        parameters.put("page", page);
            
        return getNamedParameterJdbcTemplate().query(sql, parameters, userFullMapper);
    }
    
}
