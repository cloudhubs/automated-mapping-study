package edu.baylor.ecs.ams.runner;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.impl.IEEEModel;
import edu.baylor.ecs.ams.parser.export.impl.IEEEExportParser;
import edu.baylor.ecs.ams.selenium.SeleniumWrapper;
import edu.baylor.ecs.ams.selenium.impl.IEEESeleniumWrapper;
import io.github.crew102.rapidrake.model.Result;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IEEERunner {
  private static String DRIVER_PATH = "driver/chromedriver";

  public static List<BaseModel> runQuery(String query) throws InterruptedException, IOException {
    System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

    SeleniumWrapper wrapper = new IEEESeleniumWrapper();
    wrapper.toSite()
            .sendQuery(query)
            .filterResults()
            .exportResults()
            .pause(2000) // Wait for download to finish
            .quit();

    // parser for downloaded CSV
    IEEEExportParser exportParser = new IEEEExportParser();
    List<BaseModel> results = new ArrayList<>();
    try {
      results = exportParser.parseFile();
    } catch(Exception e) {
      // TODO: make a better exception type
      throw e;
    }

    return results;
  }
}
