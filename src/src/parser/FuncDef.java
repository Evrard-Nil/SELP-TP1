package parser;

import lexer.*;

import java.io.IOException;
import java.lang.management.BufferPoolMXBean;
import java.util.ArrayList;

public class FuncDef extends AST{
    private Head h;
    private  Body b;

    public FuncDef(Head h, Body b) {
        this.h = h;
        this.b=b;
    }

    public static FuncDef parse(Token token) throws IOException, UnexpectedCharacter {
        if(token instanceof Identifier){
            Head h = Head.parse(SLexer.getToken(), new FUNC_ID((Identifier)token), new ArrayList<VAR_ID>());
            Body b = Body.parse(SLexer.getToken(), new ArrayList<VarDef>());

            return new FuncDef(h,b);
        }
        else throw new RuntimeException("Expected FUNC_ID here");
    }

    @Override
    public String toString() {
        return "FuncDef("+this.h.toString()+","+this.b.toString()+")";
    }
}
