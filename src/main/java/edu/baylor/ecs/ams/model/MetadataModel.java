package edu.baylor.ecs.ams.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class MetadataModel {
  @Id
  private Long id;
  private String doi;
  private String authors;
  private String documentTitle;
  private String publicationTitle;
  private String date;
  @ManyToMany
  private List<Keyword> authorKeywords;
  private String issn;
  private String isbns;

//  public String getDoi() {
//    return doi;
//  }
//
//  public void setDoi(String doi) {
//    this.doi = doi;
//  }
//
//  public List<String> getAuthors() {
//    return authors;
//  }
//
//  public void setAuthors(List<String> authors) {
//    this.authors = authors;
//  }
//
//  public String getDocumentTitle() {
//    return documentTitle;
//  }
//
//  public void setDocumentTitle(String documentTitle) {
//    this.documentTitle = documentTitle;
//  }
//
//  public String getPublicationTitle() {
//    return publicationTitle;
//  }
//
//  public void setPublicationTitle(String publicationTitle) {
//    this.publicationTitle = publicationTitle;
//  }
//
//  public String getDate() {
//    return date;
//  }
//
//  public void setDate(String date) {
//    this.date = date;
//  }
//
//  public List<String> getAuthorKeywords() {
//    return authorKeywords;
//  }
//
//  public void setAuthorKeywords(List<String> authorKeywords) {
//    this.authorKeywords = authorKeywords;
//  }
//
//  public String getIssn() {
//    return issn;
//  }
//
//  public void setIssn(String issn) {
//    this.issn = issn;
//  }
//
//  public List<String> getIsbns() {
//    return isbns;
//  }
//
//  public void setIsbns(List<String> isbns) {
//    this.isbns = isbns;
//  }
}
