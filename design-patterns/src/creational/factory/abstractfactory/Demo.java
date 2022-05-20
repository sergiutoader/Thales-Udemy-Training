package creational.factory.abstractfactory;

import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class Pair<A, B> {
    A a;
    B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }
}

interface HotDrink {
    void consume();
}

class Tea implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This tea is delicious");
    }
}

class Coffee implements HotDrink {

    @Override
    public void consume() {
        System.out.println("This coffee is delicious");
    }
}

interface HotDrinkFactory {
    HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepare(int amount) {
        System.out.println(
                "Put in tea bag, boil water, pour "
                        + amount + "ml, add lemon and enjoy"
        );

        return new Tea();
    }
}

class CoffeeFactory implements HotDrinkFactory {

    @Override
    public HotDrink prepare(int amount) {
        System.out.println(
                "Grind some beans, boil water, pour "
                        + amount + "ml, add cream and sugar and enjoy"
        );

        return new Coffee();
    }
}

class HotDrinkMachine {
    List<Pair<String, HotDrinkFactory>> namedFactories = new ArrayList<>();

    public HotDrinkMachine() throws Exception {
       // cannot use reflection here
        Set<Class<? extends HotDrinkFactory>> subInterfaces = new Reflections("").getSubTypesOf(HotDrinkFactory.class);
        for (Class<? extends HotDrinkFactory> type : subInterfaces) {
            namedFactories.add(new Pair<>(
               type.getSimpleName().replace("Factory", ""),
               type.getDeclaredConstructor().newInstance()
            ));
        }
    }

    public HotDrink makeDrink() throws Exception {
        System.out.println("Available drinks: ");
        for (int i = 0; i < namedFactories.size(); i++) {
            Pair<String, HotDrinkFactory> item = namedFactories.get(i);
            System.out.println("" + i + ": " + item.a);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String s;
            int i, amount;

            if ((s = reader.readLine()) != null
                && (i = Integer.parseInt(s)) >= 0
                && i < namedFactories.size()){
               System.out.println("Specify ammount:");
               s = reader.readLine();
               if (s != null && (amount = Integer.parseInt(s)) > 0) {
                   return namedFactories.get(i).b.prepare(amount);
               }
            }
            System.out.println("Incorrect input, try again");
        }
    }
}

public class Demo {
    public static void main(String[] args) throws Exception {
        HotDrink drink = new HotDrinkMachine().makeDrink();
        drink.consume();
    }
}
