package behavioral.nullobject.dynamic;

import java.lang.reflect.Proxy;

interface Log {
    void info(String msg);
    void warn(String msg);
}

class BankAccount {

    private Log log;
    private int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount) {
        balance += amount;
        log.info("Deposited " + amount);
    }
}

class ConsoleLog implements Log {

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warn(String msg) {
        System.err.println(msg);
    }
}

final class NullLog implements Log {

    @Override
    public void info(String msg) {

    }

    @Override
    public void warn(String msg) {

    }
}

public class Demo {

    @SuppressWarnings("unchecked")
    static <T> T noOp(Class<T> itf) {
        return (T) Proxy.newProxyInstance(
          itf.getClassLoader(),
          new Class<?>[] { itf },
            (proxy, method, args) -> {
              if (method.getReturnType().equals(Void.TYPE)) {
                  return null;
              } else {
                  return method.getReturnType().getConstructor().newInstance();
              }
            }
        );
    }

    public static void main(String[] args) {
        final ConsoleLog log1 = new ConsoleLog();
        final BankAccount ba1 = new BankAccount(log1);

        ba1.deposit(100);


        final NullLog log2 = new NullLog();
        final BankAccount ba2 = new BankAccount(log2);

        ba2.deposit(100);

        Log log = noOp(Log.class);
        final BankAccount ba3 = new BankAccount(log);

        ba3.deposit(100);

    }
}
