package parser;

import eval.State;
import lexer.Op;

import java.io.IOException;

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

    @Override
    public int eval(State<Integer> s) throws IOException {
        switch (this.op){
            case MINUS:
                return exp1.eval(s) - exp2.eval(s);
            case HIGH:
                return  exp1.eval(s)>exp2.eval(s)? 1: 0;
            case LESS:
                return  exp1.eval(s)<exp2.eval(s)? 1: 0;
            case PLUS:
                return exp1.eval(s)+exp2.eval(s);
            case TIMES:
                return exp1.eval(s)*exp2.eval(s);
            case DIVIDE:
                return exp1.eval(s)/exp2.eval(s);
            case EQUALS:
                return  exp1.eval(s)==exp2.eval(s)? 1: 0;
            default:
                throw new ArithmeticException();
        }
    }
}
