package behavioral.memento.codingexercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Token
{
    public int value = 0;

    public Token(int value)
    {
        this.value = value;
    }
}

class Memento
{
    public List<Token> tokens;

    public Memento(List<Token> tokens) {
        this.tokens = tokens;
    }
}

class TokenMachine
{
    public List<Token> tokens = new ArrayList<>();

    public Memento addToken(int value)
    {
        // todo
        Token newToken = new Token(value);
        tokens.add(newToken);
        return new Memento(tokens.stream().map(token -> new Token(token.value)).collect(Collectors.toList()));
    }

    public Memento addToken(Token token)
    {
        // todo (yes, please do both overloads :)
        tokens.add(token);
        return new Memento(tokens.stream().map(t -> new Token(t.value)).collect(Collectors.toList()));
    }

    public void revert(Memento m)
    {
        tokens = m.tokens;
    }
}


public class Demo {
}
