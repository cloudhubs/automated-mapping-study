package edu.baylor.ecs.ams.query;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class QueryParser {
    public static ParseTree parse(String input) throws Exception {
        CodePointCharStream source = CharStreams.fromString(input);
        BooleanQueryLexer lexer = new BooleanQueryLexer(source);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        BooleanQueryParser parser = new BooleanQueryParser(tokenStream);

        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
                throw e;
            }
        });

        try {
            return parser.query();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
