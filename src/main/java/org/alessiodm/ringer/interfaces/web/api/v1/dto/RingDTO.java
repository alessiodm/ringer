package org.alessiodm.ringer.interfaces.web.api.v1.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.alessiodm.ringer.domain.model.Ring;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RingDTO {
    
    @XmlElement
    private Long id;
    
    @XmlElement
    private Long userId;
    
    @XmlElement
    private String content;
    
    @XmlElement
    private Date timestamp;

    public RingDTO(){}
    public RingDTO(Ring r){
        this.id = r.getId();
        this.userId = r.getUserId();
        this.content = r.getContent();
        this.timestamp = r.getTimestamp();
    }
    public RingDTO(Long id, Long userId, String content, Date timestamp){
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
    }
    
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
}
