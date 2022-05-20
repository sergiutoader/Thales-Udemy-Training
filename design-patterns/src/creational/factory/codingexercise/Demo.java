package creational.factory.codingexercise;

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}

class PersonFactory
{
    private static int CURRENT_ID = 0;

    public Person createPerson(String name)
    {
        return new Person(CURRENT_ID++, name);
    }
}

public class Demo {
    public static void main(String[] args) {

    }
}
