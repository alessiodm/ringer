package org.alessiodm.ringer.web.auth;

import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="singleton")
public class UUIDGeneratorImpl implements IUUIDGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
    
}
