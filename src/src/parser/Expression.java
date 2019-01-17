package parser;

import lexer.Op;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;

import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.regex.PatternSyntaxException;

public abstract class Expression extends AST{
    static public Expression parse(Token t) throws IOException, UnexpectedCharacter {
        // TODO: 14/01/2019 Compléter l'analyse syntaxique
        if (t instanceof lexer.Literal){
            return new  Literal(((lexer.Literal) t).getI());
        }
        else if(t instanceof lexer.LPar){
            Token t2 = SLexer.getToken();
            if( t2 instanceof  lexer.Op){
                Expression exp1 = Expression.parse(SLexer.getToken());
                Token t4 = SLexer.getToken();
                if(t2.equals(Op.MINUS)) {
                    if (t4 instanceof lexer.RPar) {
                        return new UnaryMinus(exp1);
                    } else {
                        Expression exp2 = Expression.parse(t4);
                        if (SLexer.getToken() instanceof lexer.RPar) {
                            return new BinaryExpression((Op) t2, exp1, exp2);
                        } else throw new IOException("Expected RPAR here");
                    }
                }else {
                    Expression exp2 = Expression.parse(t4);
                    if (SLexer.getToken() instanceof lexer.RPar) {
                        return new BinaryExpression((Op) t2, exp1, exp2);
                    } else throw new IOException("Expected RPAR here");
                }

            }
            else if(t2 instanceof lexer.If){
                Expression exp1 = Expression.parse(SLexer.getToken());
                Expression exp2 = Expression.parse(SLexer.getToken());
                Expression exp3 = Expression.parse(SLexer.getToken());
                if(SLexer.getToken() instanceof lexer.RPar){
                    return new ConditionalExpression(exp1, exp2, exp3);
                }else  throw new IOException("Expected RPAR here");
            }
            return new Literal(0);

        }else throw new IOException("Unexpected first token in expression");
    }
}
