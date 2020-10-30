package edu.baylor.ecs.ams.parser.pdf.impl;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.impl.IEEEModel;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import edu.baylor.ecs.ams.parser.pdf.BasePDFParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;

public class IEEEPDFParser extends BasePDFParser {

    private static String PDF_DOWNLOAD_PATH = "downloads" + File.separator + "buffer";

    // IEEE needs to use Selenium to download file because it serves the file through a .jsp link. If the indexer
    // provides a direct PDF link then there are better ways to download.
    private ChromeDriver driver;

    public IEEEPDFParser() {
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
        String url = "https://ieeexplore.ieee.org/stampPDF/getPDF.jsp?tp=&arnumber="
                + model.getPdfLink().substring(model.getPdfLink().lastIndexOf("=") + 1) + "&ref=";
        driver.get(url);

        return true;
    }

    @Override
    public String parsePDF(BaseModel model) {
        String url = "https://ieeexplore.ieee.org/stampPDF/getPDF.jsp?tp=&arnumber="
                + model.getPdfLink().substring(model.getPdfLink().lastIndexOf("=") + 1) + "&ref=";
        driver.get(url);

        String parsedText;
        File dir = new File("downloads/buffer");
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.err.println("No downloaded files found");
            return null;
        }

        // Find first pdf file
        File lastModifiedFile = null;
        for (File file : files) {
            if (file.getName().endsWith(".pdf")) {
                lastModifiedFile = file;
                break;
            }
        }

        if (lastModifiedFile == null) {
            System.err.println("No PDF files found");
            return null;
        }

        // Find latest pdf file
        for (File file : files) {
            if (lastModifiedFile.lastModified() < file.lastModified() && file.getName().endsWith(".pdf")) {
                lastModifiedFile = file;
            }
        }

        PDFParser pdfParser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;

        try {
            pdfParser = new PDFParser(new RandomAccessBufferedFileInputStream(new FileInputStream(lastModifiedFile)));
            pdfParser.parse();
            cosDoc = pdfParser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            return parsedText.replaceAll("[^A-Za-z0-9.\n ]+", "");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        driver.close();
        return null;
    }

    @Override
    public String parsePDF(MetadataModel model) {
        String url = "https://ieeexplore.ieee.org/stampPDF/getPDF.jsp?tp=&arnumber="
                + model.getPdfLink().substring(model.getPdfLink().lastIndexOf("=") + 1) + "&ref=";
        driver.get(url);

        String parsedText;
        File dir = new File("downloads/buffer");
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.err.println("No downloaded files found");
            return null;
        }

        // Find first pdf file
        File lastModifiedFile = null;
        for (File file : files) {
            if (file.getName().endsWith(".pdf")) {
                lastModifiedFile = file;
                break;
            }
        }

        if (lastModifiedFile == null) {
            System.err.println("No PDF files found");
            return null;
        }

        // Find latest pdf file
        for (File file : files) {
            if (lastModifiedFile.lastModified() < file.lastModified() && file.getName().endsWith(".pdf")) {
                lastModifiedFile = file;
            }
        }

        PDFParser pdfParser = null;
        PDDocument pdDoc = null;
        COSDocument cosDoc = null;
        PDFTextStripper pdfStripper;

        try {
            pdfParser = new PDFParser(new RandomAccessBufferedFileInputStream(new FileInputStream(lastModifiedFile)));
            pdfParser.parse();
            cosDoc = pdfParser.getDocument();
            pdfStripper = new PDFTextStripper();
            pdDoc = new PDDocument(cosDoc);
            parsedText = pdfStripper.getText(pdDoc);
            return parsedText.replaceAll("[^A-Za-z0-9.\n ]+", "");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (cosDoc != null)
                    cosDoc.close();
                if (pdDoc != null)
                    pdDoc.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        driver.close();
        return null;
    }
}
