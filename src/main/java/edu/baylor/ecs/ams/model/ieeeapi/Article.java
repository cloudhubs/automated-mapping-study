package edu.baylor.ecs.ams.model.ieeeapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.Keyword;
import edu.baylor.ecs.ams.model.MetadataModel;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class Article extends BaseModel {

    @XmlElement(name = "rank")
    @JsonProperty("rank")
    private Integer rank;

    @XmlElement(name = "accessType")
    @JsonProperty("access_type")
    private String accessType;

    @XmlElement(name = "content_type")
    @JsonProperty("content_type")
    private String contentType;

    @XmlElement(name = "article_number")
    @JsonProperty("article_number")
    private String articleNumber;

    @XmlElement(name = "doi")
    @JsonProperty("doi")
    private String doi;

    @XmlElement(name = "title")
    @JsonProperty("title")
    private String title;

    @XmlElement(name = "publication_number")
    @JsonProperty("publication_number")
    private String publicationNumber;

    @XmlElement(name = "publication_title")
    @JsonProperty("publication_title")
    private String publicationTitle;

    @XmlElement(name = "isbn")
    @JsonProperty("isbn")
    private String isbn;

    @XmlElement(name = "publisher")
    @JsonProperty("publisher")
    private String publisher;

    @XmlElement(name = "citing_paper_count")
    @JsonProperty("citing_paper_count")
    private String citingPaperCount;

    @XmlElement(name = "pdf_url")
    @JsonProperty("pdf_url")
    private String pdfUrl;

    @XmlElement(name = "abstract_url")
    @JsonProperty("abstract_url")
    private String abstractUrl;

    @XmlElement(name = "html_url")
    @JsonProperty("html_url")
    private String htmlUrl;

    @XmlElement(name = "conference_location")
    @JsonProperty("conference_location")
    private String conferenceLocation;

    @XmlElement(name = "conference_dates")
    @JsonProperty("conference_dates")
    private String conferenceDates;

    @XmlElement(name = "partnum")
    @JsonProperty("partnum")
    private String partnum;

    @XmlElement(name = "start_page")
    @JsonProperty("start_page")
    private String startPage;

    @XmlElement(name = "end_page")
    @JsonProperty("end_page")
    private String endPage;

    @XmlElement(name = "abstract")
    @JsonProperty("abstract")
    private String abstractProp;

    @XmlElement(name = "index_terms")
    @JsonProperty("index_terms")
    private IndexTerms indexTerms;

    @XmlElement(name = "authors")
    @JsonProperty("authors")
    private Authors authors;

    @Override
    public String toString() {
        return "Article [rank=" + rank + ", accessType=" + accessType + ", contentType=" + contentType
                + ", articleNumber=" + articleNumber + ", doi=" + doi + ", title=" + title + ", publicationNumber="
                + publicationNumber + ", publicationTitle=" + publicationTitle + ", isbn=" + isbn + ", publisher="
                + publisher + ", citingPaperCount=" + citingPaperCount + ", pdfUrl=" + pdfUrl + ", abstractUrl="
                + abstractUrl + ", htmlUrl=" + htmlUrl + ", conferenceLocation=" + conferenceLocation
                + ", conferenceDates=" + conferenceDates + ", partnum=" + partnum + ", startPage=" + startPage
                + ", endPage=" + endPage + ", abstractProp=" + abstractProp + ", indexTerms=" + indexTerms
                + ", authors=" + authors + "]";
    }

    public MetadataModel toMetadata() {
        MetadataModel work = new MetadataModel();

        work.setDoi(doi);
        work.setDocumentTitle(title);
        work.setPublicationTitle(publicationTitle);
        work.setDate(conferenceDates);
        // work.setIssn(issn);
        work.setIsbns(isbn);
        work.setPdfLink(pdfUrl);
        work.setWorkAbstract(abstractProp);

        work.setAuthors(authors.getAuthors()
                .stream().map(
                        Author::getFullName
                ).collect(Collectors.joining(";"))
        );

        work.setAuthorKeywords(indexTerms.getAuthorTerms().getTerms()
                .stream().map(
                        kw -> new Keyword(kw.toLowerCase())
                ).collect(Collectors.toList())
        );

        return work;
    }
}
