package parser;

import eval.State;

import java.io.IOException;

public class ConditionalExpression extends Expression {

    Expression exp1;
    Expression exp2;
    Expression exp3;

    public ConditionalExpression(Expression exp1, Expression exp2, Expression exp3) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }

    public String toString(){
        return "ConditionalExpression("+exp1+","+exp2+","+exp3+")";
    }

    @Override
    public int eval(State<Integer> s) throws IOException {
        if(exp1.eval(s)==0){
            return exp3.eval(s);
        }
        return exp2.eval(s);
    }
}