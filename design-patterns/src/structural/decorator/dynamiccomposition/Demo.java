package structural.decorator.dynamiccomposition;

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

class ColoredShape implements Shape {

    private Shape shape;
    private String color;


    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info() + " has " + transparency + "% transparency";
    }
}

public class Demo {

    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        final ColoredShape coloredCircle = new ColoredShape(new Circle(20), "green");
        System.out.println(coloredCircle.info());

        final TransparentShape transparentCircle = new TransparentShape(
                new ColoredShape(
                        new Square(10),
                        "red"
                ),
                40
        );
        System.out.println(transparentCircle.info());
    }
}
