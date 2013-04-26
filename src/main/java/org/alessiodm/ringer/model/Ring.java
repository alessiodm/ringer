package org.alessiodm.ringer.model;

import java.util.Date;

/**
 *
 * @author alessio
 */
public class Ring {
    
    private Long id;
    private String content;
    private Date timestamp;
    
    // Useful for rapid checking out user info
    private Long userId;
    private String username;
    
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
