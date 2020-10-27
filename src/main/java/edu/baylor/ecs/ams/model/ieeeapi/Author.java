package edu.baylor.ecs.ams.model.ieeeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Author {

    @XmlElement(name = "full_name")
    @JsonProperty("full_name")
    private String fullName;

    @XmlElement(name = "affiliation")
    @JsonProperty("affiliation")
    private String affiliation;

    @XmlElement(name = "author_order")
    @JsonProperty("author_order")
    private String authorOrder;

    @Override
    public String toString() {
        return "Author [fullName=" + fullName + ", affiliation=" + affiliation + ", authorOrder=" + authorOrder + "]";
    }

}
