package org.alessiodm.ringer.web.api.v1.auth;

import java.util.UUID;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value="singleton")
public class UUIDGeneratorImpl implements UUIDGenerator {

    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
    
}
