package edu.baylor.ecs.ams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NamedQuery(name = MetadataModel.GET_WORK_AND_AUTHOR_KEYWORDS_BY_ID,
        query="select m from MetadataModel m left join fetch m.authorKeywords a where m.id = ?1")
@NamedQuery(name = MetadataModel.GET_WORK_AND_EXTRACTED_KEYWORDS_BY_ID,
        query="select m from MetadataModel m left join fetch m.extractedKeywords a where m.id = ?1")
@NamedQuery(name = MetadataModel.GET_WORK_AND_ABSTRACT_KEYWORDS_BY_ID,
        query="select m from MetadataModel m left join fetch m.abstractKeywords a where m.id = ?1")
@NamedQuery(name = MetadataModel.GET_ALL_WORKS_AND_AUTHOR_KEYWORDS_BY_ID,
        query="select m from MetadataModel m left join fetch m.authorKeywords a")
@NamedQuery(name = MetadataModel.GET_ALL_WORKS_AND_EXTRACTED_KEYWORDS_BY_ID,
        query="select m from MetadataModel m left join fetch m.extractedKeywords a")
@NamedQuery(name = MetadataModel.GET_ALL_WORKS_AND_ABSTRACT_KEYWORDS_BY_ID,
        query="select m from MetadataModel m left join fetch m.abstractKeywords a")
public class MetadataModel {
  public static final String GET_WORK_AND_AUTHOR_KEYWORDS_BY_ID = "GET_WORK_AND_AUTHOR_KEYWORDS_BY_ID";
  public static final String GET_WORK_AND_EXTRACTED_KEYWORDS_BY_ID = "GET_WORK_AND_EXTRACTED_KEYWORDS_BY_ID";
  public static final String GET_WORK_AND_ABSTRACT_KEYWORDS_BY_ID = "GET_WORK_AND_ABSTRACT_KEYWORDS_BY_ID";
  public static final String GET_ALL_WORKS_AND_AUTHOR_KEYWORDS_BY_ID = "GET_ALL_WORKS_AND_AUTHOR_KEYWORDS_BY_ID";
  public static final String GET_ALL_WORKS_AND_EXTRACTED_KEYWORDS_BY_ID = "GET_ALL_WORKS_AND_EXTRACTED_KEYWORDS_BY_ID";
  public static final String GET_ALL_WORKS_AND_ABSTRACT_KEYWORDS_BY_ID = "GET_ALL_WORKS_AND_ABSTRACT_KEYWORDS_BY_ID";


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(unique = true)
  private String doi;
  private String authors;
  private String documentTitle;
  private String publicationTitle;
  @Lob
  private String workAbstract;
  private String date;
  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Keyword> authorKeywords = new ArrayList<>();
  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Keyword> extractedKeywords = new ArrayList<>();
  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @Fetch(value = FetchMode.SUBSELECT)
  private List<Keyword> abstractKeywords = new ArrayList<>();
  private String issn;
  private String isbns;
  @Column(nullable = false)
  private boolean hasFullText;
  private String fullTextPath;
  private String pdfLink;

//  @ManyToMany(mappedBy = "works")
//  private List<Project> projects = new ArrayList<>();
}
