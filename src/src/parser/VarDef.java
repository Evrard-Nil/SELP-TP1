package parser;

import eval.State;
import lexer.*;

import java.awt.*;
import java.io.IOException;

public class VarDef extends AST {

    private Identifier id;
    private Expression exp;

    public VarDef(Identifier id, Expression exp) {
        this.id = id;
        this.exp = exp;
    }

    public static VarDef parse(Token token) throws IOException, UnexpectedCharacter {
        if(token instanceof Identifier){
            Expression exp = Expression.parseSimpleExpression(SLexer.getToken());
            if(SLexer.getToken() instanceof RPar){
                return  new VarDef((Identifier) token, exp);
            }
            else throw new RuntimeException("Expected identifier");
        }
        else throw new RuntimeException("Expected identifier");
    }

    @Override
    public String toString() {
        return "VarDef("+id.toString()+" = "+exp.toString()+")";
    }
    public void eval(State<Integer> s) throws IOException {
        if(s.containsKey(this.id.getName())) throw new IOException("Variable already in state");
        s.bind(this.id.getName(), this.exp.eval(s));
    }
}

