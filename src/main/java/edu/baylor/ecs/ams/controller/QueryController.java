package edu.baylor.ecs.ams.controller;

import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.request.StoreDoiRequest;
import edu.baylor.ecs.ams.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class QueryController {
    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @PostMapping("/query")
    public List<MetadataModel> query(@RequestParam String query) throws Exception {
        log.info("Query: " + query);
//        Runner.run(query);
        List<BaseModel> models = queryService.runQuery(query);
        List<MetadataModel> results = models.stream().map(m -> m.toMetadata()).collect(Collectors.toList());
        return results;
    }

    @PostMapping("/save")
    public List<MetadataModel> saveDois(@RequestBody List<StoreDoiRequest> requests) {

        return new ArrayList<>();
    }
}
