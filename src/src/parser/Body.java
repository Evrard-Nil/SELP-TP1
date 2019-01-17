package parser;

import eval.State;
import lexer.*;

import java.io.IOException;
import java.util.List;

public class Body extends AST {
    private List<VarDef> defs;
    private Expression exp;
    public Body(List<VarDef> defs, Expression exp) {
        super();
        this.defs = defs;
        this.exp=exp;
    }

    @Override
    public String toString() {
        return "Body("+this.defs.toString()+" = "+ exp.toString()+")";
    }
    public static Body parse(Token token, List<VarDef> defs) throws IOException, UnexpectedCharacter {
        if (token instanceof LPAR) {
            Token token2 = SLexer.getToken();
            if (token2 instanceof DEFVAR) { // this is a definition
                // parse tail of definition
                VarDef def = (VarDef) VarDef.parse(SLexer.getToken());
                // accumulate definition
                defs.add(def);
                // loop on the rest of the body with the accumulated definitions
                return parse(SLexer.getToken(), defs);
            } else { // this is the "composite" expression at the end of the body
                Expression exp = Expression.parseCompositeExpressionTail(token2);
                return new Body(defs, exp);
            }
        } else // this is the "simple" expression at the end of the body
            return parseSimpleBody(token, defs);
    }

    static Body parseSimpleBody(Token token, List<VarDef> defs) throws IOException, UnexpectedCharacter {
        Expression exp = Expression.parseSimpleExpression(token);
        return new Body(defs, exp);
    }
    public int eval(State s) throws IOException {
        for (VarDef f:defs){
            f.eval(s);
        }


        return this.exp.eval(s);
    }
}
