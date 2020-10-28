package edu.baylor.ecs.ams;

import edu.baylor.ecs.ams.query.ACMQueryVisitor;
import edu.baylor.ecs.ams.query.IEEEQueryVisitor;
import edu.baylor.ecs.ams.query.QueryParser;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
public class QueryParserTests {

    @Test
    void testParserException() {
        String input = "Abstract: msa";
        assertThrows(ParseCancellationException.class, () -> QueryParser.parse(input));
    }

    @Test
    void testParserScope() {
        String input = "abstract:   msa";
        String expectedOutput = "Abstract:msa";

        ParseTree parseTree = QueryParser.parse(input);

        String output = new ACMQueryVisitor().visit(parseTree);
        log.info(output);
        assertEquals(output, expectedOutput);
    }

    @Test
    void testParserMultipleWords() {
        String input = " code  smells ";
        String expectedOutput = "\"code smells\"";

        ParseTree parseTree = QueryParser.parse(input);

        String output = new ACMQueryVisitor().visit(parseTree);
        log.info(output);
        assertEquals(output, expectedOutput);
    }

    @Test
    void testIEEE() {
        String input = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND abstract: mn OR title: (as OR bs)";
        String expectedOutput = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND \"Abstract\":mn OR (\"Document Title\":as OR \"Document Title\":bs)";

        ParseTree parseTree = QueryParser.parse(input);

        String output = new IEEEQueryVisitor().visit(parseTree);
        log.info(output);
        assertEquals(output, expectedOutput);
    }

    @Test
    void testACM() {
        String input = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND abstract: mn OR title: (as OR bs)";
        String expectedOutput = "(\"abc fg\" AND \"de\") OR fg NOT 'pqr' AND Abstract:mn OR Title:(as OR bs)";

        ParseTree parseTree = QueryParser.parse(input);

        String output = new ACMQueryVisitor().visit(parseTree);
        log.info(output);
        assertEquals(output, expectedOutput);
    }
}
