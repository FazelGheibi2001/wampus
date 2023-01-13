package wumpusworld;

import java.util.Vector;


public class World {
    private int size;
    private String[][] w;
    private int pX = 1;
    private int pY = 1;
    private boolean wumpusAlive = true;
    private boolean hasArrow = true;
    private boolean isInPit = false;
    private boolean hasGold = false;
    private boolean gameOver = false;
    private int score = 0;
    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;
    private int dir = DIR_RIGHT;
    public static final String BREEZE = "B";
    public static final String STENCH = "S";
    public static final String PIT = "P";
    public static final String WUMPUS = "W";
    public static final String GLITTER = "G";
    public static final String GOLD = "T";
    public static final String UNKNOWN = "U";
    public static final String A_MOVE = "m";
    public static final String A_GRAB = "g";
    public static final String A_CLIMB = "c";
    public static final String A_SHOOT = "s";
    public static final String A_TURN_LEFT = "l";
    public static final String A_TURN_RIGHT = "r";

    public World(int size) {
        this.size = size;
        w = new String[size + 1][size + 1];

        for (int x = 0; x <= size; x++) {
            for (int y = 0; y <= size; y++) {
                w[x][y] = UNKNOWN;
            }
        }

        setVisited(1, 1);
    }

    public World(String[][] w1, int size, int px, int py, int dirr) {
        this.size = size;
        pX = px;
        pY = py;
        dir = dirr;
        this.w = w1;
    }

    public int getScore() {
        return score;
    }

    public int getSize() {
        return size;
    }

    public boolean gameOver() {
        return gameOver;
    }

    public int getPlayerX() {
        return pX;
    }

    public int getPlayerY() {
        return pY;
    }

    public boolean isInPit() {
        return isInPit;
    }

    public boolean hasArrow() {
        return hasArrow;
    }

    public boolean wumpusAlive() {
        return wumpusAlive;
    }

    public boolean hasGold() {
        return hasGold;
    }

    public int getDirection() {
        return dir;
    }

    public boolean hasBreeze(int x, int y) {
        if (!isValidPosition(x, y)) return false;
        if (isUnknown(x, y)) return false;

        if (w[x][y].contains(BREEZE))
            return true;
        else
            return false;
    }

    public boolean hasStench(int x, int y) {
        if (!isValidPosition(x, y)) return false;
        if (isUnknown(x, y)) return false;

        if (w[x][y].contains(STENCH))
            return true;
        else
            return false;
    }

    public boolean hasGlitter(int x, int y) {
        if (!isValidPosition(x, y)) return false;
        if (isUnknown(x, y)) return false;

        if (w[x][y].contains(GLITTER))
            return true;
        else
            return false;
    }

    public boolean hasPit(int x, int y) {
        if (!isValidPosition(x, y)) return false;
        if (isUnknown(x, y)) return false;

        if (w[x][y].contains(PIT))
            return true;
        else
            return false;
    }

    public boolean hasWumpus(int x, int y) {
        if (!isValidPosition(x, y)) return false;
        if (isUnknown(x, y)) return false;

        if (w[x][y].contains(WUMPUS))
            return true;
        else
            return false;
    }

    public boolean hasPlayer(int x, int y) {
        if (pX == x && pY == y) {
            return true;
        }
        return false;
    }

    public boolean isVisited(int x, int y) {
        if (!isValidPosition(x, y)) return false;

        return !isUnknown(x, y);
    }

    public boolean isUnknown(int x, int y) {
        if (!isValidPosition(x, y)) return false;

        if (w[x][y].contains(UNKNOWN))
            return true;
        else
            return false;
    }

    public boolean isValidPosition(int x, int y) {
        if (x < 1) return false;
        if (y < 1) return false;
        if (x > size) return false;
        if (y > size) return false;
        return true;
    }

    private void append(int x, int y, String s) {
        if (!isValidPosition(x, y))
            return;

        if (!w[x][y].contains(s)) {
            w[x][y] += s;
        }
    }

