package edu.baylor.ecs.ams.model.ieeeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Articles {

    @XmlElement(name = "total_records")
    @JsonProperty("total_records")
    private Integer totalRecords;

    @XmlElement(name = "total_searched")
    @JsonProperty("total_searched")
    private Integer totalSearched;

    @XmlElement(name = "articles")
    @JsonProperty("articles")
    private List<Article> articles = new java.util.ArrayList<Article>();
}
