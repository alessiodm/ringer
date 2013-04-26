package org.alessiodm.ringer.test.web.api.v1.auth;

import org.alessiodm.ringer.web.api.v1.auth.InMemoryAuthService;
import org.alessiodm.ringer.web.api.v1.auth.UUIDGenerator;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import org.junit.Ignore;
import org.junit.Test;

public class InMemoryAuthServiceTest {
    
    @Ignore
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
