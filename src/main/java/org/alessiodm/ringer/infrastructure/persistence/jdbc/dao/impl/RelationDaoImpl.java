package org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RelationDao;
import org.alessiodm.ringer.domain.User;
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
    public boolean follows(Long followerId, Long followedId){
        String sql = "select count(*) from T_RELATION where follower_id = :followerId and followed_id = :followedId";
       SqlParameterSource parameters = new MapSqlParameterSource()
                                            .addValue("followerId", followerId)
                                            .addValue("followedId", followedId);
       
       return getNamedParameterJdbcTemplate().queryForObject(sql, parameters, Integer.class) == 1;
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
        page = page < 0 ? 0 : page;
        perPage = perPage < 0 ? 10 : perPage; 
        int offset = perPage * page;
        
        String sql =  "select u.id, u.username, u.enc_password "
                    + "from T_RELATION as r "
                    + "   join T_USER as u on u.id = r.follower_id "
                    + "where r.followed_id = :followedId "
                    + "order by r.follower_id "
                    + "limit :offset, :perPage ";

        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("followedId", followedId);
        parameters.put("perPage", perPage);
        parameters.put("offset", offset);
        
        return getNamedParameterJdbcTemplate().query(sql, parameters, userFullMapper);
    }

    @Override
    public List<User> listFollowing(Long followerId, int page, int perPage) {
        page = page < 0 ? 0 : page;
        perPage = perPage < 0 ? 10 : perPage; 
        int offset = perPage * page;
        
        String sql =  "select u.id, u.username, u.enc_password "
                    + "from T_RELATION as r "
                    + "   join T_USER as u on u.id = r.followed_id "
                    + "where r.follower_id = :followerId "
                    + "order by r.followed_id "
                    + "limit :offset, :perPage ";

        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("followerId", followerId);
        parameters.put("perPage", perPage);
        parameters.put("offset", offset);
            
        return getNamedParameterJdbcTemplate().query(sql, parameters, userFullMapper);
    }
    
}
