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
public class IndexTerms {

    @XmlElement(name = "ieee_terms")
    @JsonProperty("ieee_terms")
    private Terms ieeeTerms;

    @XmlElement(name = "controlledterms")
    @JsonProperty("controlledterms")
    private Terms controlledterms;

    @XmlElement(name = "uncontrolledterms")
    @JsonProperty("uncontrolledterms")
    private Terms uncontrolledterms;

    @XmlElement(name = "author_terms")
    @JsonProperty("author_terms")
    private Terms authorTerms;

    @Override
    public String toString() {
        return "IndexTerms [ieeeTerms=" + ieeeTerms + ", controlledterms=" + controlledterms + ", uncontrolledterms="
                + uncontrolledterms + ", authorTerms=" + authorTerms + "]";
    }
}
