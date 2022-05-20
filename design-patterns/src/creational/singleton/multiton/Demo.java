package creational.singleton.multiton;

import java.util.HashMap;

public class Demo {

    public static void main(String[] args) {
        Printer mainPrinter = Printer.get(Subsystem.PRIMARY);
        Printer auxiliaryPrinter = Printer.get(Subsystem.AUXILIARY);
        Printer auxiliaryPrinter2 = Printer.get(Subsystem.AUXILIARY);
    }
}

class Printer {
    private static int INSTANCE_COUNT = 0;

    private Printer() {
        INSTANCE_COUNT++;
        System.out.println("Instances created so far: "
            + INSTANCE_COUNT);
    }

    private static final HashMap<Subsystem, Printer> instances = new HashMap<>();


    public static Printer get(Subsystem ss) {
        if (instances.containsKey(ss)) {
            return instances.get(ss);
        }

        Printer instance = new Printer();
        instances.put(ss, instance);

        return instance;
    }
}

enum Subsystem {
    PRIMARY,
    AUXILIARY,
    FALLBACK
}