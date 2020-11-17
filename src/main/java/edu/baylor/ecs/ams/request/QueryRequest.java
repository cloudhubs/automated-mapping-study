package edu.baylor.ecs.ams.request;

import edu.baylor.ecs.ams.enums.Sites;
import lombok.Data;

import java.util.List;

@Data
public class QueryRequest {
  private String query;
  private boolean downloadPapers;
  private List<Sites> sites;
}
