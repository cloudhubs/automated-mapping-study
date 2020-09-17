package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.Project;
import edu.baylor.ecs.ams.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
  private final ProjectRepository projectRepository;

  public List<MetadataModel> getProjectWorks(Long projectId) {
    Project project = projectRepository.findById(projectId).orElse(new Project());
    return project.getWorks();
  }

  public void addWorksToProject(List<MetadataModel> works, Long projectId) {
    Optional<Project> project = projectRepository.findById(projectId);
    if (project.isPresent()) {
      Project realProject = project.get();
      for (MetadataModel work : works) {
        realProject.addWork(work);
      }
      projectRepository.save(realProject);
    }
  }

  public Project createProject(Project model) {
    return projectRepository.save(model);
  }
}
