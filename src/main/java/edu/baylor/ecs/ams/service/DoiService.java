package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.repository.MetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoiService {

  private final MetadataRepository metadataRepository;

  /**
   * Finds works by their DOIs, searching the local store first, then querying if necessary
   * @param dois
   * @return
   */
  public List<MetadataModel> findAllWorksByDois(List<String> dois) {
    List<String> doisToQuery = new ArrayList<>();
    List<MetadataModel> localWorks = new ArrayList<>();
    for (String doi : dois) {
      Optional<MetadataModel> model = metadataRepository.getFirstByDoi(doi);
      if (model.isPresent()) {
        localWorks.add(model.get());
      } else {
        doisToQuery.add(doi);
      }
    }

    List<MetadataModel> newWorks = queryByDois(dois);
    newWorks = metadataRepository.saveAll(newWorks);

    localWorks.addAll(newWorks);
    return localWorks;
  }

  /**
   * Fetch works by DOI by querying a remote source
   * @param dois
   * @return
   */
  public List<MetadataModel> queryByDois(List<String> dois) {
    // TODO: actually query the source
    return dois.stream().map(doi -> {
      MetadataModel model = new MetadataModel();
      model.setDoi(doi);
      model.setAuthors("V. Bushong");
      model.setDate("01-01-1996");
      model.setDocumentTitle("A Work about Working on Works");
      model.setIsbns("Some ISBN numbers");
      model.setIssn("An ISSN");
      return model;
    }).collect(Collectors.toList());
  }
}
