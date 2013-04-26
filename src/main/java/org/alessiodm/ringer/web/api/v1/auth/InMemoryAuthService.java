package org.alessiodm.ringer.web.api.v1.auth;

import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;


@Repository
@Scope(value="singleton")
public class InMemoryAuthService implements IAuthService {

    protected ConcurrentHashMap<String, Integer> tokens = new ConcurrentHashMap<String, Integer>();
    
    @Autowired
    private IUUIDGenerator uuidGenerator;

    public InMemoryAuthService(){
        tokens.put("global1", -1);
    }
    
    @Override
    public String createTokenForUser(String username, String password) {
        if (username == null || password == null){
            return null;
        }
        
        // TODO: get user id and password from database
        Integer userId = -1;
        
        if (tokens.containsValue(userId)){
            for (String token : tokens.keySet()){
                if (tokens.get(token).equals(userId)){
                    return token;
                }
            }
        }
        
        // Perhaps very rare conflicts...
        String _token;
        int counter = 0;
        do{
            _token = uuidGenerator.generate();
            if (++counter > 10) {
                throw new RuntimeException("Too many conflicts in generating auth token!");
            }
        }
        while(tokens.containsKey(_token));
        
        tokens.put(_token, userId);
        return _token;
    }

    @Override
    public void retireToken(String token) {
        if (token != null){
            tokens.remove(token);
        }
    }

    @Override
    public Integer validateToken(String token) {
        return token == null ? null : tokens.get(token);
    }
 
    public IUUIDGenerator getUuidGenerator() {
        return uuidGenerator;
    }

    public void setUuidGenerator(IUUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }
    
}
