package edu.baylor.ecs.ams.runner;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.ieeeapi.Article;
import edu.baylor.ecs.ams.model.ieeeapi.Articles;
import edu.baylor.ecs.ams.parser.export.impl.IEEExportParser;
import edu.baylor.ecs.ams.parser.pdf.impl.IEEEPDFParser;
import edu.baylor.ecs.ams.selenium.SeleniumWrapper;
import edu.baylor.ecs.ams.selenium.impl.IEEESeleniumWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IEEERunner {
    private static String DRIVER_PATH = "driver" + File.separator + "chromedriver"
            + (System.getProperty("os.name").toLowerCase().contains("windows") ? ".exe" : "");

    public static List<BaseModel> runQuery(String query) throws InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

        String apiKey = System.getenv("IEEE_API_KEY");
        if (apiKey != null && !apiKey.isEmpty()) {
            log.info("Using IEEE API");
            return ieeeAPIQuery(apiKey, query);
        }

        log.info("IEEE_API_KEY is not defined, using Selenium");

        SeleniumWrapper wrapper = new IEEESeleniumWrapper();
        wrapper.toSite()
                .sendQuery(query)
                .filterResults()
                .exportResults()
                .pause(2000) // Wait for download to finish
                .quit();

        // parse exported CSV
        return new IEEExportParser().parseFile();
    }

    public static void runQueryExport(String query, boolean downloadFiles) throws InterruptedException, IOException {
        List<BaseModel> models = runQuery(query);

        // Create IEEE Parsers for downloaded PDFs
        IEEEPDFParser pdfParser = new IEEEPDFParser();

        if (downloadFiles) {
            for (BaseModel model : models) {
                pdfParser.downloadPDF(model);
            }
        }
    }

    public static List<BaseModel> ieeeAPIQuery(String apiKey, String query) {
        String baseUrl = "http://ieeexploreapi.ieee.org/api/v1/search/articles";

        URI uri = UriComponentsBuilder.fromUriString(baseUrl).
                queryParam("apikey", apiKey).
                queryParam("querytext", query).
                build().toUri();

        RestTemplate restTemplate = new RestTemplate();

        Articles articles = restTemplate.getForObject(uri, Articles.class);

        if (articles == null || articles.getArticles() == null) {
            return null;
        }

        List<BaseModel> results = new ArrayList<>();

        for (Article article : articles.getArticles()) {
            article.setPdfLink(article.getPdfUrl());
            results.add(article);
        }

        return results;
    }
}
