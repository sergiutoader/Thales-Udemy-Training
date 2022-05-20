package behavioral.chainofresponsibility.codingexercise;

import java.util.ArrayList;
import java.util.List;

abstract class Creature
{
    public abstract int getAttack();
    public abstract int getDefense();
}

class Goblin extends Creature
{
    protected int baseAttack = 1;
    protected int baseDefense = 1;

    public Game game;

    public Goblin(Game game)
    {
        this.game = game;
    }

    @Override
    public int getAttack()
    {
        return baseAttack + getGoblinKingBonus();
    }

    @Override
    public int getDefense()
    {
        return baseDefense + getGoblinBonus();
    }

    protected int getGoblinKingBonus() {
        int attack = 0;
        for (Creature c : game.creatures) {
            if (!c.equals(this) && c instanceof GoblinKing) {
                attack += 1;
            }
        }

        return attack;
    }

    protected int getGoblinBonus() {
        int defense = 0;
        for (Creature c : game.creatures) {
            if (!c.equals(this) && c instanceof Goblin) {
                defense += 1;
            }
        }

        return defense;
    }
}

class GoblinKing extends Goblin
{

    public GoblinKing(Game game)
    {
        super(game);
        baseDefense = 3;
        baseAttack = 3;
    }
}

class Game
{
    public List<Creature> creatures = new ArrayList<>();
}

public class Demo {
    public static void main(String[] args) {

    }
}
