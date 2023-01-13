package wumpusworld;

public class Location implements Comparable {
    public int x;
    public int y;
    public int prio;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
        this.prio = 0;
    }

    public Location(int x, int y, int prio) {
        this.x = x;
        this.y = y;
        this.prio = prio;
    }

    public int compareTo(Object o) {
        Location l = (Location) o;
        return Integer.compare(prio, l.prio);
    }
}
