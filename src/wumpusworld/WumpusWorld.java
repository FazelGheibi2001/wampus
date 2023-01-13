package wumpusworld;

import java.util.Vector;

public class WumpusWorld {
    public static void main(String[] args) {
        WumpusWorld ww = new WumpusWorld();
    }

    public WumpusWorld() {
        String option = Config.getOption();
        if (option.equalsIgnoreCase("gui")) {
            showGUI();
        }
        if (option.equalsIgnoreCase("sim")) {
            runSimulator();
        }
        if (option.equalsIgnoreCase("simdb")) {
            runSimulatorDB();
        }
    }

    private void showGUI() {
        new Pattern();
    }

    private void runSimulatorDB() {
        MapReader mr = new MapReader();
        Vector<WorldMap> maps = mr.readMaps();

        double totScore = 0;
        for (int i = 0; i < maps.size(); i++) {
            World w = maps.get(i).generateWorld();
            totScore += (double) runSimulation(w);
        }
        totScore = totScore / (double) maps.size();
        System.out.println("Average score: " + totScore);
    }

    private void runSimulator() {
        double totScore = 0;
        for (int i = 0; i < 10; i++) {
            WorldMap w = MapGenerator.getRandomMap(i);
            totScore += (double) runSimulation(w.generateWorld());
        }
        totScore = totScore / (double) 10;
        System.out.println("Average score: " + totScore);
    }

    private int runSimulation(World w) {
        int actions = 0;
        Agent a = new MyAgent(w);
        while (!w.gameOver()) {
            a.doAction();
            actions++;
        }
        int score = w.getScore();
        System.out.println("Simulation ended after " + actions + " actions. Score " + score);
        return score;
    }
}
