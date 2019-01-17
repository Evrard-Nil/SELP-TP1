package parser;

import eval.State;
import lexer.*;

import java.io.IOException;

public abstract class Expression extends AST{
    public static Expression parseCompositeExpressionTail(Token token2) throws IOException, UnexpectedCharacter {
        if( token2 instanceof  lexer.Op){
            Expression exp1 = Expression.parseSimpleExpression(SLexer.getToken());
            Token t4 = SLexer.getToken();
            if(token2.equals(Op.MINUS)) {
                if (t4 instanceof lexer.RPar) {
                    return new UnaryMinus(exp1);
                } else {
                    return checkRPar((Op) token2, exp1, t4);
                }
            }else {
                return checkRPar((Op) token2, exp1, t4);
            }

        }
        else if(token2 instanceof lexer.If){
            Expression exp1 = Expression.parseSimpleExpression(SLexer.getToken());
            Expression exp2 = Expression.parseSimpleExpression(SLexer.getToken());
            Expression exp3 = Expression.parseSimpleExpression(SLexer.getToken());
            if(SLexer.getToken() instanceof lexer.RPar){
                return new ConditionalExpression(exp1, exp2, exp3);
            }else  throw new IOException("Expected RPAR here");
        }else if (token2 instanceof Identifier){
            return new VAR_ID((Identifier) token2);

        }
        return new Literal(0);
    }

    public static Expression parseSimpleExpression(Token token) throws IOException, UnexpectedCharacter {
        if (token instanceof lexer.Literal){
            return new  Literal(((lexer.Literal) token).getI());
        }
        else if(token instanceof LPAR){
            Token t2 = SLexer.getToken();
            return  parseCompositeExpressionTail(t2);

        }else if (token instanceof Identifier){
            return new VAR_ID((Identifier) token);

        }
        else throw new IOException("Unexpected first token in expression");
    }

    public abstract int eval(State<Integer> s) throws IOException;


    private static Expression checkRPar(Op t2, Expression exp1, Token t4) throws IOException, UnexpectedCharacter {
        Expression exp2 = Expression.parseSimpleExpression(t4);
        if (SLexer.getToken() instanceof lexer.RPar) {
            return new BinaryExpression(t2, exp1, exp2);
        } else throw new IOException("Expected RPAR here");
    }
}
