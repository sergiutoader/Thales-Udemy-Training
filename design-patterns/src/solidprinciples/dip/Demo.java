package solidprinciples.dip;

// DIP (Dependency Inversion Principle)
// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// B. Abstractions should not depend on details.
// Details should depend on abstractions.
enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;
    // dob

    public Person(String name) {
        this.name = name;
    }
}

interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

class Relationships implements RelationshipBrowser { // low level model
    private List<Triplet<Person, Relationship, Person>>  relations = new ArrayList<>();

    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
                .filter(x -> Objects.equals(x.a.name, name) && x.b == Relationship.PARENT)
                .map(Triplet::getC)
                .collect(Collectors.toList());
    }
}

class Research { // high level model
    public Research(Relationships relationships) {
        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations(); // DIP violation
        relations.stream().filter(x -> x.a.name.equals("John") &&
                x.b == Relationship.PARENT)
                .forEach(ch -> System.out.println("John has a child called " + ch.c.name));
    }

    public Research(RelationshipBrowser browser) {
        List<Person> children = browser.findAllChildrenOf("John");
        for (Person child : children) {
            System.out.println("John has a child called " + child.name);
        }
    }
}


public class Demo {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);
    }
}