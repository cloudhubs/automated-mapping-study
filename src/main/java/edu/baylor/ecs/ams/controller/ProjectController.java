package edu.baylor.ecs.ams.controller;

import edu.baylor.ecs.ams.enums.Sites;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProjectController {

  private final DoiService doiService;
  private final ProjectService projectService;
  private final QueryService queryService;

  @PostMapping("/{id}/save")
  public List<MetadataModel> saveDoisToProject(@RequestBody List<StoreDoiRequest> requests, @PathVariable Long id) throws IOException {
    List<MetadataModel> works = doiService.findAllWorksByDois(requests.stream().map(r -> r.getDoi()).collect(Collectors.toList()));
    projectService.saveWorksToProject(works, id, false);
    return works;
  }

  @GetMapping
  public List<Project> getProjects() {
    List<Project> proj = projectService.getAllProjects();
    proj.stream().forEach(p -> p.setWorks(null)); // TODO: hack
    return proj;
  }

  @GetMapping("/{id}/works")
  public List<MetadataModel> getProjectWorks(@PathVariable Long id) {
    List<MetadataModel> works = projectService.getProjectWorks(id);
    return works;
  }

  @PutMapping("/{id}/query")
  public List<MetadataModel> saveQueryToProject(@PathVariable Long id, @RequestBody QueryRequest request) throws Exception {
    if (request.getSites() == null || request.getSites().size() == 0) { request.setSites(Collections.singletonList(Sites.ieee)); }
    List<BaseModel> models = queryService.runQuery(request.getQuery(), request.getSites());
    List<MetadataModel> works = models.stream().map(m -> m.toMetadata()).collect(Collectors.toList());
    List<MetadataModel> results = projectService.saveWorksToProject(works, id, request.isDownloadPapers());
    return results;
  }

  @PostMapping
  public Project createProject(@RequestBody Project model) {
    Project project = projectService.createProject(model);
    return project;
  }

  @GetMapping(value = "/{id}/exportkeywords", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public ResponseEntity<byte[]> exportProjectKeywords(@PathVariable long id) {
    try {
      byte[] bytes = projectService.exportProjectKeywordsCsv(id);
      InputStream is = new ByteArrayInputStream(bytes);
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.set("charset", "utf-8");
      responseHeaders.setContentType(MediaType.valueOf("text/csv"));
      responseHeaders.setContentLength(bytes.length);
      responseHeaders.set("Content-disposition", "attachment; filename=keywords.csv");
      return new ResponseEntity<byte[]>(bytes, responseHeaders, HttpStatus.OK);
    } catch (Exception ex) {
      log.info("Error writing content to output stream.", ex);
      throw new RuntimeException("IOError writing content to output stream");
    }
  }
}
