package edu.baylor.ecs.ams.repository;

import edu.baylor.ecs.ams.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
  @Query(name = Project.GET_PROJECT_AND_WORKS_BY_ID)
  Optional<Project> getProjectAndWorksById(Long id);
}
