package eval;

import java.io.IOException;
import java.util.HashMap;

@SuppressWarnings("serial")
public class State<E> extends HashMap<String, E> {

    // returns the value of a variable in the state
    public E lookup(String var) throws IOException {
        if (!this.containsKey(var)) throw new IOException("Unexpected first token in expression");
        return get(var);
    }
    // binds a variable in the state
    public void bind(String var, E value) {
        put(var, value);
    }
}