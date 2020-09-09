package edu.baylor.ecs.ams.parser.export;

import edu.baylor.ecs.ams.model.BaseModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class BaseExportParser {

    protected final File getFile(String downloadPath) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.err.println("No downloaded files found");
            return null;
        }

        // Find first CSV file
        File lastModifiedFile = null;
        for (File file : files) {
            if (file.getName().endsWith(".csv")) {
                lastModifiedFile = file;
                break;
            }
        }

        if (lastModifiedFile == null) {
            System.err.println("No CSV files found");
            return null;
        }

        // Find latest CSV file
        for (File file : files) {
            if (lastModifiedFile.lastModified() < file.lastModified() && file.getName().endsWith(".csv")) {
                lastModifiedFile = file;
            }
        }

        return lastModifiedFile;
    }

    public abstract List<BaseModel> parseFile() throws IOException;
}
