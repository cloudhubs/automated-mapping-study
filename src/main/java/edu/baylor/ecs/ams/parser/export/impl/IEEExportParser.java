package edu.baylor.ecs.ams.parser.export.impl;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.impl.IEEEModel;
import edu.baylor.ecs.ams.parser.export.BaseExportParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IEEExportParser extends BaseExportParser {

    public static String IEEE_DOWNLOAD_PATH = "downloads/exports/ieee";

    @Override
    public List<BaseModel> parseFile() throws IOException {
        File export = getFile(IEEE_DOWNLOAD_PATH);

        Reader in = new FileReader(export.getAbsolutePath());
        String str = IOUtils.toString(in);
        in.close();

//        str = str.substring(str.indexOf("\n") + 1);
//        str = str.replaceAll(",NA", ",\"NA");
//        str = str.replaceAll("NA,", "NA\",");
//        str = str.replaceAll("([a-zA-Z0-9.áó]+)\",([a-zA-Z0-9.áó]+)", "$1\",\"$2");
//        str = str.replaceAll("([a-zA-Z0-9.áó]+),\"([a-zA-Z0-9.áó]+)", "$1\",\"$2");
//        str = str.replaceAll(",,", ",\"\",");
//        str = str.replaceAll("\",\"", "DELIMITER_A");
//        str = str.replaceAll("\"\n\"", "DELIMITER_B");
//        str = str.replaceAll("\"", "");
//        str = str.replaceAll("\"\"", "");
//        str = str.replaceAll("DELIMITER_A", "\",\"");
//        str = str.replaceAll("DELIMITER_B", "\"\n\"");
//        str = str.concat("\"");
//        str = "\"".concat(str);
//        String header = "\"Document Title\",\"Authors\",\"Author Affiliations\",\"Publication Title\",\"Date Added To Xplore\",\"Publication Year\",\"Volume\",\"Issue\",\"Start Page\",\"End Page\",\"Abstract\",\"ISSN\",\"ISBNs\",\"DOI\",\"Funding Information\",PDF Link,\"Author Keywords\",\"IEEE Terms\",\"INSPEC Controlled Terms\",\"INSPEC Non-Controlled Terms\",\"Mesh_Terms\",\"Article Citation Count\",\"Reference Count\",\"License\",\"Online Date\",\"Issue Date\",\"Meeting Date\",\"Publisher\",\"Document Identifier\"";
//        str = header.concat("\n").concat(str);

        Reader reader = new CharSequenceReader(str);
        List<CSVRecord> records = CSVFormat.EXCEL.withIgnoreEmptyLines().withFirstRecordAsHeader().parse(reader).getRecords();

        return records.stream()
                .map(record -> {
                    IEEEModel model = new IEEEModel();
                    model.setDocumentTitle(record.get("Document Title"));
                    model.setAuthors(record.get("Authors").isEmpty() ? new ArrayList<>() : Arrays.asList(record.get("Authors").split(";")));
                    model.setAuthorAffiliations(record.get("Author Affiliations").isEmpty() ? new ArrayList<>() : Arrays.asList(record.get("Author Affiliations").split(";")));
                    model.setPublicationTitle(record.get("Publication Title"));
                    model.setDateAddedToXplore(record.get("Date Added To Xplore"));
                    model.setPublicationYear(record.get("Publication Year").isEmpty() ? -1 : Integer.parseInt(record.get("Publication Year")));
                    model.setVolume(record.get("Volume"));
                    model.setIssue(record.get("Issue"));
                    model.setStartPage(record.get("Start Page"));
                    model.setEndPage(record.get("End Page"));
                    model.setAbstractText(record.get("Abstract"));
                    model.setISSN(record.get("ISSN"));
                    model.setISBNs(record.get("ISBNs").isEmpty() ? new ArrayList<>() : Arrays.asList(record.get("ISBNs").split(";")));
                    model.setDOI(record.get("DOI"));
                    model.setFundingInformation(record.get("Funding Information"));
                    model.setPdfLink(record.get("PDF Link"));
                    model.setAuthorKeywords(record.get("Author Keywords").isEmpty() ? new ArrayList<>() : Arrays.asList(record.get("Author Keywords").split(";")));
                    model.setIEEETerms(record.get("IEEE Terms").isEmpty() ? new ArrayList<>() : Arrays.asList(record.get("IEEE Terms").split(";")));
                    model.setINSPECControlledTerms(record.get("INSPEC Controlled Terms").isEmpty() ? new ArrayList<>() : Arrays.asList(record.get("INSPEC Controlled Terms").split(";")));
                    model.setINSPECNonControlledTerms(record.get("INSPEC Non-Controlled Terms").isEmpty() ? new ArrayList<>() : Arrays.asList(record.get("INSPEC Non-Controlled Terms").split(";")));
                    model.setMeshTerms(record.get("Mesh_Terms").isEmpty() ? new ArrayList<>() : Arrays.asList(record.get("Mesh_Terms").split(";")));
                    model.setCitationCount(record.get("Article Citation Count").isEmpty() ? -1 : Integer.parseInt(record.get("Article Citation Count")));
                    model.setReferenceCount(record.get("Reference Count").isEmpty() ? -1 : Integer.parseInt(record.get("Reference Count")));
                    model.setLicense(record.get("License"));
                    model.setOnlineDate(record.get("Online Date"));
                    model.setIssueDate(record.get("Issue Date"));
                    model.setMeetingDate(record.get("Meeting Date"));
                    model.setPublisher(record.get("Publisher"));
                    model.setDocumentIdentifier(record.get("Document Identifier"));
                    return model;
                })
                .collect(Collectors.toList());
    }
}
