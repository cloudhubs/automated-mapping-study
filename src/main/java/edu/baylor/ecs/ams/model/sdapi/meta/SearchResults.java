package edu.baylor.ecs.ams.model.sdapi.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResults {
    @JsonProperty("opensearch:totalResults")
    long totalResults;

    @JsonProperty("entry")
    List<Entry> entries;
}
