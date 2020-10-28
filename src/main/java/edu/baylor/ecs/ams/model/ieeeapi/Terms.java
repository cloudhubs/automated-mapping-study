package edu.baylor.ecs.ams.model.ieeeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Terms {

    @XmlElement(name = "term")
    @JsonProperty("terms")
    public List<String> terms = new ArrayList<String>();

    @Override
    public String toString() {
        return "Terms [terms=" + terms + "]";
    }
}
