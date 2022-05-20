package structural.decorator.staticcomposition;

import java.util.function.Supplier;

interface Shape {
    String info();
}

class Circle implements Shape {
    private float radius;


    public Circle(float radius) {
        this.radius = radius;
    }

    public Circle() {
        radius = 10;
    }

    void resize(float factor) {
        radius *= factor;
    }


    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square implements Shape {
    private float size;

    public Square(float size) {
        this.size = size;
    }

    public Square() {
        size = 10;
    }


    @Override
    public String info() {
        return "A square of size " + size;
    }
}

class ColoredShape <T extends Shape> implements Shape {
    private Shape shape;
    private String color;

    public ColoredShape(Supplier<? extends T> constructor, String color) {
        this.shape = constructor.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape <T extends Shape> implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape(Supplier<? extends T> constructor, int transparency) {
        this.shape = constructor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "% transparency";
    }
}
public class Demo {
    public static void main(String[] args) {
        ColoredShape<Square> blueSquare = new ColoredShape<>(
                () -> new Square(20),
                "blue"
        );

        System.out.println(blueSquare.info());

        TransparentShape<ColoredShape<Circle>> transparentRedCircle = new TransparentShape<>(
                () -> new ColoredShape<>(
                        () -> new Circle(5),
                        "green"
                ),
                35
        );

        System.out.println(transparentRedCircle.info());
    }
}
