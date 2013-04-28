package org.alessiodm.ringer.interfaces.web.api.v1.auth;

import java.util.concurrent.ConcurrentHashMap;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.domain.repository.UserRepository;
import org.alessiodm.ringer.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

@Scope(value="singleton")
public class InMemoryAuthService implements AuthService {

    protected ConcurrentHashMap<String, Long> tokens = new ConcurrentHashMap<String, Long>();
    
    @Autowired
    private UUIDGenerator uuidGenerator;

    @Autowired
    private UserRepository userRepository;
    
    public InMemoryAuthService(){
        tokens.put("global1", -1L);
    }
    
    @Override
    @Transactional
    public String createTokenForUser(String username, String password) {
        if (username == null || password == null){
            return null;
        }
        
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user == null){
            return null;
        }
        
        for (String token : tokens.keySet()){
            if (tokens.get(token).equals(user.getId())){
                return token;
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
        
        tokens.put(_token, user.getId());
        return _token;
    }

    @Override
    public void retireToken(String token) {
        if (token != null){
            tokens.remove(token);
        }
    }

    @Override
    @Transactional
    public User validateToken(String token) {
        if (token == null){
            return null;
        }
        
        Long uid = tokens.get(token);
        if (uid == null){
            return null;
        }
        
        User user = userRepository.findUserById(uid);
        return user == null ? null : user;
    }
 
    @Override
    public String getUserToken(Long id) {
        if (id == null){
            return null;
        }
        
        for (String token : tokens.keySet()){
            if (tokens.get(token).equals(id)){
                return token;
            }
        }
        
        return null;
    }
    
    
    public UUIDGenerator getUuidGenerator() {
        return uuidGenerator;
    }

    public void setUuidGenerator(UUIDGenerator uuidGenerator) {
        this.uuidGenerator = uuidGenerator;
    }

}
