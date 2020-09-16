package edu.baylor.ecs.ams.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class MappingStudy {
  @Id
  private Long id;
  @ManyToMany
  private List<MetadataModel> works;
}
