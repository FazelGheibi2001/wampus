package wumpusworld;

import java.util.Random;

public class MapGenerator {
    public static WorldMap getRandomMap(int seed) {
        Random rnd = new Random(seed);
        WorldMap w = new WorldMap(4);

        addRandomWumpus(w, rnd);
        addRandomGold(w, rnd);
        addRandomPit(w, rnd);
        addRandomPit(w, rnd);
        addRandomPit(w, rnd);

        return w;
    }

    private static void addRandomPit(WorldMap w, Random r) {
        boolean valid = false;
        while (!valid) {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1) && !w.hasPit(x, y)) {
                valid = true;
                w.addPit(x, y);
            }
        }
    }

    private static void addRandomWumpus(WorldMap w, Random r) {
        boolean valid = false;
        while (!valid) {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1)) {
                valid = true;
                w.addWumpus(x, y);
            }
        }
    }

    private static void addRandomGold(WorldMap w, Random r) {
        boolean valid = false;
        while (!valid) {
            int x = rnd(r);
            int y = rnd(r);
            if (!(x == 1 && y == 1)) {
                valid = true;
                w.addGold(x, y);
            }
        }
    }

    private static int rnd(Random rnd) {
        return rnd.nextInt(4) + 1;
    }
}
