package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.parser.export.impl.IEEEExportParser;
import edu.baylor.ecs.ams.parser.pdf.impl.IEEEPDFParser;
import edu.baylor.ecs.ams.selenium.SeleniumWrapper;
import edu.baylor.ecs.ams.selenium.impl.IEEESeleniumWrapper;
import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;

import java.util.List;

public class Runner {

    private static String DRIVER_PATH = "driver/chromedriver";

    public static void run(String query) throws Exception {

        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

        SeleniumWrapper wrapper = new IEEESeleniumWrapper();
        wrapper.toSite()
                .sendQuery(query)
                .filterResults()
                .exportResults()
                .pause(2000) // Wait for download to finish
                .quit();

        // Create IEEE Parsers for exported CSV and the PDFs
        IEEEExportParser exportParser = new IEEEExportParser();
        IEEEPDFParser pdfParser = new IEEEPDFParser();

        // Create an object to hold algorithm parameters
        String[] stopWords = new SmartWords().getSmartWords();
        String[] stopPOS = {"VB", "VBD", "VBG", "VBN", "VBP", "VBZ"};
        int minWordChar = 1;
        boolean shouldStem = true;
        String phraseDelims = "[-,.?():;\"!/]";
        RakeParams params = new RakeParams(stopWords, stopPOS, minWordChar, shouldStem, phraseDelims);

        // Create a RakeAlgorithm object
        String POStaggerURL = "model-bin/en-pos-maxent.bin"; // The path to your POS tagging model
        String SentDetecURL = "model-bin/en-sent.bin"; // The path to your sentence detection model
        RakeAlgorithm rakeAlg = new RakeAlgorithm(params, POStaggerURL, SentDetecURL);

        List<BaseModel> models = exportParser.parseFile();
        for (BaseModel model : models) {
            String text = pdfParser.parsePDF(model);
            // Call the rake method
            Result result = rakeAlg.rake(text);
            // Print the result
            System.out.println(result.distinct());
        }
    }
}
