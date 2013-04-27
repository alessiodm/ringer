package org.alessiodm.ringer.web.api.v1.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleResult {
    
    @XmlElement
    private ResultType result;
    @XmlElement
    private String description;

    public SimpleResult(){}
    public SimpleResult(ResultType result){
        this(result, null);
    }
    public SimpleResult(ResultType result, String description){
        this.result = result;
        this.description = description;
    }
    
    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public static SimpleResult getSimpleResultFromExpectedInt(int expected, int actual){
        return getSimpleResultFromExpectedInt(expected, actual, "", "");
    }
    
    public static SimpleResult getSimpleResultFromExpectedInt(int expected, int actual, String okeyMessage, String errorMessage){
        if (expected != actual){
            return new SimpleResult(SimpleResult.ResultType.ERROR, errorMessage);
        }
        else{
            return new SimpleResult(SimpleResult.ResultType.OKEY, okeyMessage);
        }
    }
    
    @XmlEnum
    @XmlType(name = "")
    public static enum ResultType implements Serializable {
        OKEY, 
        ERROR
    }
}
