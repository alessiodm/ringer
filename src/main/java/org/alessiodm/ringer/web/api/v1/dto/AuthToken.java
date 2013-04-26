package org.alessiodm.ringer.web.api.v1.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthToken {

    @XmlElement
    private String token;

    // Empty cnstr for JAXB needs
    public AuthToken(){ }
    
    public AuthToken(String token){
        this.token = token;
    }
    
    public String getToken() {
        return token;
    }
 
    @Override
    public String toString(){
        return token;
    }
}
