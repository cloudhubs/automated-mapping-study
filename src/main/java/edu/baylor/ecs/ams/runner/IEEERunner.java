package edu.baylor.ecs.ams.runner;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.parser.export.impl.IEEExportParser;
import edu.baylor.ecs.ams.parser.pdf.impl.IEEEPDFParser;
import edu.baylor.ecs.ams.selenium.SeleniumWrapper;
import edu.baylor.ecs.ams.selenium.impl.IEEESeleniumWrapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IEEERunner {
  private static String DRIVER_PATH = "driver" + File.separator + "chromedriver"
          + (System.getProperty("os.name").toLowerCase().contains("windows") ? ".exe" : "");

  public static List<BaseModel> runQuery(String query) throws InterruptedException, IOException {
    System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

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
}
