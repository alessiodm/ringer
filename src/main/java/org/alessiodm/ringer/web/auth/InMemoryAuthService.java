package org.alessiodm.ringer.web.auth;

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
    public String getToken(Integer accountId) {
        if (accountId == null){
            return null;
        }
        
        if (tokens.containsValue(accountId)){
            for (String token : tokens.keySet()){
                if (tokens.get(token).equals(accountId)){
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
        
        tokens.put(_token, accountId);
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
