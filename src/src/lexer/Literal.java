package lexer;

public class Literal implements Token {
    private int i;

    public Literal(int i) {
        this.i = i;
    }

    public String toString() {
        return "Literal :" + this.i;
    }
}
