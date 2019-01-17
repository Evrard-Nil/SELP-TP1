package parser;

public class Literal extends Expression {
    int val;

    public Literal(int val){
        this.val=val;
    }

    public String toString(){
        return "Literal(" + val+")";
    }

    public int getVal() {
        return val;
    }
}
