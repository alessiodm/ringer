package org.alessiodm.ringer.domain.model.handling;

public class RingerException extends RuntimeException {
    
    // Throw always same exception => optimization
    public static final RingerException USERNAME_NOT_AVAILABLE = 
            new RingerException("Username not available");
    
    public static final RingerException RELATION_ALREADY_EXISTS = 
            new RingerException("Relation already exists");
    
    public static final RingerException NOT_USERS_RING = 
            new RingerException("The ring does not belong to user");
    
    private RingerException(){
        super();
    }
    
    private RingerException(String msg){
        super(msg);
    }
}
