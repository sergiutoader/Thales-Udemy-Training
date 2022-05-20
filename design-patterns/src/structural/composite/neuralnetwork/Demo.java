package structural.composite.neuralnetwork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

interface SomeNeurons extends Iterable<Neuron> {
    default void connectTo(SomeNeurons other) {
        if (this == other) {
            return;
        }

        for(Neuron from : this) {
            for (Neuron to : other) {
                from.in.add(to);
                to.out.add(from);
            }
        }
    }
}

class Neuron implements SomeNeurons {
    public ArrayList<Neuron> in;
    public ArrayList<Neuron> out;

    @Override
    public void forEach(Consumer<? super Neuron> action) {
        action.accept(this);
    }

    @Override
    public Spliterator<Neuron> spliterator() {
        return Collections.singleton(this).spliterator();
    }

    @Override
    public Iterator<Neuron> iterator() {
        return Collections.singleton(this).iterator();
    }

//    public void connectTo(Neuron other) {
//        out.add(other);
//        other.in.add(this);
//    }
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons {
}

public class Demo {
    public static void main(String[] args) {
        final Neuron n1 = new Neuron();
        final Neuron n2 = new Neuron();

        NeuronLayer layer1 = new NeuronLayer();
        NeuronLayer layer2 = new NeuronLayer();

        n1.connectTo(n2);
        n1.connectTo(layer1);
        layer1.connectTo(n1);
        layer1.connectTo(layer2);
    }
}
