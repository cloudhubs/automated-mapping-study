package edu.baylor.ecs.ams.repository;

import edu.baylor.ecs.ams.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, String> {
}
