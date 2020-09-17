package edu.baylor.ecs.ams.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  @ManyToMany
  private List<MetadataModel> works = new ArrayList<>();

  public void addWork(MetadataModel work) {
    works.add(work);
  }
}
