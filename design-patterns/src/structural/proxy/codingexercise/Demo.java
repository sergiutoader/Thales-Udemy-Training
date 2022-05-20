package structural.proxy.codingexercise;

class Person
{
    private int age;

    public Person(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String drink() { return "drinking"; }
    public String drive() { return "driving"; }
    public String drinkAndDrive() { return "driving while drunk"; }
}

class ResponsiblePerson extends Person
{

    public ResponsiblePerson(Person person)
    {
        super(person.getAge());
    }

    public String drink() {
        if (getAge() < 18) {
            return "too young";
        }
        return "drinking";
    }
    public String drive() {
        if (getAge() < 16) {
            return "too young";
        }
        return "driving";
    }
    public String drinkAndDrive() {
        return "dead";
    }
}

public class Demo {
    public static void main(String[] args) {

    }
}
