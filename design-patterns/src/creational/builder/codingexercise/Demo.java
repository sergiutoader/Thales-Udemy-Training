package creational.builder.codingexercise;

import java.util.ArrayList;

public class Demo {

    public static void main(String[] args) {
        CodeBuilder cb = new CodeBuilder("Person")
                .addField("name", "String")
                .addField("age", "int");

        System.out.println(cb);
    }

}

class CodeBuilder
{
    private String className;

    private ArrayList<String> names;
    private ArrayList<String> types;

    private final static String INDENTATION = "  ";
    private final static String LINE_SEPARATOR = System.lineSeparator();

    public CodeBuilder(String className)
    {
        this.className = className;
        this.names = new ArrayList<>();
        this.types = new ArrayList<>();
    }

    public CodeBuilder addField(String name, String type)
    {
        names.add(name);
        types.add(type);
        return this;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder()
                .append("public ")
                .append("class")
                .append(" ")
                .append(className)
                .append(LINE_SEPARATOR)
                .append("{")
                .append(LINE_SEPARATOR);

        for (int i = 0; i < names.size(); i++) {
            sb.append(INDENTATION)
                    .append("public ")
                    .append(types.get(i))
                    .append(" ")
                    .append(names.get(i))
                    .append(";")
                    .append(LINE_SEPARATOR);
        }

        sb.append("}");


        return sb.toString();
    }
}
