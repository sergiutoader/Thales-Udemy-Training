package behavioral.state.handmadestatemachine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Pair<T, U> {
    T first;
    U second;

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
}

enum State {
    OFF_HOOK,   // stating
    ON_HOOK,    // terminal
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Trigger {
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

public class Demo {
    private static Map<State, List<Pair<Trigger, State>>>
        rules = new HashMap<>();

    static {
        rules.put(State.OFF_HOOK, List.of(
                new Pair<>(Trigger.CALL_DIALED, State.CONNECTING),
                new Pair<>(Trigger.STOP_USING_PHONE, State.ON_HOOK)
        ));
        rules.put(State.CONNECTING, List.of(
                new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK),
                new Pair<>(Trigger.CALL_CONNECTED, State.CONNECTED)
        ));
        rules.put(State.CONNECTED, List.of(
                new Pair<>(Trigger.LEFT_MESSAGE, State.OFF_HOOK),
                new Pair<>(Trigger.PLACED_ON_HOLD, State.ON_HOLD)
        ));

        rules.put(State.ON_HOLD, List.of(
                new Pair<>(Trigger.TAKEN_OFF_HOLD, State.CONNECTED),
                new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK)
        ));
    }

    private static State currentState = State.OFF_HOOK;
    private static final State exitState = State.ON_HOOK;

    public static void main(String[] args) {
        final BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in)
        );

        while(currentState != exitState) {
            System.out.println("The phone is currently " + currentState);
            System.out.println("Select trigger:");

            for(int i = 0; i < rules.get(currentState).size(); i++) {
                final Trigger trigger = rules.get(currentState).get(i).getFirst();
                System.out.println("" + i + ". " + trigger);
            }
            boolean parseOK;
            int choice = 0;

            do {
                try {
                    System.out.println("Please enter your choice");
                    choice = Integer.parseInt(console.readLine());
                    parseOK = choice >= 0 && choice < rules.get(currentState).size();
                } catch (Exception e) {
                    parseOK = false;
                }
            } while (!parseOK);

            currentState = rules.get(currentState).get(choice).getSecond();
        }
        System.out.println("And we are done!");
    }
}


