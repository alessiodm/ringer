package org.alessiodm.ringer.interfaces.web.api.v1.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.alessiodm.ringer.domain.User;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListOfUserDetail {
    
    @XmlElementWrapper
    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
    
    public void convertFromUserList(List<User> users){
        this.users = new ArrayList<UserDTO>(users.size());
        for (User u : users){
            this.users.add(new UserDTO(u.getId(), u.getUsername()));
        }
    }
}
