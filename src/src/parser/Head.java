package parser;

import lexer.*;

import java.io.IOException;
import java.util.List;

public class Head {
    private FUNC_ID funcId;
    private List<VAR_ID> var_ids;

    public Head(FUNC_ID funcId, List<VAR_ID> var_ids) {
        this.funcId = funcId;
        this.var_ids = var_ids;
    }

    public static Head parse(Token token, FUNC_ID funcId, List<VAR_ID> var_ids) throws IOException, UnexpectedCharacter {

        var_ids.add(new VAR_ID((Identifier) token));
        token = SLexer.getToken();

        if (token instanceof Identifier) {
            // loop on rest of VAR_IDs
            return Head.parse(token, funcId, var_ids);
        } else if (token instanceof RPar) {
            return new Head(funcId, var_ids);
        } else {
            throw new IOException("Expected RPAR here");
        }
    }
}
