package edu.baylor.ecs.ams.repository;

import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MetadataRepository extends JpaRepository<MetadataModel, Long> {
  Optional<MetadataModel> getFirstByDoi(String doi);

  @Query(name = MetadataModel.GET_WORK_AND_AUTHOR_KEYWORDS_BY_ID)
  Optional<MetadataModel> getWorkAndAuthorKeywordsById(Long id);

  @Query(name = MetadataModel.GET_WORK_AND_ABSTRACT_KEYWORDS_BY_ID)
  Optional<MetadataModel> getWorkAndAbstractKeywordsById(Long id);

  @Query(name = MetadataModel.GET_WORK_AND_EXTRACTED_KEYWORDS_BY_ID)
  Optional<MetadataModel> getWorkAndExtractedKeywordsById(Long id);

  @Query(name = MetadataModel.GET_ALL_WORKS_AND_AUTHOR_KEYWORDS_BY_ID)
  List<MetadataModel> getAllWorksAndAuthorKeywords();

  @Query(name = MetadataModel.GET_ALL_WORKS_AND_ABSTRACT_KEYWORDS_BY_ID)
  List<MetadataModel> getAllWorksAndAbstractKeywordsById();

  @Query(name = MetadataModel.GET_ALL_WORKS_AND_EXTRACTED_KEYWORDS_BY_ID)
  List<MetadataModel> getAllWorksAndExtractedKeywordsById();
}
