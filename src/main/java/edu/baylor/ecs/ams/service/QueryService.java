package edu.baylor.ecs.ams.service;

import edu.baylor.ecs.ams.model.BaseModel;
import edu.baylor.ecs.ams.runner.IEEERunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {
  public List<BaseModel> runQuery(String query) throws IOException, InterruptedException {
    // check cache

    // convert to different formats
    // TODO: conversion from base query to different types
    String IEEEQuery = getIEEEQuery(query);

    // run query
    List<BaseModel> results = new ArrayList<>();
    try {
     results = IEEERunner.runQuery(IEEEQuery);
    } catch (Exception e) {
      // TODO: better exception
      throw e;
    }

    // concatenate from different sources

    return results;

  }

  private String getIEEEQuery(String baseQuery) {
    // TODO: do conversion
    return baseQuery;
  }


  public void exportQuery(String query, boolean downloadFiles) throws IOException, InterruptedException {
    String IEEEQuery = getIEEEQuery(query);

    // run query
//    List<BaseModel> results = new ArrayList<>();
    try {
      IEEERunner.runQueryExport(IEEEQuery, downloadFiles);
    } catch (Exception e) {
      // TODO: better exception
      throw e;
    }
  }
}
