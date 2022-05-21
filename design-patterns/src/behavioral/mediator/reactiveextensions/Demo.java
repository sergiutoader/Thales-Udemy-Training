package behavioral.mediator.reactiveextensions;
import io.reactivex.Observable;
import io.reactivex.Observer;

import java.util.ArrayList;
import java.util.List;

class EventBroker extends Observable<Integer> {

    private List<Observer<? super Integer>> observers = new ArrayList<>();

    @Override
    protected void subscribeActual(Observer<? super Integer> observer) {
        observers.add(observer);
    }

    public void publish(Integer n) {
        for (Observer observer : observers) {
            observer.onNext(n);
        }
    }
}

class FootballPlayer {
    private int goalsScored = 0;
    private EventBroker broker;

    public String name;

    public FootballPlayer(EventBroker broker, String name) {
        this.broker = broker;
        this.name = name;
    }

    public void score() {
        broker.publish(++goalsScored);
    }
}

class FootballCoach {
    public FootballCoach(EventBroker broker) {
        broker.subscribe(i -> System.out.println("Hey you scored " + i + " goals!"));
    }
}

public class Demo {
    public static void main(String[] args) {
        final EventBroker broker = new EventBroker();
        final FootballPlayer player = new FootballPlayer(broker, "Jones");

        final FootballCoach coach = new FootballCoach(broker);

        player.score();
        player.score();
        player.score();
    }
}
