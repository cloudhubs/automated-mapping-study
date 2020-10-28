package edu.baylor.ecs.ams;

import edu.baylor.ecs.ams.query.ACMQueryVisitor;
import edu.baylor.ecs.ams.query.IEEEQueryVisitor;
import edu.baylor.ecs.ams.query.QueryParser;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
public class SearchQueryTests {

    @Test
    void testIEEE() throws Exception {
        String input = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND abstract: mn OR title: (as OR bs)";
        String expectedOutput = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND \"Abstract\":mn OR (\"Document Title\":as OR \"Document Title\":bs)";

        ParseTree parseTree = QueryParser.parse(input);

        String output = new IEEEQueryVisitor().visit(parseTree);
        log.info(output);
        assertEquals(output, expectedOutput);
    }

    @Test
    void testACM() throws Exception {
        String input = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND abstract: mn OR title: (as OR bs)";
        String expectedOutput = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND Abstract:mn OR Title:(as OR bs)";

        ParseTree parseTree = QueryParser.parse(input);

        String output = new ACMQueryVisitor().visit(parseTree);
        log.info(output);
        assertEquals(output, expectedOutput);
    }
}
