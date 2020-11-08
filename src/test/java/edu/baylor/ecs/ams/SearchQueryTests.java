package edu.baylor.ecs.ams;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.runner.IEEERunner;
import edu.baylor.ecs.ams.runner.ScienceDirectRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class SearchQueryTests {

    @Disabled
    @Test
    void ieeeSearchQuery() throws IOException, InterruptedException {
        String query = "((\"Abstract\":code smells) AND \"Full Text Only\":microservice)";
        List<BaseModel> results = IEEERunner.runQuery(query);
        assertTrue(results.size() > 0);
        log.info(String.valueOf(results.get(0).toMetadata()));
    }

    @Disabled
    @Test
    void sdSearchQuery() {
        String query = "keywords(microservice) AND keywords(container)";
        List<BaseModel> results = ScienceDirectRunner.runQuery(query);
        assertTrue(results.size() > 0);
        log.info(String.valueOf(results.get(0).toMetadata()));
    }

    @Test
    void sdSearchQueryExport() {
        String query = "keywords(microservice) AND keywords(container)";
        ScienceDirectRunner.runQueryExport(query, true);
    }

}
