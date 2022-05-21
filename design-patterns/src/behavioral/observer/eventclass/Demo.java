package behavioral.observer.eventclass;

import io.reactivex.internal.d.e.S;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

class Event<TArgs> {
    private int count = 0;
    private Map<Integer, Consumer<TArgs>> handlers = new HashMap<>();

    public Subscription addHandler(Consumer<TArgs> handler) {
        int i = count;
        handlers.put(count++, handler);
        return new Subscription(this, i);
    }

    public void fire(TArgs args) {
        for (Consumer<TArgs> handler : handlers.values()) {
            handler.accept(args);
        }
    }

    public class Subscription implements AutoCloseable {
        private Event<TArgs> event;
        private int id;

        public Subscription(Event<TArgs> event, int id) {
            this.event = event;
            this.id = id;
        }

        @Override
        public void close() throws Exception {
            event.handlers.remove(id);
        }
    }
}

class PropertyChangedEventArgs {
    public Object source;
    public String propertyName;

    public PropertyChangedEventArgs(Object source, String propertyName) {
        this.source = source;
        this.propertyName = propertyName;
    }
}

class Person {
    public Event<PropertyChangedEventArgs> propertyChanged = new Event<>();

    private int age;

    private boolean oldCanVote = canVote();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) {
            return;
        }

        propertyChanged.fire(new PropertyChangedEventArgs(
                this,
                "age"
        ));

        this.age = age;

        if (oldCanVote != canVote()) {
            propertyChanged.fire(new PropertyChangedEventArgs(
                    this,
                    "canVote"
            ));
        }
    }

    public boolean canVote() {
        return age >= 18;
    }
}

public class Demo {

    public static void main(String[] args) throws Exception {
        final Person person = new Person();
        final Event<PropertyChangedEventArgs>.Subscription sub =
                person.propertyChanged.addHandler(
                    x -> System.out.println("Person's " + x.propertyName + " has changed")
                );

        person.setAge(17);
        person.setAge(18);
        sub.close();
        person.setAge(19);

    }
}
