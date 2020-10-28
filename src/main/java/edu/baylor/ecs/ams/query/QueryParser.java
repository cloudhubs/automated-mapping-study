package edu.baylor.ecs.ams.query;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;

@Slf4j
public class QueryParser {

    public static ParseTree parse(String input) {
        CodePointCharStream source = CharStreams.fromString(input);
        BooleanQueryLexer lexer = new BooleanQueryLexer(source);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        BooleanQueryParser parser = new BooleanQueryParser(tokenStream);

        lexer.addErrorListener(new ThrowingErrorListener());
        parser.addErrorListener(new ThrowingErrorListener());

        try {
            return parser.query();
        } catch (ParseCancellationException e) {
            log.error(e.toString());
            throw e;
        }
    }

    public static class ThrowingErrorListener extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e)
                throws ParseCancellationException {
            throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
        }
    }

}
