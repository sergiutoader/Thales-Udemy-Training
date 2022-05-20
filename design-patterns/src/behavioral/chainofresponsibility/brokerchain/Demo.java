package behavioral.chainofresponsibility.brokerchain;

import java.util.*;
import java.util.function.Consumer;

// Chain of Responsibility, Observer, Mediator, (-)Memento
// CQS
class Event<Args> {
    private int index = 0;
    private Map<Integer, Consumer<Args>> handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler) {
        int i = index;
        handlers.put(index++, handler);

        return i;
    }

    public void unsubscribe(int key) {
        handlers.remove(key);
    }

    public void fire(Args args) {
        for (Consumer<Args> handler : handlers.values()) {
            handler.accept(args);
        }
    }
}

class Query {
    public String creatureName;
    enum Argument {
        ATTACK,
        DEFENSE;
    }
    public Argument argument;
    public int result;


    public Query(String creatureName, Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game {
    public Event<Query> queries = new Event<>();
}

class Creature {
    private Game game;
    public String name;

    public int baseAttack, baseDefense;

    public Creature(Game game, String name,
                    int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack() {
        final Query q = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    int getDefense() {
        final Query q = new Query(name, Query.Argument.DEFENSE, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

class CreatureModifier {
    protected Game game;
    protected Creature creature;

    public CreatureModifier(Game game, Creature creature) {
        this.game = game;
        this.creature = creature;
    }
}

class DoubleAttackModifier
        extends CreatureModifier
        implements AutoCloseable {
    private final int token;

    public DoubleAttackModifier(Game game, Creature creature) {
        super(game, creature);

       token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(creature.name)
                    && q.argument.equals(Query.Argument.ATTACK)) {
                q.result *= 2;
            }
        });
    }


    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

class IncreaseDefenceModifier extends CreatureModifier implements AutoCloseable {
    private final int token;

    public IncreaseDefenceModifier(Game game, Creature creature) {
        super(game, creature);

        token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(creature.name)
                    && q.argument.equals(Query.Argument.DEFENSE)) {
                q.result += 3;
            }
        });
    }

    @Override
    public void close() {
        game.queries.unsubscribe(token);
    }
}

public class Demo {

    public static void main(String[] args) {
        final Game game = new Game();
        final Creature goblin = new Creature(game, "Strong Goblin", 2, 2);
        System.out.println(goblin);

        //IncreaseDefenceModifier idm = new IncreaseDefenceModifier(game, goblin);
        try (final IncreaseDefenceModifier dam = new IncreaseDefenceModifier(game, goblin)) {
            System.out.println(goblin);
        }

        try (final DoubleAttackModifier dam = new DoubleAttackModifier(game, goblin)) {
            System.out.println(goblin);
        }

        System.out.println(goblin);

    }
}
