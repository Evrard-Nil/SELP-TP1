package parser;

import eval.State;
import lexer.Identifier;
import lexer.Token;

import java.io.IOException;

public class VAR_ID extends Expression {


    private Identifier id;
    public VAR_ID(Identifier id) {
        this.id=id;
    }


    @Override
    public String toString() {
        return "VAR_ID("+this.id.getName()+")";
    }

    public int eval(State<Integer> s) throws IOException {
        return s.lookup(this.id.getName());
    }
}
