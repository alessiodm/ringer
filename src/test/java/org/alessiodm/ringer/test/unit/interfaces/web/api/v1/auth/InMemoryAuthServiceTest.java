package org.alessiodm.ringer.test.unit.interfaces.web.api.v1.auth;

import org.alessiodm.ringer.service.impl.InMemoryAuthService;
import org.alessiodm.ringer.service.impl.UUIDGenerator;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import org.junit.Ignore;
import org.junit.Test;

public class InMemoryAuthServiceTest {
    
    @Test(expected = RuntimeException.class)
    public void testUUIDConflictError() {
        UUIDGenerator gen = EasyMock.createMock(UUIDGenerator.class);
        expect(gen.generate()).andReturn("fufufufufufufu").times(15);
        replay(gen);
        
        InMemoryAuthService auth = new InMemoryAuthService();
        auth.setUuidGenerator(gen);

        auth.createTokenForUser("user", "password"); // First token okey
        auth.createTokenForUser("user", "password"); // Second and subsequent conflicts....
    }
}
