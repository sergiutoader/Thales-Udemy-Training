package creational.singleton.innerstatic;

public class Demo {
    public static void main(String[] args) {

    }
}

class InnerStaticSingleton {
    private InnerStaticSingleton(){}

    private static class Impl {
        private static final InnerStaticSingleton
            INSTANCE = new InnerStaticSingleton();
    }

    // thread safe
    public InnerStaticSingleton getInstance() {
        return Impl.INSTANCE;
    }
}
