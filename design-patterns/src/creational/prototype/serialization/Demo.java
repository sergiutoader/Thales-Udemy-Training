package creational.prototype.serialization;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

class Foo implements Serializable {
    public int stuff;
    public String whatever;

    public Foo(int stuff, String whatever) {
        this.stuff = stuff;
        this.whatever = whatever;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "stuff=" + stuff +
                ", whatever='" + whatever + '\'' +
                '}';
    }
}

public class Demo {
    public static void main(String[] args) {
        Foo foo = new Foo(42, "whatever");
        Foo bar = SerializationUtils.roundtrip(foo);

        bar.whatever = "xyz";

        System.out.println(foo);
        System.out.println(bar);
    }
}
