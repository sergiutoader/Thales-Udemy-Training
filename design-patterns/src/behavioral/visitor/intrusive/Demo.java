package behavioral.visitor.intrusive;

abstract class Expression {
    public abstract void print(StringBuilder sb);
}

class DoubleExpression extends Expression {
    private double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

    @Override
    public void print(StringBuilder sb) {
        sb.append(value);
    }
}

class AdditionExpression extends Expression {
    private Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print(StringBuilder sb) {
        sb.append("(");
        left.print(sb);
        sb.append("+");
        right.print(sb);
        sb.append(")");
    }
}

public class Demo {
    public static void main(String[] args) {
        // 1 + (2 + 3)
        Expression e = new AdditionExpression(
                new DoubleExpression(1),
                new AdditionExpression(
                        new DoubleExpression(2),
                        new DoubleExpression(3)
                )
        );

        final StringBuilder sb = new StringBuilder();
        e.print(sb);
        System.out.println(sb.toString());
    }
}
