package structural.bridge;

// Shape -> Circle, Square
// Rendering -> Vector, Raster

// VectorCirleRenderer, VectorSquareRenderer, RasterSquareRenderer....


interface Renderer {
    void renderCircle(float radius);
}

class VectorRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing a circle of radius "
            + radius);
    }
}

class RasterRenderer implements Renderer {

    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing pixels for a circle of radius "
            + radius);
    }
}

abstract class Shape {
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();
    public abstract void resize(float factor);
}


class Circle extends Shape {

    public float radius;

    public Circle(Renderer renderer) {
        super(renderer);
    }

    public Circle(Renderer renderer, float radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw() {
        renderer.renderCircle(radius);
    }

    @Override
    public void resize(float factor) {
        this.radius *= factor;
    }
}


public class Demo {
    public static void main(String[] args) {
        final RasterRenderer raster = new RasterRenderer();
        final VectorRenderer vector = new VectorRenderer();

        final Circle circle = new Circle(vector, 5);
        circle.draw();
        circle.resize(2);
        circle.draw();

        // using dependency injection -> using Google Guice

    }
}
