package creational.singleton.codingexercise;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;


public class Demo {
    public static void main(String[] args) {

    }
}

class SingletonTester
{
    public static boolean isSingleton(Supplier<Object> func)
    {
        final Object o1 = func.get();
        final Object o2 = func.get();

        return o1 == o2;
    }
}