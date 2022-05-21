package behavioral.memento.general;

class Memento
{
    private int balance;

    public int getBalance() {
        return balance;
    }

    public Memento(int balance) {
        this.balance = balance;
    }
}

class BackAccount {
    private int balance;

    public BackAccount(int balance) {
        this.balance = balance;
    }

    public Memento deposit(int amount) {
        balance += amount;
        return new Memento(balance);
    }

    public Memento withdraw(int amount) {
        balance -= amount;
        return new Memento(balance);
    }

    public void restore(Memento memento) {
        balance = memento.getBalance();
    }

    @Override
    public String toString() {
        return "BackAccount{" +
                "balance=" + balance +
                '}';
    }
}

public class Demo {
    public static void main(String[] args) {
        final BackAccount ba = new BackAccount(100);
        final Memento m1 = ba.deposit(50); // 150
        final Memento m2 = ba.deposit(25); // 175

        System.out.println(ba);

        //restore to m1

        ba.restore(m1);
        System.out.println(ba);

        // restore to m2

        ba.restore(m2);
        System.out.println(ba);



    }
}
