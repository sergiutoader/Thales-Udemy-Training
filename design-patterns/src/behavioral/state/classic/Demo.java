package behavioral.state.classic;

class State {
    void on(LightSwitch ls) {
        System.out.println("Light is already on");
    }

    void off(LightSwitch ls) {
        System.out.println("Light is already off");
    }
}

class OnState extends State {
    public OnState() {
        System.out.println("Light on");
    }

    @Override
    void off(LightSwitch ls) {
        System.out.println("Switching light off..");
        ls.setState(new OffState());
    }
}

class OffState extends State {
    public OffState() {
        System.out.println("Light off");
    }

    @Override
    void on(LightSwitch ls) {
        System.out.println("Switching light on...");
        ls.setState(new OnState());
    }

}

class LightSwitch {
    private State state; // onState/offState

    public LightSwitch() {
        state = new OffState();
    }

    void on() {
        state.on(this);
    }

    void off() {
        state.off(this);
    }

    public void setState(State state) {
        this.state = state;
    }
}

public class Demo {
    public static void main(String[] args) {
        final LightSwitch lightSwitch = new LightSwitch();

        lightSwitch.on();
        lightSwitch.off();
        lightSwitch.off();
    }
}
