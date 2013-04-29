package org.alessiodm.ringer.interfaces.web.api.v1.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.alessiodm.ringer.domain.model.Ring;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListOfRings {
    
    @XmlElementWrapper
    private List<RingDTO> rings;

    public List<RingDTO> getRings() {
        return rings;
    }

    public void setRings(List<RingDTO> rings) {
        this.rings = rings;
    }
    
    public void convertFromRingList(List<Ring> rings){
        this.rings = new ArrayList<RingDTO>(rings.size());
        for (Ring r : rings){
            this.rings.add(new RingDTO(r));
        }
    }
}
