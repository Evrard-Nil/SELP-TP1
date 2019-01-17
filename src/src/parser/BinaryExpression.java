package parser;

import lexer.Op;

public class BinaryExpression extends Expression {
    Op op;

    Expression exp1;
    Expression exp2;

    public BinaryExpression(Op op, Expression exp1, Expression exp2) {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public String toString(){
        return "BinaryExpression(" + op+","+exp1+","+exp2+")";
    }
}
