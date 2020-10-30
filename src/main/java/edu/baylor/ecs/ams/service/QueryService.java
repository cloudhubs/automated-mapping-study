package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.query.ACMQueryVisitor;
import edu.baylor.ecs.ams.query.IEEEQueryVisitor;
import edu.baylor.ecs.ams.query.QueryParser;
import edu.baylor.ecs.ams.runner.IEEERunner;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QueryService {
  public List<BaseModel> runQuery(String query) throws Exception {
    // TODO: check cache

    // convert to different formats
    // TODO: better exception handling
    ParseTree parseTree = QueryParser.parse(query);

    String ieeeQuery = new IEEEQueryVisitor().visit(parseTree);
    log.info(ieeeQuery);
    // String acmQuery = new ACMQueryVisitor().visit(parseTree);

    // TODO: concatenate results from different sources
    // TODO: better exception handling

    return IEEERunner.runQuery(ieeeQuery);

  }


  public void exportQuery(String query, boolean downloadFiles) throws Exception {
    // TODO: check cache

    // convert to different formats
    // TODO: better exception handling
    ParseTree parseTree = QueryParser.parse(query);

    String ieeeQuery = new IEEEQueryVisitor().visit(parseTree);
    log.info(ieeeQuery);
    // String acmQuery = new ACMQueryVisitor().visit(parseTree);

    // TODO: concatenate from different sources
    // TODO: better exception handling

    IEEERunner.runQueryExport(ieeeQuery, downloadFiles);
  }
}
