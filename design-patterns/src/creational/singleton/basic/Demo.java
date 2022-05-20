package creational.singleton.basic;

import java.io.*;

class BasicSingleton implements Serializable {
    private static final BasicSingleton INSTANCE = new BasicSingleton();
    private int value = 0;

    private BasicSingleton() {}

    public static BasicSingleton getInstance() {
        return INSTANCE;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    protected Object readResolve() {
        return INSTANCE;
    }

}

public class Demo {

    static void saveToFile(BasicSingleton singleton,
                           String filename) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)
        ) {
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile (String filename) throws Exception {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)
        ) {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {
        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(123);
        System.out.println(singleton.getValue());

        // Problems (Breaking the singleton contract):
        // 1. Reflection
        // 2. Serialization

        singleton = BasicSingleton.getInstance();
        singleton.setValue(111);

        String filename = "singleton.bin";
        saveToFile(singleton, filename);

        singleton.setValue(222);

        BasicSingleton singletonNewInstance = readFromFile(filename);
        System.out.println(singleton.getValue() + " " + singletonNewInstance.getValue());
        System.out.println(singleton == singletonNewInstance);

    }
}
