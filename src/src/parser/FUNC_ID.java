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


    @Override
    public int eval(State<Integer> s) throws IOException {
        return 0;
    }
}
