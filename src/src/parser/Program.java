package parser;

import lexer.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program extends AST {
    private List<FuncDef> defs;
    private Body body;

    public Program(List<FuncDef> defs, Body body) {
        this.defs=defs;
        this.body=body;
    }

    @Override
    public String toString() {
        return "Program("+this.defs.toString()+","+this.body.toString()+")";
    }

    public static Program parse(Token token, List<FuncDef> defs) throws IOException, UnexpectedCharacter {
        if (token instanceof LPAR) {
            Token token2 = SLexer.getToken();
            if (token2 instanceof Defun) { // this is a definition
                // parse tail of definition
                FuncDef def =  FuncDef.parse(SLexer.getToken());
                // accumulate definition
                defs.add(def);
                // loop on the rest of the body with the accumulated definitions
                return parse(SLexer.getToken(), defs);
            } else { // this is the "composite" body at the end of the program
                Body body = Body.parse(SLexer.getToken(),new ArrayList<VarDef>());
                return new Program(defs, body);
            }
        } else // this is the "simple" body at the end of the program
            return parseSimpleProgram(token, defs);
    }

    static Program parseSimpleProgram(Token token, List<FuncDef> defs) throws IOException, UnexpectedCharacter {
        Body body = Body.parse(SLexer.getToken(),new ArrayList<VarDef>());
        return new Program(defs, body);
    }
}
