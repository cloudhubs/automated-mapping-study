package edu.baylor.ecs.ams;

import com.google.gson.Gson;
import edu.baylor.ecs.ams.model.query.GenericQuery;
import edu.baylor.ecs.ams.model.query.QueryConnector;
import edu.baylor.ecs.ams.model.query.QueryLiteral;
import edu.baylor.ecs.ams.model.query.QueryScope;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
class AmsApplicationTests {

    @Test
    void contextLoads() {
    }

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

        System.out.println(new Gson().toJson(query));
    }

}
