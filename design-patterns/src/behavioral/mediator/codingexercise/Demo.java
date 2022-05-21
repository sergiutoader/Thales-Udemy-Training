package behavioral.mediator.codingexercise;

import java.util.ArrayList;
import java.util.List;

class Participant
{
    public int value = 0;
    private Mediator mediator;

    public Participant(Mediator mediator)
    {
        this.mediator = mediator;
        mediator.participants.add(this);
    }

    public void say(int n)
    {
        mediator.broadcast(this, n);
    }


    public void receive(int n) {
        value += n;
    }
}

class Mediator
{
    public List<Participant> participants = new ArrayList<>();

    public void broadcast(Participant source, int n) {

        for (Participant p : participants) {
            if (p != source) {
                p.receive(n);
            }
        }
    }
}


public class Demo {
}
