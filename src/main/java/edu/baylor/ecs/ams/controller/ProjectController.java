package edu.baylor.ecs.ams.controller;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.Project;
import edu.baylor.ecs.ams.request.QueryRequest;
import edu.baylor.ecs.ams.request.StoreDoiRequest;
import edu.baylor.ecs.ams.service.DoiService;
import edu.baylor.ecs.ams.service.ProjectService;
import edu.baylor.ecs.ams.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

  private final DoiService doiService;
  private final ProjectService projectService;
  private final QueryService queryService;

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

  @PostMapping("/{id}/query")
  public List<MetadataModel> saveQueryToProject(@PathVariable Long id, @RequestBody QueryRequest request) throws IOException, InterruptedException {
    List<BaseModel> models = queryService.runQuery(request.getQuery());
    List<MetadataModel> results = models.stream().map(m -> m.toMetadata()).collect(Collectors.toList());
    projectService.addWorksToProject(results, id);
    return results;
  }

  @PostMapping
  public Project createProject(@RequestBody Project model) {
    Project project = projectService.createProject(model);
    return project;
  }
}
