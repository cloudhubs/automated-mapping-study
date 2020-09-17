package edu.baylor.ecs.ams.controller;

import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.Project;
import edu.baylor.ecs.ams.request.StoreDoiRequest;
import edu.baylor.ecs.ams.service.DoiService;
import edu.baylor.ecs.ams.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

  private final DoiService doiService;
  private final ProjectService projectService;

  @PostMapping("/{id}/save")
  public List<MetadataModel> saveDoisToProject(@RequestBody List<StoreDoiRequest> requests, @PathVariable Long id) {
    List<MetadataModel> works = doiService.findAllWorksByDois(requests.stream().map(r -> r.getDoi()).collect(Collectors.toList()));
    projectService.addWorksToProject(works, id);
    return works;
  }

  @GetMapping("/{id}/works")
  public List<MetadataModel> getProjectWorks(@PathVariable Long id) {
    return projectService.getProjectWorks(id);
  }

  @PostMapping
  public Project createProject(@RequestBody Project model) {
    Project project = projectService.createProject(model);
    return project;
  }
}
