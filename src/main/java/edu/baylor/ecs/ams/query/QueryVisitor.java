package edu.baylor.ecs.ams.query;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class QueryVisitor extends BooleanQueryBaseVisitor<String> {

    @Override
    public abstract String visitQuery(BooleanQueryParser.QueryContext ctx);

    public abstract String getScope(String scope);

    // if words is quoted or, contains a single word, return words
    // otherwise return quoted words
    public String getWords(String words) {
        if (words.contains("\"")) {
            return words;
        }
        words = words.trim();
        String[] splitWords = words.split("\\s");
        if (splitWords.length < 2) {
            return words;
        }
        return "\"" + String.join(" ", splitWords) + "\"";
    }
}
