package edu.baylor.ecs.ams.request;

import lombok.Data;

@Data
public class QueryRequest {
  private String query;
  private boolean downloadPapers;
}
