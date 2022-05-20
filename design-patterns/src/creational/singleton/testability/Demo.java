package creational.singleton.testability;

import com.google.common.collect.Iterables;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import org.junit.Test;

import javax.xml.crypto.Data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

interface Database {
    int getPopulation(String name);
}

public class Demo {
    public static void main(String[] args) {

    }
}

class SingletonDatabase implements Database {
    private final Dictionary<String, Integer> capitals
            = new Hashtable<>();

    private static int instanceCount = 0;

    public static int getCount() { return instanceCount; }

    private SingletonDatabase() {
        instanceCount++;
        System.out.println("Initializing Database");

        try {
            final File f = new File(
                    SingletonDatabase.class.getProtectionDomain()
                            .getCodeSource().getLocation().getPath()
            );

            final Path fullPath = Paths.get(f.getPath(), "capitals.txt");
            final List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2)
                    .forEach(kv -> capitals.put(kv.get(0),
                            Integer.parseInt(kv.get(1))
                    ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();

    public static SingletonDatabase getInstance() {
        return INSTANCE;
    }

    public int getPopulation(String city) {
        return capitals.get(city);
    }
}

class SingletonRecordFinder {
    public int getTotalPopulation(List<String> names) {
        int result = 0;

        for(String name : names) {
            result += SingletonDatabase.getInstance().getPopulation(name);
        }

        return result;
    }
}

class ConfigurableRecordFinder {
    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names) {
        int result = 0;

        for(String name : names) {
            result += database.getPopulation(name);
        }

        return result;
    }
}

class DummyDatabase implements Database {

    private Dictionary<String, Integer> dummyData = new Hashtable<>();

    public DummyDatabase() {
        dummyData.put("alpha", 1);
        dummyData.put("beta", 2);
        dummyData.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return dummyData.get(name);
    }
}


class Tests {
    @Test // not a unit test, an integration test
    public void singletonTotalPopulationTest() {
        final SingletonRecordFinder rf = new SingletonRecordFinder();
        final List<String> names = List.of("London", "Moscow");

        int tp = rf.getTotalPopulation(names);
        assertEquals(111111111 + 100000000, tp);
    }

    @Test
    public void dependantPopulationTest() {
        final DummyDatabase db = new DummyDatabase();
        final ConfigurableRecordFinder crf = new ConfigurableRecordFinder(db);
        assertEquals(4, crf.getTotalPopulation(List.of("alpha", "gamma")));
    }
}