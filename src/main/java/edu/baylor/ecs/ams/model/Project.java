package edu.baylor.ecs.ams.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NamedQuery(name = Project.GET_PROJECT_AND_WORKS_BY_ID,
  query="select p from Project p left join fetch p.works w where p.id = ?1")
public class Project {
  public static final String GET_PROJECT_AND_WORKS_BY_ID = "GET_PROJECT_AND_WORKS_BY_ID";

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
