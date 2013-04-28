package org.alessiodm.ringer.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.alessiodm.ringer.domain.repository.UserRepository;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author alessio
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class User {
    
    @XmlElement
    private Long id;
    
    @XmlElement
    private String username;
    
    @XmlTransient
    @JsonIgnore
    private String encPassword;
    
    private @XmlTransient @Autowired UserRepository userRepository;
    
    public void startFollowing(User u){
        if (amIFollowing(u) == true){
            throw RingerException.RELATION_ALREADY_EXISTS;
        }
        userRepository.createRelation(this, u);
    }
    
    public void stopFollowing(User u){
        userRepository.deleteRelation(this, u);
    }
    
    public List<User> getFollowers(int offset, int number){
        return userRepository.getFollowers(this, offset, number);
    }
    
    public List<User> getFollowing(int offset, int number){
        return userRepository.getFollowing(this, offset, number);
    }
    
    protected boolean amIFollowing(User u){
        return id == u.id || userRepository.follows(this, u);
    }
    
    // ----- Start POJO part -----
    
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
