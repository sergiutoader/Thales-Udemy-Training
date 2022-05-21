package behavioral.mediator.chatroom;

import java.util.ArrayList;
import java.util.List;

class Person {
    public String name;
    public ChatRoom room;
    private List<String> chatLog = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public void receive(String sender, String message) {
        String s = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + s);
        chatLog.add(s);
    }

    public void say(String message) {
        room.broadcast(name, message);
    }

    public void privateMessage(String who, String message) {
        room.message(name, who, message);
    }
}

class ChatRoom {
    private List<Person> people = new ArrayList<>();

    public void join(Person p) {
        String joinMsg = p.name + " joins the room";
        broadcast("room", joinMsg);
        p.room = this;
        people.add(p);
    }

    public void broadcast(String source, String message) {
        for (Person person : people) {
            if (!person.name.equals(source)) {
                person.receive(source, message);
            }
        }
    }

    public void message(String source, String destination, String message) {
        people.stream()
                .filter(p -> p.name.equals(destination))
                .findFirst()
                .ifPresent(p -> p.receive(source, message));
    }
}

public class Demo {
    public static void main(String[] args) {
        final ChatRoom room = new ChatRoom();
        final Person p1 = new Person("John");
        final Person p2 = new Person("Jane");
        room.join(p1);
        room.join(p2);

        p1.say("Hi room");
        p2.say("Oh hey John");

        final Person p3 = new Person("Simon");
        room.join(p3);
        p3.say("Hi everyone");
        p2.privateMessage("Simon", "Glad you could join us!");
    }
}
