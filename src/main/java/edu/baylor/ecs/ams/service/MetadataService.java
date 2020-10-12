package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.repository.MetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetadataService {
  MetadataRepository metadataRepo;

//  public MetadataModel getWorkAndAllKeywords(Long id) {
//    MetadataModel model = metadataRepo.getWorkAndAuthorKeywordsById(id).orElseThrow();
//    MetadataModel modelAbstractKeywords = metadataRepo.getWorkAndAbstractKeywordsById(id).orElseThrow();
//    MetadataModel modelExtractedKeywords = metadataRepo.getWorkAndExtractedKeywordsById(id).orElseThrow();
//
//    model.setAbstractKeywords(modelAbstractKeywords.getAbstractKeywords());
//    model.setExtractedKeywords(modelExtractedKeywords.getExtractedKeywords());
//
//    return model;
//  }
//
//  public List<MetadataModel> getAllWorksAndAllKeywords() {
//
//    List<MetadataModel> models = metadataRepo.getAllWorksAndAuthorKeywords();
//    List<MetadataModel> modelsAbstractKeywords = metadataRepo.getAllWorksAndAbstractKeywordsById();
//    List<MetadataModel> modelsExtractedKeywords = metadataRepo.getAllWorksAndExtractedKeywordsById();
//    models.forEach(m -> m.setAbstractKeywords(modelsAbstractKeywords))
//
//    return models.stream().for;
//  }
}
