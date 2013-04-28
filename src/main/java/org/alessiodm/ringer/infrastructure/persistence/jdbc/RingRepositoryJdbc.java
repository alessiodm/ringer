package org.alessiodm.ringer.infrastructure.persistence.jdbc;

import java.util.List;
import org.alessiodm.ringer.domain.Ring;
import org.alessiodm.ringer.domain.repository.RingRepository;
import org.alessiodm.ringer.domain.User;
import org.alessiodm.ringer.infrastructure.persistence.jdbc.dao.RingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.transaction.annotation.Transactional;

public class RingRepositoryJdbc implements RingRepository {

    private @Autowired AutowireCapableBeanFactory beanFactory;
    
    protected @Autowired RingDao ringDao;
    
    @Override
    public Ring findRingById(Long ringId) {
        return ringDao.findById(ringId);
    }

    @Override
    @Transactional public Ring createRing(User u, String content) {
        Ring r =  ringDao.createRing(u.getId());
        ringDao.createRingContent(r.getId(), content);
        r.setContent(content);
        beanFactory.autowireBean(r);
        return r;
    }

    @Override
    @Transactional public int deleteRing(Ring r) {
        ringDao.deleteRingContent(r.getId());
        return ringDao.deleteRing(r.getId());
    }

    @Override
    public List<Ring> getRingsList(User u, String keyword, int page, int perPage) {
        return ringDao.listRings(u.getId(), keyword, page, perPage);
    }
    
}
