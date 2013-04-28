package org.alessiodm.ringer.util;

public class RingerAPIException extends RuntimeException {
    
    public static final RingerAPIException USERNAME_NOT_AVAILABLE = 
            new RingerAPIException("Username not available");
    
    public static final RingerAPIException RELATION_ALREADY_EXISTS = 
            new RingerAPIException("Relation already exists");
    
    public static final RingerAPIException NOT_USERS_RING = 
            new RingerAPIException("The ring does not belong to user");
    
    private RingerAPIException(){
        super();
    }
    
    private RingerAPIException(String msg){
        super(msg);
    }
}
