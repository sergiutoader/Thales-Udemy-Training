package creational.singleton.laziness;

public class Demo {
    public static void main(String[] args) {

    }
}

class LazySingleton {

    private static LazySingleton INSTANCE;

    private LazySingleton() {
        System.out.println("Initializing a lazy singleton");
    }

//    public static synchronized LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new LazySingleton();
//        }
//
//        return INSTANCE;
//    }

    // double-checked locking - lazy and thread safe
    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton();
                }
            }
        }

        return INSTANCE;
    }

}
