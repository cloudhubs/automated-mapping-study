package edu.baylor.ecs.ams.repository;

import edu.baylor.ecs.ams.model.MetadataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MetadataRepository extends JpaRepository<MetadataModel, Long> {
  Optional<MetadataModel> getFirstByDoi(String doi);
}
