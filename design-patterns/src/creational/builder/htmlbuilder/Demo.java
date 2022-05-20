package creational.builder.htmlbuilder;

import java.util.ArrayList;
import java.util.Collections;

class HtmlElement {
    public String name, text;
    public ArrayList<HtmlElement> elements = new ArrayList<>();
    private final int INDENT_SIZE = 2;
    private final String NEWLINE = System.lineSeparator();

    public HtmlElement() {
    }

    public HtmlElement(String name, String text) {
        this.name = name;
        this.text = text;
    }

    private String toStringImpl(int indent) {
        StringBuilder sb = new StringBuilder();
        String i = String.join("", Collections.nCopies(indent * INDENT_SIZE, " "));
        sb.append(String.format("%s<%s>%s", i, name, NEWLINE));
        if (text != null && !text.isEmpty()) {
            sb.append(String.join("", Collections.nCopies((indent + 1) * INDENT_SIZE, " ")))
                    .append(text)
                    .append(NEWLINE);
        }

        for (HtmlElement e : elements) {
            sb.append(e.toStringImpl(indent + 1));
        }

        sb.append(String.format("%s</%s>%s", i, name, NEWLINE));

        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringImpl(0);
    }
}

class HtmlBuilder {
    private String rootName;
    private HtmlElement root = new HtmlElement();

    public HtmlBuilder(String rootName) {
        this.rootName = rootName;
        root.name = rootName;
    }

    public HtmlBuilder addChild(String childName, String childText) {
        HtmlElement e = new HtmlElement(childName, childText);
        root.elements.add(e);

        return this;
    }

    public void clear() {
        root = new HtmlElement();
        root.name = rootName;
    }

    @Override
    public String toString() {
        return root.toString();
    }
}

public class Demo {
    public static void main(String[] args) {
        // fluent interface
        StringBuilder sb = new StringBuilder();
        sb.append("foo")
                .append("bar");

        HtmlBuilder builder = new HtmlBuilder("ul");
        builder
                .addChild("li", "hello")
                .addChild("li", "world");

        System.out.println(builder);
    }
}
