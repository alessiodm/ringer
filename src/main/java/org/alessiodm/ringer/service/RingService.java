package org.alessiodm.ringer.service;

import java.util.List;
import org.alessiodm.ringer.domain.model.Ring;
import org.alessiodm.ringer.domain.model.User;

public interface RingService {

    public Ring showRingDetails(Long ringId);
    public Ring createRing(User u, String content);
    public int deleteUserRing(Ring r, User u);
    public List<Ring> getRingsList(User u, String keyword, int page, int perPage);
    
}
