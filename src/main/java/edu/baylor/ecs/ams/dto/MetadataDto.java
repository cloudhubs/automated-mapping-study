package edu.baylor.ecs.ams.dto;

import java.util.List;

public class MetadataDto {
  private String doi;
  private List<String> authors;
  private String documentTitle;
  private String publicationTitle;
  private String date;
  private List<String> authorKeywords;
  private String ISSN;
  private List<String> ISBNs;

  public String getDoi() {
    return doi;
  }

  public void setDoi(String doi) {
    this.doi = doi;
  }

  public List<String> getAuthors() {
    return authors;
  }

  public void setAuthors(List<String> authors) {
    this.authors = authors;
  }

  public String getDocumentTitle() {
    return documentTitle;
  }

  public void setDocumentTitle(String documentTitle) {
    this.documentTitle = documentTitle;
  }

  public String getPublicationTitle() {
    return publicationTitle;
  }

  public void setPublicationTitle(String publicationTitle) {
    this.publicationTitle = publicationTitle;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<String> getAuthorKeywords() {
    return authorKeywords;
  }

  public void setAuthorKeywords(List<String> authorKeywords) {
    this.authorKeywords = authorKeywords;
  }

  public String getISSN() {
    return ISSN;
  }

  public void setISSN(String ISSN) {
    this.ISSN = ISSN;
  }

  public List<String> getISBNs() {
    return ISBNs;
  }

  public void setISBNs(List<String> ISBNs) {
    this.ISBNs = ISBNs;
  }
}
