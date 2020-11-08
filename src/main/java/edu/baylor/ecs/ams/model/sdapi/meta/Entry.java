package edu.baylor.ecs.ams.model.sdapi.meta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.Keyword;
import edu.baylor.ecs.ams.model.MetadataModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry extends BaseModel {
    @JsonProperty("prism:doi")
    String doi;

    @JsonProperty("pii")
    String pii; // Publication Item Identifier

    @JsonProperty("prism:issn")
    String issn;

    @JsonProperty("prism:coverDate")
    String publicationDate;

    @JsonProperty("dc:title")
    String title;

    @JsonProperty("prism:publicationName")
    String sourceTitle;

    @JsonProperty("dc:description")
    String description;

    @JsonProperty("authkeywords")
    String authorKeywords;

    @JsonProperty("openaccessArticle")
    Boolean openAccess;

    @JsonProperty("prism:url")
    String uri;

    @JsonProperty("authors")
    AuthorsWrapper authorsWrapper;

    @Override
    public MetadataModel toMetadata() {
        MetadataModel work = new MetadataModel();

        work.setDoi(doi);
        work.setDocumentTitle(title);
        work.setPublicationTitle(sourceTitle);
        work.setDate(publicationDate);
        work.setIssn(issn);
        work.setWorkAbstract(description);

//        work.setIsbns(isbn);
//        work.setPdfLink(pdfUrl);

        if (authorsWrapper != null && authorsWrapper.getAuthors() != null) {
            work.setAuthors(authorsWrapper.getAuthors().stream().map(
                    Author::getName
                    ).collect(Collectors.joining(";"))
            );
        }

        if (authorKeywords != null) {
            work.setAuthorKeywords(Arrays.stream(authorKeywords.split(" | ")).map(
                    kw -> new Keyword(kw.toLowerCase())
                    ).collect(Collectors.toList())
            );
        }

        return work;
    }
}
