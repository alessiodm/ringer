package org.alessiodm.ringer.domain;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author alessio
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Ring {
    
    @XmlElement
    private Long id;
    
    @XmlElement
    private Long userId;
    
    @XmlElement
    private String content;
    
    @XmlElement
    private Date timestamp;
    
    
    public boolean belongsTo(User u){
        return u.getId() == userId;
    }
    
    // ----- POJO part ----
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 41 * hash + (this.userId != null ? this.userId.hashCode() : 0);
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
        final Ring other = (Ring) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.userId != other.userId && (this.userId == null || !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }
}
