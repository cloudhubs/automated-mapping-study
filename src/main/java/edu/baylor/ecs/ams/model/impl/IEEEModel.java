package edu.baylor.ecs.ams.model.impl;

import edu.baylor.ecs.ams.model.Keyword;
import edu.baylor.ecs.ams.model.MetadataModel;
import edu.baylor.ecs.ams.model.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
@Setter
public class IEEEModel extends BaseModel {
    private String documentTitle; // A Dashboard for Microservice Monitoring and Management
    private List<String> authors; // B. Mayer; R. Weinreich
    private List<String> authorAffiliations; // Johannes Kepler Univ. Linz, Linz, Austria; Johannes Kepler Univ. Linz, Linz, Austria
    private String publicationTitle; // 2017 IEEE International Conference on Software Architecture Workshops (ICSAW)
    private String dateAddedToXplore; // 26 Jun 2017
    private int publicationYear; // 2017
    private String volume; // ""
    private String issue; // ""
    private String startPage; // 66
    private String endPage; // 69
    private String abstractText; // We present an experimental dashboard for microservice monitoring and management ...
    private String ISSN; // ""
    private List<String> ISBNs; // 978-1-5090-4793-2
    private String DOI; // 10.1109/ICSAW.2017.44
    private String fundingInformation; // ""
    private List<String> authorKeywords; // Microservices;microservice monitoring;microservice management;microservice dashboard
    private List<String> IEEETerms; // Conferences;Software architecture
    private List<String> INSPECControlledTerms; // data acquisition;data integration;service-oriented architecture;system monitoring
    private List<String> INSPECNonControlledTerms; // microservice monitoring;experimental dashboard;microservice management;monitoring infrastructures;microservice runtime data collection;information sources;microservice development
    private List<String> meshTerms; // ""
    private int citationCount; // 7
    private int referenceCount; // 8
    private String license; // ""
    private String onlineDate; // 26 Jun 2017
    private String issueDate; // ""
    private String meetingDate; // ""
    private String publisher; // "IEEE"
    private String documentIdentifier; // "IEEE Conferences"

    @Override
    public MetadataModel toMetadata() {
        MetadataModel work = new MetadataModel();
        work.setDoi(DOI != null && !DOI.isBlank() ? DOI : documentTitle);
        work.setAuthors(String.join(";", authors));
        work.setDocumentTitle(documentTitle);
        work.setPublicationTitle(publicationTitle);
        work.setDate(dateAddedToXplore);
//        dto.setAuthorKeywords(authorKeywords.stream().map(kw -> new Keyword(kw.toLowerCase())).collect(Collectors.toList()));
        work.setIssn(ISSN);
        work.setIsbns(String.join("; ", ISBNs));
        work.setPdfLink(pdfLink);
        work.setWorkAbstract(abstractText);
        return work;
    }
}
