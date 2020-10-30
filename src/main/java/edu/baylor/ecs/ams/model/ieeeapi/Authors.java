package edu.baylor.ecs.ams.model.ieeeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Authors {

    @XmlElement(name = "author")
    @JsonProperty("authors")
    private List<Author> authors = new java.util.ArrayList<Author>();

    @Override
    public String toString() {
        return "Authors [authors=" + authors + "]";
    }
}