    public void addWumpus(int x, int y) {
        if (!w[x][y].contains(WUMPUS)) {
            append(x, y, WUMPUS);
            append(x - 1, y, STENCH);
            append(x + 1, y, STENCH);
            append(x, y - 1, STENCH);
            append(x, y + 1, STENCH);
        }
    }

    public World cloneWorld() {
        //this.size = size;
        String[][] w1 = new String[size + 1][size + 1];
        int px, py, dirr;
        px = pX;
        py = pY;
        dirr = dir;
        for (int x = 0; x <= size; x++) {
            for (int y = 0; y <= size; y++) {
                w1[x][y] = w[x][y];
            }
        }

        return new World(w1, size, px, py, dirr);
    }

    public void addPit(int x, int y) {
        if (!w[x][y].contains(PIT)) {
            append(x, y, PIT);
            append(x - 1, y, BREEZE);
            append(x + 1, y, BREEZE);
            append(x, y - 1, BREEZE);
            append(x, y + 1, BREEZE);
        }
    }

    public void addGold(int x, int y) {
        if (!w[x][y].contains(GLITTER)) {
            append(x, y, GLITTER);
        }
    }

    private void setVisited(int x, int y) {
        if (w[x][y].contains(UNKNOWN)) {
            w[x][y] = w[x][y].replaceAll(UNKNOWN, "");
        }
    }

    public boolean doAction(String a) {
        if (gameOver) return false;

        //Each action costs 1 score
        score -= 1;

        if (a.equals(A_MOVE)) {
            if (!isInPit) {
                if (dir == DIR_LEFT) return move(pX - 1, pY);
                if (dir == DIR_RIGHT) return move(pX + 1, pY);
                if (dir == DIR_UP) return move(pX, pY + 1);
                if (dir == DIR_DOWN) return move(pX, pY - 1);
            }
        }
        if (a.equals(A_TURN_LEFT)) {
            dir--;
            if (dir < 0) dir = 3;
            return true;
        }
        if (a.equals(A_TURN_RIGHT)) {
            dir++;
            if (dir > 3) dir = 0;
            return true;
        }
        if (a.equals(A_GRAB)) {
            if (hasGlitter(pX, pY)) {
                w[pX][pY] = w[pX][pY].replaceAll(GLITTER, "");
                score += 1000;
                hasGold = true;
                gameOver = true;
                return true;
            }
        }
        if (a.equals(A_SHOOT)) {
            if (hasArrow) {
                score -= 10;
                hasArrow = false;
                shoot();
                return true;
            }
        }
        if (a.equals(A_CLIMB)) {
            isInPit = false;
        }

        //Action failed
        return false;
    }

    private void shoot() {
        if (dir == DIR_RIGHT) {
            for (int x = pX; x <= size; x++) {
                if (w[x][pY].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_LEFT) {
            for (int x = pX; x >= 0; x--) {
                if (w[x][pY].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_UP) {
            for (int y = pY; y <= size; y++) {
                if (w[pX][y].contains(WUMPUS)) removeWumpus();
            }
        }
        if (dir == DIR_DOWN) {
            for (int y = pY; y >= 0; y--) {
                if (w[pX][y].contains(WUMPUS)) removeWumpus();
            }
        }
    }

    private void removeWumpus() {
        for (int x = 1; x <= 4; x++) {
            for (int y = 1; y <= 4; y++) {
                w[x][y] = w[x][y].replaceAll(WUMPUS, "");
                w[x][y] = w[x][y].replaceAll(STENCH, "");
            }
        }

        wumpusAlive = false;
    }

    private boolean move(int nX, int nY) {
        //Check if valid
        if (!isValidPosition(nX, nY)) {
            return false;
        }

        pX = nX;
        pY = nY;

        setVisited(pX, pY);

        if (hasWumpus(pX, pY)) {
            score -= 1000;
            gameOver = true;
        }
        if (hasPit(pX, pY)) {
            score -= 1000;
            isInPit = true;
        }

        return true;
    }
}
