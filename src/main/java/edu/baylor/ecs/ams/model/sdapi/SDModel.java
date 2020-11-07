package edu.baylor.ecs.ams.model.sdapi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class SDModel {
    long resultsFound;
    List<Article> results;
}
