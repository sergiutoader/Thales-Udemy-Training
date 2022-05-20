package structural.composite.geometricshapes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GraphicObject {
    protected String name = "Group";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GraphicObject() {
    }

    public String color;
    public List<GraphicObject> children = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        print(sb, 0);

        return sb.toString();
    }

    private void print(StringBuilder sb, int depth) {
        sb.append(String.join("", Collections.nCopies(depth, "")))
                .append(depth > 0 ? " " : "")
                .append((color == null) || color.isEmpty() ? "" : " " + color + " ")
                .append(getName())
                .append(System.lineSeparator());
        for (GraphicObject child : children) {
            child.print(sb, depth + 1);
        }
    }
}

class Circle extends GraphicObject {
    public Circle(String color) {
        this.name = "Circle";
        this.color = color;
    }
}

class Square extends GraphicObject {
    public Square(String color) {
        this.name = "Square";
        this.color = color;
    }
}


public class Demo {
    public static void main(String[] args) {
        final GraphicObject drawing = new GraphicObject();
        drawing.setName("myDrawing");
        drawing.children.add(new Square("red"));
        drawing.children.add(new Circle("yellow"));

        final GraphicObject group = new GraphicObject();
        group.children.add(new Circle("blue"));
        group.children.add(new Square("blue"));

        drawing.children.add(group);

        System.out.println(drawing);


    }
}
