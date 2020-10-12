package edu.baylor.ecs.ams.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
public class Keyword {
  @Id
//  @Lob
  private String keyword;

  public Keyword() {}
  public Keyword(String keyword) {
    this.setKeyword(keyword);
  }
}
