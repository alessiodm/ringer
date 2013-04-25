package org.alessiodm.ringer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Foo {

    @XmlAttribute
    private String ao = "cicco";
    @XmlElement
    private int bu = 0;

    
    public String getAo() {
        return ao;
    }

    public void setAo(String ao) {
        this.ao = ao;
    }
    
    public int getBu() {
        return bu;
    }

    public void setBu(int bu) {
        this.bu = bu;
    }
}
