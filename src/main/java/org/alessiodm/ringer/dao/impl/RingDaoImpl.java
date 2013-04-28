package org.alessiodm.ringer.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.alessiodm.ringer.dao.RingDao;
import org.alessiodm.ringer.domain.Ring;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class RingDaoImpl extends NamedParameterJdbcDaoSupport implements RingDao {
    
    private RowMapper<Ring> ringFullMapper = new RowMapper<Ring>() {
            @Override
            public Ring mapRow(ResultSet rs, int rowNum) throws SQLException {
                Ring ring = new Ring();
                ring.setId(rs.getLong("id"));
                ring.setUserId(rs.getLong("user_id"));
                ring.setContent(rs.getString("content"));
                ring.setTimestamp(rs.getDate("timestamp"));
                
                return ring;
            }
        };
    
    @Override
    public int createRingContent(Long ringId, String content) {
        String sql = "insert into T_RING_CONTENT (id, content) values (:ringId, :content)";
        SqlParameterSource parameters = new MapSqlParameterSource()
                                            .addValue("ringId", ringId)
                                            .addValue("content", content);
        
        return getNamedParameterJdbcTemplate().update(sql, parameters);
    }
    
    @Override
    public Ring createRing(Long userId) {
        String sql = "insert into T_RING (user_id, timestamp) values (:userId, :timestamp)";
        Date timestamp = new Date();
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource parameters = new MapSqlParameterSource()
                                            .addValue("userId", userId)
                                            .addValue("timestamp", timestamp);
        
        getNamedParameterJdbcTemplate().update(sql, parameters, keyHolder);
        
        Ring r = new Ring();
        r.setId(keyHolder.getKey().longValue());
        r.setUserId(userId);
        r.setTimestamp(timestamp);
        
        return r;
    }

    @Override
    public Ring findById(Long ringId) {
        String sql = "select r.id, r.user_id, c.content, r.timestamp "
                    + "from T_RING as r inner join T_RING_CONTENT as c on (r.id = c.id) "
                    + "where r.id = :id ";

        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("id", ringId);
            
        List<Ring> list = getNamedParameterJdbcTemplate().query(sql, parameters, ringFullMapper);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int deleteRingContent(Long ringId){
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("id", ringId);
        return getNamedParameterJdbcTemplate().update("delete from T_RING_CONTENT where id = :id", parameters);
    }
    
    @Override
    public int deleteRing(Long ringId) {
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("id", ringId);
        return getNamedParameterJdbcTemplate().update("delete from T_RING where id = :id", parameters);
    }

    @Override
    public List<Ring> listRings(Long userId, String keyword, int page, int perPage) {
        page = page < 0 ? 0 : page;
        perPage = perPage < 0 ? 10 : perPage; 
        int offset = perPage * page;
        
        String matchSql = " and match(rc.content) against(:keyword in boolean mode) ";
        String sql = " select ring.id, ring.user_id, rc.content, ring.timestamp "
                    + "from T_RING as ring "
                    + "    inner join T_RING_CONTENT as rc on (ring.id = rc.id)"
                    + "    inner join T_RELATION as rel on (ring.user_id = rel.followed_id) "
                    + "where rel.follower_id = :userId %s "
                    + "  union "    
                    + "select ring.id, ring.user_id, rc.content, ring.timestamp "
                    + "from T_RING as ring "
                    + "    inner join T_RING_CONTENT as rc on (ring.id = rc.id)"
                    + "where ring.user_id = :userId %s "
                    + "order by timestamp desc "
                    + "limit :offset, :perPage ";
        
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("userId", userId);
        parameters.put("perPage", perPage);
        parameters.put("offset", offset);
        
        if (keyword != null){
            sql = String.format(sql, matchSql, matchSql);
            parameters.put("keyword", keyword);
        }
        else {
            sql = String.format(sql, "", "");  
        } 
        
        return getNamedParameterJdbcTemplate().query(sql, parameters, ringFullMapper);
    }

    @Override
    public boolean belongsToUser(Long ringId, Long userId) {
        String sql = "select count(*) from T_RING where id = :ringId and user_id = :userId";
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("ringId", ringId);
        parameters.put("userId", userId);
        Integer count = getNamedParameterJdbcTemplate().queryForObject(sql, parameters, Integer.class);
        
        return (count != null && count > 0);
    }

}
