package edu.baylor.ecs.ams;

import edu.baylor.ecs.ams.query.ACMQueryVisitor;
import edu.baylor.ecs.ams.query.IEEEQueryVisitor;
import edu.baylor.ecs.ams.query.QueryParser;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SearchQueryTests {
    
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
