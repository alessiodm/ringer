package org.alessiodm.ringer.test.unit.interfaces.web.api.v1.controller;

import org.alessiodm.ringer.test.AbstractRingerSpringTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/webmvc-config.xml")
public abstract class AbstractUnitControllerTest extends AbstractRingerSpringTest {
    
}
