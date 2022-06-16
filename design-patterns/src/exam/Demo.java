package exam;

class Server {
    private static Server INSTANCE;
    private Server() {}

    public static Server getInstance() {
        if (INSTANCE == null) {
            synchronized (Server.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Server();
                }
            }
        }
        return INSTANCE;
    }
}



public class Demo {
    public static void main(String[] args) {
        final Server instance = Server.getInstance();
        final Server instance1 = Server.getInstance();
        final Server instance2 = Server.getInstance();
        System.out.println(instance == instance2);
    }
}
