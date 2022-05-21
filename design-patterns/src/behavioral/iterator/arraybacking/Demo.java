package behavioral.iterator.arraybacking;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class SimpleCreature {
    private int strength, agility, intelligence;


    public int max() {
        return Math.max(strength, Math.max(agility, intelligence));
    }

    public int sum() {
        return strength + agility + intelligence;
    }

    public double average() {
        return sum() / 3.0;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}

class Creature implements Iterable<Integer> {

    private final int strength = 0;
    private final int agility = 1;
    private final int intelligence = 2;
    private final int[] stats = new int[3];

    public int getStrength() {
        return stats[strength];
    }
    public void setStrength(int value) {
        stats[strength] = value;
    }

    public int getAgility() {
        return stats[agility];
    }

    public void setAgility(int value) {
        this.stats[agility] = value;
    }

    public int getIntelligence() {
        return stats[intelligence];
    }

    public void setIntelligence(int value) {
        this.stats[intelligence] = value;
    }

    public int sum() {
        return IntStream.of(stats).sum();
    }

    public int max() {
        return IntStream.of(stats).max().getAsInt();
    }

    public double average() {
        return IntStream.of(stats).average().getAsDouble();
    }



    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for (int stat : stats) {
            action.accept(stat);
        }
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(stats).spliterator();
    }
}

public class Demo {
    public static void main(String[] args) {
        final Creature creature = new Creature();
        creature.setAgility(10);
        creature.setIntelligence(12);
        creature.setStrength(7);

        System.out.println(
                "Creature has a max stat of " + creature.max()
                        + ", total stats=" + creature.sum()
                        + ", average stats=" + creature.average()
        );
    }
}
