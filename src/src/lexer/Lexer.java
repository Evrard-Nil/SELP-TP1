package lexer;
import java.util.*;
import java.io.*;

import static lexer.Op.*;

public class Lexer {
	private InputStream in;
	private int i; // current ASCII character (coded as an integer)
	private int numberOfOperations;

	public Lexer(InputStream in) throws IOException {
		this.in = in;
		i = in.read(); // initialize current character
	}

	public List<Token> lex() throws UnexpectedCharacter, IOException {
		// return the list of tokens recorded in the file
		List<Token> tokens = new ArrayList<Token>();

		try {
			Token token = getToken();

			while (! (token instanceof EOF)) {
				tokens.add(token);
				token = getToken();
			}
			tokens.add(token); // this is the EOF token
		} catch (IOException e){
			in.close(); // close the reader
			throw e; // pass the exception up the stack
		}
		return tokens;
	}

	public Token getToken() throws UnexpectedCharacter, IOException {
		switch (i){
			case -1 :
				in.close();
				return new EOF();
			case '(' :
				i = in.read();
				return new LPAR();
			case ' ' :
				i = in.read();
				return getToken();
			case '=' :
				i = in.read();
				if(i == '='){
					i = in.read();
					return EQUALS;
				}
				return new DEFVAR();
			case ')' :
				i = in.read();
				return new RPar();
			case '+' :
				i = in.read();
				return PLUS;
			case '-' :
				i = in.read();
				return MINUS;
			case '*' :
				i = in.read();
				return TIMES;
			case '/' :
				i = in.read();
				return DIVIDE;
			case '<' :
				i = in.read();
				return LESS;
			case '>' :
				i = in.read();
				return HIGH;
			case '0' :
				i = in.read();
				return new Literal(0);
			case '\n' :
				i = in.read();
				return getToken();
			case '\t' :
				i = in.read();
				return getToken();
			case '\r' :
				i = in.read();
				return getToken();
			case 'i' :
				String preVal = Character.toString((char) i);
				i = in.read();
				if(i == 'f'){
					preVal+= (char) i;
					i = in.read();
					if(i == ' '){
						i= in.read();
						return new If();
					}
					return getId(preVal+ (char) i);
				}
				return getId(preVal);
			case 'd' :
				String preVall = Character.toString((char) i);
				i = in.read();
				if(i == 'e'){
					preVall += (char) i;
					i = in.read();
					if(i == 'f'){
						preVall += (char)  i;
						i = in.read();
						if(i == 'u'){
							preVall +=  (char) i;
							i = in.read();
							if(i == 'n'){
								preVall +=  (char) i;
								i = in.read();
								if (i == ' '){
									i = in.read();
									return new Defun();
								}else {
									return getId(preVall+  (char) i);
								}
							} else
								return getId(preVall);
						} else
							return getId(preVall);
					}else
						return getId(preVall);
				} else
					return getId(preVall);
			default :
				if('1' <= i && i <= '9'){
					// Literal
					String literal = Character.toString((char) i);
					i = in.read();

					while('1' <= i && i <= '9'){
						literal += (char) i;
						i = in.read();
					}
					return new Literal(Integer.parseInt(literal));

				} else if('a' <= i && i <= 'z'){
					// Identifier or keyword
					return  getId(Character.toString((char) i));
				}
				throw new UnexpectedCharacter(i);
		}
	}

	private Identifier getId(String preVal) throws IOException {
		i = in.read();

		while('a' <= i && i <= 'z'||'0' <= i && i <= '9' ){
			preVal += (char) i;
			i = in.read();
		}
		return new Identifier( preVal);
	}
}