package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.Keyword;
import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.Project;
import edu.baylor.ecs.ams.repository.KeywordRepository;
import edu.baylor.ecs.ams.repository.MetadataRepository;
import edu.baylor.ecs.ams.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
  private final ProjectRepository projectRepository;
  private final KeywordRepository keywordRepository;
  private final MetadataRepository metadataRepository;

  public List<MetadataModel> getProjectWorks(Long projectId) {
    Project project = projectRepository.findById(projectId).orElse(new Project());
    return project.getWorks();
  }

  public List<MetadataModel> saveWorksToProject(List<MetadataModel> works, Long projectId) {
    List<MetadataModel> returnWorks = new ArrayList<>();
    Optional<Project> optProject = projectRepository.getProjectAndWorksById(projectId);
    if (optProject.isPresent()) {
      Project project = optProject.get();
      for (MetadataModel work : works) {
        // if this project does not contain this work, add it; else skip it
        if (project.getWorks().stream().noneMatch(w -> w.getDoi().equals(work.getDoi()))) {
          Optional<MetadataModel> optWork = metadataRepository.getFirstByDoi(work.getDoi());
          MetadataModel savedWork;
          // if this work exists in the database, just use it; else, persist it
          if (optWork.isPresent()) {
            savedWork = optWork.get();
          } else {
            work.setAuthorKeywords(keywordRepository.saveAll(work.getAuthorKeywords()));
            savedWork = metadataRepository.save(work);
          }
          project.addWork(savedWork);
        }
      }
      // updates the project
      project = projectRepository.save(project);
      returnWorks = project.getWorks();
    }
    return returnWorks;
  }

  public Project createProject(Project model) {
    return projectRepository.save(model);
  }
}
