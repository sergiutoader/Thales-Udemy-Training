package behavioral.observer.codingexercise;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

interface Observer<T> {
    void handle(PropertyChangedEventArgs<T> args);
}

class PropertyChangedEventArgs<T> {
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangedEventArgs(T source,
                                    String propertyName,
                                    Object newValue) {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

// Observable
class Game
{
    // todo
    private List<Rat> rats = new ArrayList<>();

    public void addRat(Rat rat) {
        rats.add(rat);
        propertyChanged(this, "size", rats.size());
    }

    public void removeRat(Rat rat) {
        rats.remove(rat);
        propertyChanged(this, "size", rats.size());

    }

    protected void propertyChanged(
            Game source,
            String propertyName,
            Object newValue
    ) {
        for (Rat rat : rats) {
            rat.handle(
                    new PropertyChangedEventArgs<>(source, propertyName, newValue)
            );
        }
    }
}

// Observer
class Rat implements Closeable, Observer<Game>
{
    private Game game;
    public int attack = 1;

    public Rat(Game game)
    {
        this.game = game;
        // todo: rat enters game here
        game.addRat(this);
    }

    @Override
    public void close()
    {
        // todo: rat dies ;(
        game.removeRat(this);
    }

    @Override
    public void handle(PropertyChangedEventArgs<Game> args) {
        attack = (int)args.newValue;
    }
}

public class Demo {

}