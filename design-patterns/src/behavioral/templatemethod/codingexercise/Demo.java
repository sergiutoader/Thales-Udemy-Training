package behavioral.templatemethod.codingexercise;

class Creature
{
    public int attack, health;

    public Creature(int attack, int health)
    {
        this.attack = attack;
        this.health = health;
    }
}

abstract class CardGame
{
    public Creature [] creatures;

    public CardGame(Creature[] creatures)
    {
        this.creatures = creatures;
    }

    // returns -1 if no clear winner (both alive or both dead)
    public int combat(int creature1, int creature2)
    {
        Creature first = creatures[creature1];
        Creature second = creatures[creature2];
        hit(first, second);
        hit(second, first);
        boolean creature1Dead = first.health <= 0;
        boolean creature2Dead = second.health <= 0;

        if ((creature1Dead && creature2Dead)
                || (!creature1Dead && !creature2Dead)) {
            return -1;
        }

        if (creature1Dead) {
            return creature2;
        }

        return creature1;
    }

    // attacker hits other creature
    protected abstract void hit(Creature attacker, Creature other);
}

class TemporaryCardDamageGame extends CardGame
{
    public TemporaryCardDamageGame(Creature[] creatures) {
        super(creatures);
    }

    @Override
    protected void hit(Creature attacker, Creature other) {
        int originalOtherHealth = other.health;

        other.health -= attacker.attack;

        if (other.health > 0) {
            other.health = originalOtherHealth;
        }
    }
}

class PermanentCardDamageGame extends CardGame
{
    public PermanentCardDamageGame(Creature[] creatures) {
        super(creatures);
    }

    @Override
    protected void hit(Creature attacker, Creature other) {
        other.health -= attacker.attack;
    }
}

public class Demo {
    public static void main(String[] args) {

    }
}
