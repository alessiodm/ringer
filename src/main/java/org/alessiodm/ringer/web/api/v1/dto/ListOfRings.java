package org.alessiodm.ringer.web.api.v1.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import org.alessiodm.ringer.domain.Ring;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ListOfRings {
    
    @XmlElementWrapper
    private List<Ring> rings;

    public List<Ring> getRings() {
        return rings;
    }

    public void setRings(List<Ring> rings) {
        this.rings = rings;
    }
    
}
