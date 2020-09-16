package edu.baylor.ecs.ams.request;

import edu.baylor.ecs.ams.model.Source;
import lombok.Data;

@Data
public class StoreDoiRequest {
  private String doi;
  private Source source;
}
