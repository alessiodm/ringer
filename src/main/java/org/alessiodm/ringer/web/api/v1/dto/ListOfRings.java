package org.alessiodm.ringer.web.api.v1.dto;

import java.util.List;
import org.alessiodm.ringer.domain.Ring;

public class ListOfRings {
    
    private List<Ring> rings;

    public List<Ring> getRings() {
        return rings;
    }

    public void setRings(List<Ring> rings) {
        this.rings = rings;
    }
    
}
