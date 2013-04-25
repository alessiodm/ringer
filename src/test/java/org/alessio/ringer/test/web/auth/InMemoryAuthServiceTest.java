package org.alessio.ringer.test.web.auth;

import org.alessiodm.ringer.web.auth.IUUIDGenerator;
import org.alessiodm.ringer.web.auth.InMemoryAuthService;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import org.junit.Test;

public class InMemoryAuthServiceTest {
    
    @Test(expected = RuntimeException.class)
    public void testUUIDConflictError() {
        IUUIDGenerator gen = EasyMock.createMock(IUUIDGenerator.class);
        expect(gen.generate()).andReturn("fufufufufufufu").times(15);
        replay(gen);
        
        InMemoryAuthService auth = new InMemoryAuthService();
        auth.setUuidGenerator(gen);

        auth.getToken(15); // First token okey
        auth.getToken(18); // Second and subsequent conflicts....   
    }
}
