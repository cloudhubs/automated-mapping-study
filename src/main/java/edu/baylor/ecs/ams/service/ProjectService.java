package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.Keyword;
import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.Project;
import edu.baylor.ecs.ams.parser.pdf.impl.IEEEPDFParser;
import edu.baylor.ecs.ams.repository.KeywordRepository;
import edu.baylor.ecs.ams.repository.MetadataRepository;
import edu.baylor.ecs.ams.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
  private final TextService textService;

  public List<MetadataModel> getProjectWorks(Long projectId) {
    Project project = projectRepository.getProjectAndWorksById(projectId).orElse(new Project());
    return project.getWorks();
  }

  public List<MetadataModel> saveWorksToProject(List<MetadataModel> works, Long projectId, boolean saveFullText) throws IOException {
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
//            work.setAuthorKeywords(keywordRepository.saveAll(work.getAuthorKeywords()));
            savedWork = metadataRepository.save(work);
          }
          project.addWork(savedWork);
        }
      }

      // parse abstracts if needed
      for (MetadataModel work : project.getWorks()) {
        if (work.getAbstractKeywords() == null || work.getAbstractKeywords().size() == 0) {
          List<Keyword> keywords = textService.extractKeywords(work.getWorkAbstract());
          work.setAbstractKeywords(keywordRepository.saveAll(keywords));
        }
      }
      project.setWorks(metadataRepository.saveAll(project.getWorks()));

      // download full texts if needed
      if (saveFullText) {
        IEEEPDFParser pdfParser = new IEEEPDFParser();
        for (MetadataModel work : project.getWorks()) {
          if (!work.isHasFullText()) {
            Path filename = Paths.get("downloads", "fulltext", work.getDoi().replace("/", "_") + ".txt");
            File textFile = new File(filename.toUri());
            // parse the full text
            String fullText = pdfParser.parsePDF(work);
            work.setHasFullText(true);
            work.setFullTextPath(filename.toAbsolutePath().toString());
            // extract keywords
            List<Keyword> keywords = textService.extractKeywords(fullText);
            work.setExtractedKeywords(keywordRepository.saveAll(keywords));
            // create file if it doesn't exist
            if (!textFile.exists()) {
              FileWriter writer = new FileWriter(textFile);
              writer.write(fullText);
              textFile.createNewFile();
            }
          }
        }
        project.setWorks(metadataRepository.saveAll(project.getWorks()));
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

  public List<Project> getAllProjects() {
    return projectRepository.findAll();
  }
}
