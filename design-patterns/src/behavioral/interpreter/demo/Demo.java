package behavioral.interpreter.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public BinaryOperation.Type type;
    public Element left, right;

    public BinaryOperation() {
    }

    public BinaryOperation(BinaryOperation.Type type, Element left, Element right) {
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
        VARIABLE
    }

    public Type type;
    public String text;

    public Token(Token.Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

class ExpressionProcessor
{
    public Map<Character, java.lang.Integer> variables = new HashMap<>();

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
                default:
                    char c = input.charAt(i);
                    final StringBuilder sb = new StringBuilder("" + c);
                    if (Character.isDigit(c)) {
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
                    } else if (Character.isAlphabetic(c)) {
                        result.add(new Token(Token.Type.VARIABLE, sb.toString()));
                    }
            }
        }

        return result;
    }

    public int calculate(String expression)
    {
        List<Token> tokens = lex(expression);

        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false;

        for(int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            switch (token.type) {
                case INTEGER:
                    final int integer = java.lang.Integer.parseInt(token.text);
                    if(!haveLHS) {
                        result.left = new Integer(integer);
                        haveLHS = true;
                    } else {
                        result.right = new Integer(integer);
                        int newValue = result.eval();
                        result = new BinaryOperation();
                        result.left = new Integer(newValue);
                    }
                    break;
                case PLUS:
                    result.type = BinaryOperation.Type.ADDITION;
                    break;
                case MINUS:
                    result.type = BinaryOperation.Type.SUBTRACTION;
                    break;
                case VARIABLE:
                    Token nextToken = i + 1 < tokens.size() ? tokens.get(i + 1) : null;
                    if ((nextToken != null && nextToken.type == Token.Type.VARIABLE)
                        || !variables.containsKey(token.text.charAt(0))
                    ) {
                        return 0;
                    }

                    int value = variables.get(token.text.charAt(0));
                    if(!haveLHS) {
                        result.left = new Integer(value);
                        haveLHS = true;
                    } else {
                        result.right = new Integer(value);
                        int newValue = result.eval();
                        result = new BinaryOperation();
                        result.left = new Integer(newValue);

                    }
                    break;
            }
        }

        return result.left.eval();
    }
}

public class Demo {
}
