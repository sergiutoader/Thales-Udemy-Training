package behavioral.command.codingexercise;

class Command
{
    enum Action
    {
        DEPOSIT, WITHDRAW
    }

    public Action action;
    public int amount;
    public boolean success;

    public Command(Action action, int amount)
    {
        this.action = action;
        this.amount = amount;
    }
}

class Account
{
    public int balance;

    public void process(Command c)
    {
        switch (c.action) {
            case DEPOSIT:
                balance += c.amount;
                c.success = true;
                break;
            case WITHDRAW:
                if (balance - c.amount < 0) {
                    c.success = false;
                    break;
                }
                c.success = true;
                balance -= c.amount;
                break;
        }
    }
}

public class Demo {
}
