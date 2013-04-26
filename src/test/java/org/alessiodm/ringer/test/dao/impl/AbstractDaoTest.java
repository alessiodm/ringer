package org.alessiodm.ringer.test.dao.impl;

import org.alessiodm.ringer.test.AbstractRingerSpringTest;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class AbstractDaoTest extends AbstractRingerSpringTest{
    
}
