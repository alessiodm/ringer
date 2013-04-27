package org.alessiodm.ringer.web.api.v1.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.alessiodm.ringer.domain.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListOfUsers {
    
    @XmlElementWrapper
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
