package parser;

import lexer.Op;

public class UnaryMinus extends Expression {

    Expression exp1;

    public UnaryMinus(Expression exp1) {
        this.exp1 = exp1;
    }

    public String toString(){
        return "UnaryMinus(" +exp1+")";
    }
}
