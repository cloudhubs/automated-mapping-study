package edu.baylor.ecs.ams.parser.pdf.impl;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.parser.pdf.BasePDFParser;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;

@Slf4j
public class SDPDFParser extends BasePDFParser {

    private static String PDF_DOWNLOAD_PATH = "downloads" + File.separator + "buffer";

    // IEEE needs to use Selenium to download file because it serves the file through a .jsp link. If the indexer
    // provides a direct PDF link then there are better ways to download.
    private ChromeDriver driver;

    public SDPDFParser() {
        String currentWorkingDir = System.getProperty("user.dir");
        System.out.println(currentWorkingDir);
        Path rootPath = Paths.get(currentWorkingDir);
        Path fullPath = rootPath.resolve(PDF_DOWNLOAD_PATH);
        String pathString = fullPath.toAbsolutePath().toString();
        System.out.println(pathString);

        // Download PDF to buffer
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> preferences = new Hashtable<String, Object>();
        preferences.put("profile.default_content_settings.popups", 0);
        preferences.put("download.prompt_for_download", false);
        preferences.put("download.directory_upgrade", true);
        preferences.put("download.default_directory", pathString);
        preferences.put("plugins.always_open_pdf_externally", true);

        options.setExperimentalOption("prefs", preferences);

        // launch the browser and navigate to the page
        this.driver = new ChromeDriver(options);

    }

    public boolean downloadPDF(BaseModel model) {
        log.info("Downloading pdf: " + model.toMetadata().getPdfLink());
        driver.get(model.toMetadata().getPdfLink());
        return true;
    }

    @Override
    public String parsePDF(BaseModel model) {
        return null;
    }

    @Override
    public String parsePDF(MetadataModel model) {
        return null;
    }
}
