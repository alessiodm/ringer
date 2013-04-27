package org.alessiodm.ringer.web.api.v1.dto;

import java.util.List;
import org.alessiodm.ringer.domain.User;

public class ListOfUsers {
    
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
