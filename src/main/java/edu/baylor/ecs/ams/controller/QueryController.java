package edu.baylor.ecs.ams.controller;

import edu.baylor.ecs.ams.service.Runner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class QueryController {
    @PostMapping("/query")
    public List<String> query(@RequestParam String query) throws Exception {
        log.info("Query: " + query);
        Runner.run(query);
        return new ArrayList<>();
    }
}
