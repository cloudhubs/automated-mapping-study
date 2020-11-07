package edu.baylor.ecs.ams.runner;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.sdapi.SDModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ScienceDirectRunner {
    private static String DRIVER_PATH = "driver" + File.separator + "chromedriver"
            + (System.getProperty("os.name").toLowerCase().contains("windows") ? ".exe" : "");

    public static List<BaseModel> runQuery(String query) {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

        String apiKey = System.getenv("SD_API_KEY");
        if (apiKey != null && !apiKey.isEmpty()) {
            log.info("Using ScienceDirect API");
            return sdAPIQuery(apiKey, query);
        }

        return new ArrayList<>();

//        log.info("SD_API_KEY is not defined, using Selenium");

//        SeleniumWrapper wrapper = new IEEESeleniumWrapper();
//        wrapper.toSite()
//                .sendQuery(query)
//                .filterResults()
//                .exportResults()
//                .pause(2000) // Wait for download to finish
//                .quit();
//
//        // parse exported CSV
//        return new IEEExportParser().parseFile();
    }

//    public static void runQueryExport(String query, boolean downloadFiles) throws InterruptedException, IOException {
//        List<BaseModel> models = runQuery(query);
//
//        // Create ScienceDirect Parsers for downloaded PDFs
//        SDPDFParser pdfParser = new SDPDFParser();
//
//        if (downloadFiles) {
//            for (BaseModel model : models) {
//                pdfParser.downloadPDF(model);
//            }
//        }
//    }

    public static List<BaseModel> sdAPIQuery(String apiKey, String query) {
        String baseUrl = "https://api.elsevier.com/content/search/sciencedirect";

        URI uri = UriComponentsBuilder.fromUriString(baseUrl).
                queryParam("apiKey", apiKey).
                build().toUri();

        RestTemplate restTemplate = new RestTemplate();

        List<BaseModel> results = new ArrayList<>();

        // loop through pages, each page contains maximum 100 records
        while (true) {
            ResponseEntity<SDModel> response = restTemplate.exchange(
                    uri,
                    HttpMethod.PUT,
                    getRequestEntity(query, results.size()),
                    SDModel.class
            );

            SDModel sdModel = response.getBody();

            if (response.getStatusCode() != HttpStatus.OK || sdModel == null) {
                break;
            }

            results.addAll(sdModel.getResults());

            log.info(results.size() + " of " + sdModel.getResultsFound());

            if (results.size() >= sdModel.getResultsFound()) {
                break;
            }
        }

        return results;
    }

    private static HttpEntity<String> getRequestEntity(String query, int offset) {
        String queryBody = "{\"qs\":\"#query\",\"display\":{\"offset\":#offset,\"show\":100}}";
        queryBody = queryBody.replace("#query", query).replace("#offset", String.valueOf(offset));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new HttpEntity<>(queryBody, headers);
    }
}
