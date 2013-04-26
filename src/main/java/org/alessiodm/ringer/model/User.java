package org.alessiodm.ringer.model;

/**
 *
 * @author alessio
 */
public class User {
    
    private Long id;
    private String username;
    private String encPassword;

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
}
