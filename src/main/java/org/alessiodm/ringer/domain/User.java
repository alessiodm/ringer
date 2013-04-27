package org.alessiodm.ringer.domain;

import java.util.List;
import org.alessiodm.ringer.dao.RelationDao;
import org.alessiodm.ringer.dao.RingDao;
import org.alessiodm.ringer.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alessio
 */
@Component
public class User {
    
    private Long id;
    private String username;
    private String encPassword;

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private RingDao ringDao;
    
    @Autowired
    private RelationDao relationDao;
    
    @Transactional
    public Ring shoutRing(String content){
        return ringDao.createRing(id, content);
    }
    
    @Transactional
    public int removeRing(Long ringId){
        return ringDao.deleteRing(ringId, id);
    }
    
    @Transactional
    public List<Ring> getRingsList(String keyword, int page, int perPage){
        return ringDao.listRings(id, keyword, page, perPage);
    }
    
    @Transactional
    public int startFollowing(Long newFollowed){
        return relationDao.createRelation(id, newFollowed);
    }
    
    @Transactional
    public int stopFollowing(Long followed){
        return relationDao.deleteRelation(id, followed);
    }
    
    @Transactional
    public List<User> getMyFollowers(int page, int perPage){
        return relationDao.listFollowers(id, page, perPage);
    }
    
    @Transactional
    public List<User> getMyFollowedUsers(int page, int perPage){
        return relationDao.listFollowing(id, page, perPage);
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncPassword() {
        return encPassword;
    }

    public void setEncPassword(String password) {
        this.encPassword = password;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 23 * hash + (this.username != null ? this.username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        return true;
    }
    
}
