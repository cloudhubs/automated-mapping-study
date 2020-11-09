package edu.baylor.ecs.ams.query;

import org.antlr.v4.runtime.Token;

public class SDQueryVisitor extends BooleanQueryBaseVisitor<String> {
    @Override
    public String visitQuery(BooleanQueryParser.QueryContext ctx) {
        return visitQueryRecursiveScope(ctx, null);
    }

    private String visitQueryRecursiveScope(BooleanQueryParser.QueryContext ctx, Token parentScope) {
        if (ctx.word != null) {
            String resolved = "(" + ctx.word.getText() + ")";
            if (ctx.scope != null) {
                resolved = getScope(ctx.scope.getText()) + resolved;
            } else if (parentScope != null) {
                resolved = getScope(parentScope.getText()) + resolved;
            } else {
                resolved = getScope("") + resolved;
            }
            return resolved;
        } else if (ctx.mid != null) {
            return "(" + visitQueryRecursiveScope(ctx.mid, ctx.scope) + ")";
        } else if (ctx.operator != null) {
            return visitQueryRecursiveScope(ctx.left, parentScope) + " " + ctx.operator.getText() + " " +
                    visitQueryRecursiveScope(ctx.right, parentScope);
        }
        return null;
    }

    private String getScope(String scope) {
        if (scope.equals("title:")) {
            return "title";
        }
        return "keywords";
    }
}
