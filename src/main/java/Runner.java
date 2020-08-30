import io.github.crew102.rapidrake.RakeAlgorithm;
import io.github.crew102.rapidrake.data.SmartWords;
import io.github.crew102.rapidrake.model.RakeParams;
import io.github.crew102.rapidrake.model.Result;
import model.BaseModel;
import parser.export.impl.IEEEExportParser;
import parser.pdf.impl.IEEEPDFParser;
import selenium.SeleniumWrapper;
import selenium.impl.IEEESeleniumWrapper;

import java.util.List;

public class Runner {

    private static String DRIVER_PATH = "driver\\chromedriver.exe";

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);

        SeleniumWrapper wrapper = new IEEESeleniumWrapper();
        wrapper.toSite()
                .sendQuery("microservices")
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
        for(BaseModel model : models){
            String text = pdfParser.parsePDF(model);
            // Call the rake method
            Result result = rakeAlg.rake(text);
            // Print the result
            System.out.println(result.distinct());
        }
    }
}
