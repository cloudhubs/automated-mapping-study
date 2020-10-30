package edu.baylor.ecs.ams;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.runner.IEEERunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class IEEETests {

    @Disabled
    @Test
    void ieeeSearchQuery() throws IOException, InterruptedException {
        String query = "((\"Abstract\":code smells) AND \"Full Text Only\":microservice)";
        List<BaseModel> results = IEEERunner.runQuery(query);
        log.info(String.valueOf(results));
        assertTrue(results.size() > 0);
    }

}
