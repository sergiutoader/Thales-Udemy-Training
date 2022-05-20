package structural.bridge.codingexercise;

interface Renderer {
    public String whatToRenderAs();
}

abstract class Shape
{
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract String getName();

    @Override
    public String toString() {
        return "Drawing " + getName() + " as " + renderer.whatToRenderAs();
    }
}

class Triangle extends Shape
{
    public Triangle(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Triangle";
    }

}

class Square extends Shape
{
    public Square(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Square";
    }
}


class VectorRenderer implements Renderer {

    @Override
    public String whatToRenderAs() {
        return "lines";
    }
}

class RasterRenderer implements Renderer {

    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}


public class Demo {
}
