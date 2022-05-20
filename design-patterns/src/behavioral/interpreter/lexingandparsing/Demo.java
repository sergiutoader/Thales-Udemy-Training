package behavioral.interpreter.lexingandparsing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Element {
    int eval();
}

class Integer implements Element {
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element {

    public enum Type {
        ADDITION,
        SUBTRACTION
    }

    public Type type;
    public Element left, right;

    public BinaryOperation() {
    }

    public BinaryOperation(Type type, Element left, Element right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval() {
        switch (type) {
            case ADDITION:
                return left.eval() + right.eval();
            case SUBTRACTION:
                return left.eval() - right.eval();
            default:
                return 0;
        }
    }
}


class Token {
    public enum Type {
        INTEGER,
        PLUS,
        MINUS,
        LPAREN,
        RPAREN
    }

    public Type type;
    public String text;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

public class Demo {
    static List<Token> lex(String input) {
        List<Token> result = new ArrayList<>();

        for(int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '+':
                    result.add(new Token(Token.Type.PLUS, "+"));
                    break;
                case '-':
                    result.add(new Token(Token.Type.MINUS, "-"));
                    break;
                case '(':
                    result.add(new Token(Token.Type.LPAREN, "("));
                    break;
                case ')':
                    result.add(new Token(Token.Type.RPAREN, ")"));
                    break;
                default:
                    final StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for(int j = i + 1; j < input.length(); j++) {
                        char nextChar = input.charAt(j);
                        if (Character.isDigit(nextChar)) {
                            sb.append(nextChar);
                        } else {
                            i = j - 1;
                            break;
                        }
                    }
                    result.add(new Token(Token.Type.INTEGER, sb.toString()));
                    break;
            }
        }

        return result;
    }

    static Element parse(List<Token> tokens) {
        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            switch (token.type) {
                case INTEGER:
                    final int integer = java.lang.Integer.parseInt(token.text);
                    if(!haveLHS) {
                        result.left = new Integer(integer);
                        haveLHS = true;
                    } else {
                        result.right = new Integer(integer);
                    }
                    break;
                case PLUS:
                    result.type = BinaryOperation.Type.ADDITION;
                    break;
                case MINUS:
                    result.type = BinaryOperation.Type.SUBTRACTION;
                    break;
                case LPAREN:
                    int j = i;
                    for(; j < tokens.size(); j++) {
                        if (tokens.get(j).type == Token.Type.RPAREN) {
                            break;
                        }
                    }

                    final List<Token> subexpression = tokens
                            .stream()
                            .skip(i + 1)
                            .limit(j - i - 1)
                            .collect(Collectors.toList());
                    Element element = parse(subexpression);
                    if (!haveLHS) {
                        result.left = element;
                        haveLHS = true;
                    } else {
                        result.right = element;
                    }
                    i = j;
                    break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final String input = "(13+4)-(12+1)";
        List<Token> tokens = lex(input);
        System.out.println(
                tokens.stream().map(Token::toString).collect(Collectors.joining("  "))
        );

        Element parsed = parse(tokens);
        System.out.println(input + " = " + parsed.eval());
    }
}
