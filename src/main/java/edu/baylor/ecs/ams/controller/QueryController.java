package edu.baylor.ecs.ams.controller;

import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.request.QueryRequest;
import edu.baylor.ecs.ams.service.QueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QueryController {
    private final QueryService queryService;

//    @PostMapping("/query")
//    public List<MetadataModel> query(@RequestParam String query) throws Exception {
//        List<BaseModel> models = queryService.runQuery(query);
//        List<MetadataModel> results = models.stream().map(m -> m.toMetadata()).collect(Collectors.toList());
//        return results;
//    }

    @PostMapping("/queryexport")
    public String queryAndExport(@RequestBody QueryRequest request) {
        try {
            queryService.exportQuery(request.getQuery(), request.isDownloadPapers());
        } catch (Exception e) {
            return "Export failed!";
        }

        String message = "CSV file located at {project root}/downloads/exports/ieee";

        if (request.isDownloadPapers()) {
            message = message + ", and PDF files located at {project root}/downloads/buffer";
        }

        return message;
    }

}
