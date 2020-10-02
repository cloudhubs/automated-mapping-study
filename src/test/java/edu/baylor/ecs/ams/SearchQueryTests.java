package edu.baylor.ecs.ams;

import com.google.gson.Gson;
import edu.baylor.ecs.ams.query.*;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
@Slf4j
public class SearchQueryTests {
    @Test
    void testGenericQuery() {
        // ((a OR b) AND c) OR d
        QueryLiteral a = new QueryLiteral(QueryScope.ABSTRACT, "a");
        QueryLiteral b = new QueryLiteral(QueryScope.ABSTRACT, "b");
        QueryLiteral c = new QueryLiteral(QueryScope.ABSTRACT, "c");
        QueryLiteral d = new QueryLiteral(QueryScope.ABSTRACT, "d");

        GenericQuery q1 = new GenericQuery(
                null,
                new ArrayList<QueryLiteral>(Arrays.asList(a, b)),
                new ArrayList<QueryConnector>(Arrays.asList(QueryConnector.OR))
        );

        GenericQuery q2 = new GenericQuery(
                null,
                new ArrayList<QueryLiteral>(Arrays.asList(c)),
                null
        );

        GenericQuery q3 = new GenericQuery(
                new ArrayList<GenericQuery>(Arrays.asList(q1, q2)),
                null,
                new ArrayList<QueryConnector>(Arrays.asList(QueryConnector.AND))
        );

        GenericQuery q4 = new GenericQuery(
                null,
                new ArrayList<QueryLiteral>(Arrays.asList(d)),
                null
        );

        GenericQuery query = new GenericQuery(
                new ArrayList<GenericQuery>(Arrays.asList(q3, q4)),
                null,
                new ArrayList<QueryConnector>(Arrays.asList(QueryConnector.OR))
        );

        log.info(new Gson().toJson(query));
    }

    @Test
    void testBooleanQuery() throws Exception {
        String input = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND abstract: mn OR title: (as OR bs)";

        ParseTree parseTree = QueryParser.parse(input);

        IEEEQueryVisitor visitor = new IEEEQueryVisitor();
        log.info(visitor.visit(parseTree));

        ACMQueryVisitor acmQueryVisitor = new ACMQueryVisitor();
        log.info(acmQueryVisitor.visit(parseTree));
    }
}
