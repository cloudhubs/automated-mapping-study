package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.enums.Sites;
import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.query.ACMQueryVisitor;
import edu.baylor.ecs.ams.query.IEEEQueryVisitor;
import edu.baylor.ecs.ams.query.QueryParser;
import edu.baylor.ecs.ams.query.SDQueryVisitor;
import edu.baylor.ecs.ams.runner.IEEERunner;
import edu.baylor.ecs.ams.runner.ScienceDirectRunner;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static edu.baylor.ecs.ams.enums.Sites.ieee;
import static edu.baylor.ecs.ams.enums.Sites.sciencedirect;

@Service
@Slf4j
public class QueryService {
  public List<BaseModel> runQuery(String query, List<Sites> sources) throws Exception {
    List<BaseModel> results = new ArrayList<>();
    ParseTree parseTree = QueryParser.parse(query);
    // TODO: check cache

    // convert to different formats
    // TODO: better exception handling
    for(Sites site : sources) {
      switch(site) {
        case ieee:
          String ieeeQuery = new IEEEQueryVisitor().visit(parseTree);
          results.addAll(IEEERunner.runQuery(ieeeQuery));
        case sciencedirect:
          String sdQuery = new SDQueryVisitor().visit(parseTree);
          results.addAll(ScienceDirectRunner.runQuery(sdQuery));
      }
    }

    // TODO: concatenate results from different sources
    // TODO: better exception handling

    return results;
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
