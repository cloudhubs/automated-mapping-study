package edu.baylor.ecs.ams.query;

public class ACMQueryVisitor extends BooleanQueryBaseVisitor<String> {
    @Override
    public String visitQuery(BooleanQueryParser.QueryContext ctx) {
        if (ctx.word != null) {
            String resolved = ctx.word.getText();
            if (ctx.scope != null) {
                resolved = ctx.scope.getText() + resolved;
            }
            return resolved;
        } else if (ctx.mid != null) {
            String resolved = "(" + visitQuery(ctx.mid) + ")";
            if (ctx.scope != null) {
                resolved = ctx.scope.getText() + resolved;
            }
            return resolved;
        } else if (ctx.operator != null) {
            return visitQuery(ctx.left) + " " + ctx.operator.getText() + " " + visitQuery(ctx.right);
        }
        return null;
    }
}
