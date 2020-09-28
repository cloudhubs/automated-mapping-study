package edu.baylor.ecs.ams.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Keyword {
  @Id
  private String keyword;

  public Keyword() {}
  public Keyword(String keyword) {
    this.setKeyword(keyword);
  }
}
