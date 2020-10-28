package edu.baylor.ecs.ams.query;

public class ACMQueryVisitor extends QueryVisitor {
    @Override
    public String visitQuery(BooleanQueryParser.QueryContext ctx) {
        if (ctx.word != null) {
            String resolved = getWords(ctx.word.getText());
            if (ctx.scope != null) {
                resolved = getScope(ctx.scope.getText()) + resolved;
            }
            return resolved;
        } else if (ctx.mid != null) {
            String resolved = "(" + visitQuery(ctx.mid) + ")";
            if (ctx.scope != null) {
                resolved = getScope(ctx.scope.getText()) + resolved;
            }
            return resolved;
        } else if (ctx.operator != null) {
            return visitQuery(ctx.left) + " " + ctx.operator.getText() + " " + visitQuery(ctx.right);
        }
        return null;
    }

    @Override
    public String getScope(String scope) {
        switch (scope) {
            case "abstract:":
                return "Abstract:";
            case "title:":
                return "Title:";
            case "fulltext:":
                return "Fulltext:";
            case "all:":
                return "AllField:";
            default:
                return "";
        }
    }
}
