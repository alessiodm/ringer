package org.alessiodm.ringer.interfaces.web.api.v1.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.alessiodm.ringer.domain.model.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDTO {
    
    @XmlElement
    private Long id;
    
    @XmlElement
    private String username;
    
    public UserDTO(){
    }
    
    public UserDTO(User u){
        this.id = u.getId();
        this.username = u.getUsername();
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
    
}
