package calc;

import eval.State;
import lexer.*;
import parser.Body;
import parser.Expression;
import parser.VarDef;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        InputStream is = System.in;
        if ( args.length>0 ) {
            inputFile = args[0];
            is = new FileInputStream(inputFile);
        }
        System.out.println(interpret(is));
    }

    public static int interpret(InputStream is) throws IOException, UnexpectedCharacter {
        SLexer.init(is);
        State sState =new State<Integer>();
        //State fState = new State<FuncDef>();
        Body body =  Body.parse(SLexer.getToken(),new ArrayList<VarDef>());
        System.out.println(body);
        return body.eval(sState);
    }

}
