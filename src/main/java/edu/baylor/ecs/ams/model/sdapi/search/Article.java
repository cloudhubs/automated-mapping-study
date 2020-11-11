package edu.baylor.ecs.ams.model.sdapi.search;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.model.MetadataModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class Article extends BaseModel {
    String doi;
    String pii; // Publication Item Identifier
    String publicationDate;
    String title;
    String sourceTitle;
    Boolean openaccess;
    String uri;
    List<Author> authors;

    @Override
    public MetadataModel toMetadata() {
        MetadataModel work = new MetadataModel();

        work.setDoi(doi);
        work.setDocumentTitle(title);
        work.setPublicationTitle(sourceTitle);
        work.setDate(publicationDate);

//        work.setIssn(issn);
//        work.setIsbns(isbn);
//        work.setPdfLink(pdfUrl);
//        work.setWorkAbstract(abstractProp);

        work.setAuthors(authors.stream().map(
                Author::getName
                ).collect(Collectors.joining(";"))
        );

//        work.setAuthorKeywords(indexTerms.getAuthorTerms().getTerms()
//                .stream().map(
//                        kw -> new Keyword(kw.toLowerCase())
//                ).collect(Collectors.toList())
//        );

        return work;
    }
}
