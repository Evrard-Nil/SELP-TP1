package parser;

import eval.State;
import lexer.Op;

import java.io.IOException;

public class UnaryMinus extends Expression {

    Expression exp1;

    public UnaryMinus(Expression exp1) {
        this.exp1 = exp1;
    }

    public String toString(){
        return "UnaryMinus(" +exp1+")";
    }

    @Override
    public int eval(State<Integer> s) throws IOException {
        return -exp1.eval(s);
    }
}
