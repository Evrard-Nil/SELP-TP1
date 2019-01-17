package calc;

import lexer.*;
import parser.Expression;

import java.io.FileInputStream;
import java.io.InputStream;

public class Calc2 {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        InputStream is = System.in;
        if ( args.length>0 ) {
            inputFile = args[0];
            is = new FileInputStream(inputFile);
        }

        try {
            SLexer.init(is);
            Expression exp = Expression.parse(SLexer.getToken());
            System.out.println(exp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
