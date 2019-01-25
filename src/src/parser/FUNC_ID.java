package parser;

import eval.State;
import lexer.Identifier;

import java.io.IOException;

public class FUNC_ID extends Expression {
    private Identifier id;
    public FUNC_ID(Identifier id) {
        this.id=id;
    }


    @Override
    public String toString() {
        return "Function("+this.id.getName()+")";
    }
    public FuncDef eval(State<FuncDef> s) throws IOException {
        if (!s.containsKey((this.id.getName()))) throw new IOException("Function not in state");
        return s.lookup(this.id.getName());
    }

    @Override
    public int eval(State<Integer> s) throws IOException {
        return 0;
    }
}
