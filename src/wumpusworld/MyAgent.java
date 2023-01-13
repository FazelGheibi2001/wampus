package wumpusworld;

public class MyAgent implements Agent {
    private World w;
    int rnd;
    int wumpus = 0, pa = 0, pb = 0, minvalue = 100, t = 1, minscore = 100, k1, op, k = 1, fp = 0;
    int c2 = 0, c3 = 0, path = 0;
    int[][] map = new int[4][4];
    int[] b = new int[10];
    int pbb = 0;
    int pd = 0;
    int path1 = 0;

    public MyAgent(World world) {
        w = world;
    }

    public void direction(int x) {
        int i = 0, dif;

        if (w.getDirection() == World.DIR_RIGHT) {
            System.out.println("I am facing Right");
            i = 1;
        }
        if (w.getDirection() == World.DIR_LEFT) {
            System.out.println("I am facing Left");
            i = 2;
        }
        if (w.getDirection() == World.DIR_UP) {
            System.out.println("I am facing Up");
            i = 3;
        }
        if (w.getDirection() == World.DIR_DOWN) {
            System.out.println("I am facing Down");
            i = 4;
        }
        dif = i - x;

        if (dif == 0)
            w.doAction(World.A_MOVE);
        if ((i == 1 && x == 2) || (x == 1 && i == 2) || (i == 3 && x == 4) || (x == 3 && i == 4)) {
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_MOVE);

        }

        if ((i == 4 && x == 2) || (i == 2 && x == 3) || (i == 3 && x == 1) || (i == 1 && x == 4)) {
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_MOVE);
        }

        if ((x == 4 && i == 2) || (x == 2 && i == 3) || (x == 3 && i == 1) || (x == 1 && i == 4)) {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_MOVE);
        }

    }

    public void arrow(int x) {
        int i = 0, dif;

        if (w.getDirection() == World.DIR_RIGHT) {
            System.out.println("I am facing Right");
            i = 1;
        }
        if (w.getDirection() == World.DIR_LEFT) {
            System.out.println("I am facing Left");
            i = 2;
        }
        if (w.getDirection() == World.DIR_UP) {
            System.out.println("I am facing Up");
            i = 3;
        }
        if (w.getDirection() == World.DIR_DOWN) {
            System.out.println("I am facing Down");
            i = 4;
        }
        dif = i - x;
        if (dif == 0)
            w.doAction(World.A_SHOOT);
        if ((i == 1 && x == 2) || (x == 1 && i == 2) || (i == 3 && x == 4) || (x == 3 && i == 4)) {
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_SHOOT);

        }

        if ((i == 4 && x == 2) || (i == 2 && x == 3) || (i == 3 && x == 1) || (i == 1 && x == 4)) {
            w.doAction(World.A_TURN_RIGHT);
            w.doAction(World.A_SHOOT);
        }

        if ((x == 4 && i == 2) || (x == 2 && i == 3) || (x == 3 && i == 1) || (x == 1 && i == 4)) {
            w.doAction(World.A_TURN_LEFT);
            w.doAction(World.A_SHOOT);
        }

    }

    public void pathDestination(int[][] map, int[] a, int x, int y, int c1, int move, int score, int fx, int fd) {

        int k;

        if (x == fx && y == fd) {
            if (minscore >= score) {
                minscore = score;
                k1 = a[0];
                for (int i = 0; i <= c1; i++)
                    b[i] = a[i];
                pd = c1;
                if (c1 > 0) {
                    k = 1;
                    fp = c1;
                    path = 1;
                }
            }
        } else {
            if (map[x][y] % 2 == 0 && map[x][y] > 0) {
                k = map[x][y] / 2;
                score = score + k;

            }
            if (map[x][y] != 3 && map[x][y] != 7 && map[x][y] != -3) {
                if (move != 1 && (y + 1) <= 3 && c1 < 6) {

                    // movie right

                    if (map[x][y + 1] == -3) {

                        c1++;
                        a[c1] = 5;
                        pathDestination(map, a, x, y + 1, c1, 2, score, fx, fd);
                        a[c1] = 0;
                        c1--;
                    } else {
                        c1++;
                        a[c1] = 1;
                        pathDestination(map, a, x, y + 1, c1, 2, score, fx, fd);
                        a[c1] = 0;
                        c1--;
                    }
                }
                if (move != 2 && (y - 1) >= 0 && c1 < 6) {
                    if (map[x][y - 1] == -3) {

                        c1++;
                        a[c1] = 6;
                        pathDestination(map, a, x, y - 1, c1, 1, score, fx, fd);
                        a[c1] = 0;
                        c1--;

                    } else {

                        c1++;
                        a[c1] = 2;
                        pathDestination(map, a, x, y - 1, c1, 1, score, fx, fd);
                        a[c1] = 0;
                        c1--;
                    }
                }
                if (move != 3 && x - 1 >= 0 && c1 < 6) {
                    if (map[x - 1][y] == -3) {
                        c1++;
                        a[c1] = 7;
                        pathDestination(map, a, x - 1, y, c1, 4, score, fx, fd);
                        a[c1] = 0;
                        c1--;
                    } else {

                        c1++;
                        a[c1] = 3;
                        pathDestination(map, a, x - 1, y, c1, 4, score, fx, fd);
                        a[c1] = 0;
                        c1--;
                    }
                }
                if (move != 4 && x + 1 <= 3 && c1 < 6) {
                    if (map[x + 1][y] == -3) {

                        c1++;
                        a[c1] = 8;
                        pathDestination(map, a, x + 1, y, c1, 3, score, fx, fd);
                        a[c1] = 0;
                        c1--;
                    } else {

                        c1++;
                        a[c1] = 4;
                        pathDestination(map, a, x + 1, y, c1, 3, score, fx, fd);
                        c1--;
                    }
                }

            }

        }
    }

    public void pathbreeze(int[][] map, int[] a, int x, int y, int c1, int move) {
        int l = 0;

        if (map[x][y] % 2 == 0 && map[x][y] > 0) {

            if (minvalue >= map[x][y] || t == 1) {
                t++;
                minvalue = map[x][y];

            }
            if (minvalue == 2) {
                pb = 1;
                l = 1;
                t = 1;
                for (int i = 0; i <= c1; i++) {
                    b[i] = a[i];
                }
                System.out.println(c1);
                pd = c1;
                if (c1 > 0) {
                    k = 1;
                    fp = c1;
                    path = 1;
                }
                switch (b[0]) {
                    case 1:
                        direction(1);
                        break;
                    case 2: // left
                        direction(2);
                        break;
                    case 3: // up
                        direction(3);
                        break;
                    case 4: // down
                        direction(4);
                        break;
                }

            }
            if (t == 3 && l != 1 && minvalue < 6) {
                pb = 1;
                t = 1;
                for (int i = 0; i <= c1; i++)
                    b[i] = a[i];
                pd = c1;
                if (c1 > 0) {
                    k = 1;
                    fp = c1;
                    path = 1;
                }

                switch (a[0]) {
                    case 1:
                        direction(1);
                        break;
                    case 2: // left
                        direction(2);
                        break;
                    case 3: // up
                        direction(3);
                        break;
                    case 4: // down
                        direction(4);
                        break;
                }

            }
        } else {
            if (map[x][y] != 3 && map[x][y] != 7 && map[x][y] != -3 && pb != 1 && map[x][y] != -2) {
                if (move != 1 && (y + 1) <= 3 && pb != 1 && c1 < 6) {

                    // movie right

                    if (map[x][y + 1] == -3 && pb != 1) {
                        c1++;
                        a[c1] = 5;

                        pathbreeze(map, a, x, y + 1, c1, 2);
                        a[c1] = 0;

                        c1--;
                    } else {
                        c1++;
                        a[c1] = 1;
                        pathbreeze(map, a, x, y + 1, c1, 2);
                        a[c1] = 0;
                        c1--;
                    }
                }
                if (move != 2 && (y - 1) >= 0 && pb != 1 && c1 < 6) {
                    if (map[x][y - 1] == -3) {
                        c1++;
                        a[c1] = 6;
                        pathbreeze(map, a, x, y - 1, c1, 1);
                        a[c1] = 0;
                        c1--;

                    } else {

                        c1++;
                        a[c1] = 2;
                        pathbreeze(map, a, x, y - 1, c1, 1);
                        a[c1] = 0;
                        c1--;
                    }
                }
                if (move != 3 && x - 1 >= 0 && pb != 1 && c1 < 6) {
                    if (map[x - 1][y] == -3) {
                        c1++;
                        a[c1] = 7;
                        pathbreeze(map, a, x - 1, y, c1, 4);
                        a[c1] = 0;
                        c1--;

                    } else {
                        c1++;
                        a[c1] = 3;
                        pathbreeze(map, a, x - 1, y, c1, 4);
                        a[c1] = 0;
                        c1--;
                    }
                }
                if (move != 4 && x + 1 <= 3 && pb != 1 && c1 < 6) {
                    if (map[x + 1][y] == -3) {
                        c1++;
                        a[c1] = 8;
                        pathbreeze(map, a, x + 1, y, c1, 3);
                        a[c1] = 0;
                        c1--;

                    } else {

                        c1++;
                        a[c1] = 4;
                        pathbreeze(map, a, x + 1, y, c1, 3);
                        a[c1] = 0;
                        c1--;
                    }
                }

            }

        }

    }

    public void path(int[][] map, int[] a, int x, int y, int c1, int move) {

        if (x <= 3 && y <= 3 && x >= 0 && y >= 0)
            if (map[x][y] == 0 || (!w.isVisited(y + 1, 4 - x) && map[x][y] == 1)) {

                pa = 1;
                for (int i = 0; i < c1; i++)
                    b[i] = a[i];
                if (c1 > 0) {
                    k = 1;
                    fp = c1;
                    path = 1;
                }

                switch (a[0]) {
                    case 1:
                        direction(1);
                        break;
                    case 2: // left
                        direction(2);
                        break;
                    case 3: // up
                        direction(3);
                        break;
                    case 4: // down
                        direction(4);
                        break;
                }
                return;
            } else {
                if (c1 < 5)
                    if (x <= 3 && y <= 3 && x >= 0 && y >= 0)
                        if ((map[x][y] % 2 != 0 || map[x][y] < 0) && map[x][y] != 3 && map[x][y] != 7 && map[x][y] != -3
                                && pa != 1 && map[x][y] != -2) {

                            if (move != 1 && (y + 1) <= 3 && pa != 1 && c1 < 5) {

                                // movie right
                                if (map[x][y + 1] == -3) {

                                    a[c1] = 5;
                                    path(map, a, x, y + 1, c1, 2);
                                    a[c1] = 0;
                                    c1--;

                                } else {
                                    c1++;
                                    a[c1] = 1;
                                    path(map, a, x, y + 1, c1, 2);
                                    a[c1] = 0;
                                    c1--;
                                }
                            }
                            if (move != 2 && (y - 1) >= 0 && pa != 1 && c1 < 5) {
                                if (map[x][y - 1] == -3) {

                                    c1++;
                                    a[c1] = 6;
                                    path(map, a, x, y - 1, c1, 1);
                                    a[c1] = 0;
                                    c1--;
                                } else {

                                    c1++;
                                    a[c1] = 2;
                                    System.out.println(c1);
                                    path(map, a, x, y - 1, c1, 1);
                                    a[c1] = 0;
                                    c1--;
                                }
                            }
                            if (move != 3 && x - 1 >= 0 && pa != 1 && c1 < 5) {
                                if (map[x - 1][y] == -3) {

                                    c1++;
                                    a[c1] = 7;
                                    path(map, a, x - 1, y, c1, 4);
                                    a[c1] = 0;
                                    c1--;
                                } else {

                                    c1++;
                                    a[c1] = 3;

                                    path(map, a, x - 1, y, c1, 4);
                                    a[c1] = 0;
                                    c1--;
                                }
                            }
                            if (move != 4 && x + 1 <= 3 && pa != 1 && c1 < 5) {
                                if (map[x + 1][y] == -3) {
                                    c1++;
                                    a[c1] = 8;
                                    path(map, a, x + 1, y, c1, 3);
                                    a[c1] = 0;
                                    c1--;

                                } else {

                                    c1++;
                                    a[c1] = 4;
                                    path(map, a, x + 1, y, c1, 3);
                                    a[c1] = 0;
                                    c1--;
                                }
                            }
                        }

            }

    }

    public void breezeAndStrenchMethod(int[][] map, int cX, int cY, int x, int y) {
        int count = 0, Y = 0, X = 0, t1 = 0, d = 0, count1 = 0, d1 = 0;

        int[] a = new int[16];
        map[x][y] = -7;
        path = 0;
        k = 1;
        if (x - 1 >= 0) {
            if (!w.isVisited(cX, cY + 1)) {
                if (map[x - 1][y] != 1 && map[x - 1][y] != -7 && map[x - 1][y] != -4) {
                    if (!(map[x - 1][y] % 2 == 0 && map[x - 1][y] > 0)) {
                        if (map[x - 1][y] == 3 || map[x - 1][y] == 7) {

                            count1++;
                            X = x - 1;
                            Y = y;

                            d = 3;

                        } else {
                            X = x - 1;
                            Y = y;
                            map[x - 1][y] = 7;
                            d1 = 3;
                            count++;
                        }
                    }
                }
            }
        }

        if (y + 1 <= 3) {
            if (!w.isVisited(cX + 1, cY)) {
                if (map[x][y + 1] != 1 && map[x][y + 1] != -7 && map[x][y + 1] != -5 && map[x][y + 1] != -4) {
                    if (!(map[x][y + 1] % 2 == 0 && map[x][y + 1] > 0)) {
                        if (map[x][y + 1] == 3 || map[x][y + 1] == 7) {

                            count1++;
                            X = x;
                            Y = y + 1;

                            d = 1;

                        } else {
                            X = x;
                            Y = y + 1;
                            d1 = 1;
                            map[x][y + 1] = 7;
                            count++;
                        }
                    }
                }
            }

        }
        if (x + 1 <= 3) {
            if (!w.isVisited(cX, cY - 1)) {
                if (map[x + 1][y] != 1 && map[x + 1][y] != -7 && map[x + 1][y] != -4 && map[x + 1][y] != -5) {
                    if (!(map[x + 1][y] % 2 == 0 && map[x + 1][y] > 0)) {
                        if (map[x + 1][y] == 3 || map[x + 1][y] == 7) {

                            count1++;
                            X = x + 1;
                            d = 4;
                            Y = y;

                        } else {
                            X = x + 1;
                            Y = y;
                            d1 = 4;
                            map[x + 1][y] = 7;
                            count++;

                        }
                    }

                }
            }
        }

        if (y - 1 >= 0) {
            if (!w.isVisited(cX - 1, cY)) {
                if (map[x][y - 1] != 1 && map[x][y - 1] != -7 && map[x][y - 1] != -5 && map[x][y - 1] != -4) {
                    if (!(map[x][y - 1] % 2 == 0 && map[x][y - 1] > 0)) {
                        if (map[x][y - 1] == 3 || map[x][y - 1] == 7) {

                            count1++;
                            X = x;
                            y = y - 1;
                            d = 2;

                        } else {
                            X = x;
                            Y = y - 1;
                            map[x][y - 1] = 7;
                            d1 = 2;
                            count++;
                        }
                    }
                }
            }
        }
        if (x == 3 && y == 0) {
            arrow(1);
            if (w.hasBreeze(cX, cY) && w.hasStench(cX, cY)) {
                direction(1);
                map[3][1] = 3;
                map[2][0] = 2;
                map[3][0] = -4;

            } else {
                direction(1);
                map[3][1] = 2;
                map[2][0] = 2;
                map[3][0] = 4;

            }
            return;
        }

        if (count1 == 1) {

            count = 2;
            wumpus = 1;
            map[x][y] = 1;
            arrow(d);
            direction(4);
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++) {
                    if (map[i][j] == 3 || map[i][j] == -5)
                        map[i][j] = 1;
                    if (map[i][j] == 7)
                        map[i][j] = 2;
                }
            return;

        }

        if (count == 1) {
            wumpus = 1;
            map[x][y] = 1;
            arrow(d1);

            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++) {
                    if (map[i][j] == 3 || map[i][j] == -5)
                        map[i][j] = 1;
                    if (map[i][j] == 7)
                        map[i][j] = 2;
                }

        } else {
            if (w.isVisited(cX, cY + 1)) {
                if ((x - 1) >= 0) {
                    if ((y + 1) <= 3)
                        if ((!w.isVisited(cX + 1, cY + 1) && map[x - 1][y + 1] == 1)
                                || (map[x - 1][y] == 1 && !w.isVisited(cX, cY + 1))) {

                            t1 = 1;

                            d = 3;
                        } else if (y - 1 >= 0)
                            if ((!w.isVisited(cX - 1, cY + 1) && map[x - 1][y - 1] == 1)
                                    || (map[x - 1][y] == 1 && !w.isVisited(cX, cY + 1))) {
                                {

                                    t1 = 1;

                                    d = 3;
                                }
                            }
                }

            }
            if (w.isVisited(cX + 1, cY)) {

                if ((y + 1) <= 3) {
                    if ((x - 1) >= 0)
                        if ((!w.isVisited(cX + 1, cY + 1) && map[x - 1][y + 1] == 1)
                                || (map[x][y + 1] == 1 && !w.isVisited(cX + 1, cY))) {

                            t1 = 1;

                            d = 1;
                        } else if (x + 1 <= 3)
                            if ((!w.isVisited(cX + 1, cY - 1) && map[x + 1][y + 1] == 1)
                                    || (map[x][y + 1] == 1 && !w.isVisited(cX + 1, cY))) {
                                {

                                    t1 = 1;

                                    d = 1;
                                }
                            }
                }
            }
            if (w.isVisited(cX, cY - 1)) {

                if ((x + 1) <= 3) {
                    if ((y + 1) <= 3)
                        if ((!w.isVisited(cX + 1, cY - 1) && map[x + 1][y + 1] == 1)
                                || (map[x + 1][y] == 1 && !w.isVisited(cX, cY - 1))) {

                            t1 = 1;
                            d = 4;
                        } else if (y - 1 >= 0)
                            if ((!w.isVisited(cX - 1, cY - 1) && map[x - 1][y - 1] == 1)
                                    || (map[x + 1][y] == 1 && !w.isVisited(cX, cY - 1))) {
                                {

                                    t1 = 1;
                                    d = 4;
                                }
                            }
                }

            }

            if (w.isVisited(cX - 1, cY)) {

                if ((y - 1) >= 0) {
                    if ((x + 1) <= 3) {
                        if ((!w.isVisited(cX - 1, cY - 1) && map[x + 1][y - 1] == 1)
                                || (map[x][y - 1] == 1 && !w.isVisited(cX - 1, cY))) {
                            // move left
                            t1 = 1;
                            d = 2;
                        }
                    }

                    if ((x - 1) >= 0)
                        if ((!w.isVisited(cX - 1, cY + 1) && map[x - 1][y - 1] == 1)
                                || (map[x][y - 1] == 1 && !w.isVisited(cX - 1, cY))) {
                            // move left
                            t1 = 1;
                            d = 2;
                        }
                }
            } else {
                if ((y - 1) >= 0)
                    if (map[x][y - 1] == 1 && !w.isVisited(cX - 1, cY)) {
                        // move left
                        t1 = 1;
                        d = 2;
                    }

            }

        }

        if (t1 == 1)
            switch (d) {

                case 1:
                    direction(1);
                    break;
                case 2:
                    direction(2);
                    break;
                case 3:
                    direction(3);
                    break;
                case 4:
                    direction(4);
                    break;
            }

        if (t1 != 1) {
            pa = 0;
            count = 0;
            path(map, a, x, y, -1, 0);
            if (pa != 1)
                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        if (!w.isVisited(i + 1, j + 1))
                            count++;
            if (count <= 2) {
                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        if (!w.isVisited(i + 1, j + 1)) {
                            minscore = 100;

                            pathDestination(map, a, x, y, -1, 0, 0, i, j);

                            switch (b[0]) {
                                case 1:
                                    direction(1);
                                    break;
                                case 2: // left
                                    direction(2);
                                    break;
                                case 3: // up
                                    direction(3);
                                    break;
                                case 4: // down
                                    direction(4);
                                    break;
                                case 5: // shoot

                                    w.doAction(World.A_SHOOT);
                                    break;
                                case 6:

                                    w.doAction(World.A_SHOOT);
                                    break;
                                case 7:

                                    w.doAction(World.A_SHOOT);
                                    break;
                                case 8:

                                    w.doAction(World.A_SHOOT);
                                    break;
                                default: // random
                                    break;
                            }

                        }

            } else if (pa != 1) {
                minvalue = 100;
                pb = 0;
                t = 1;
                pathbreeze(map, a, x, y, -1, 0);
            }
        }
    }

    public void breezeMethod(int[][] map, int cX, int cY, int x, int y) {

        int count = 0, t1 = 0, d = 0, X = 0, Y = 0;
        int a[] = new int[16];

        map[x][y] = -4;

        if (x - 1 >= 0) {
            if (!w.isVisited(cX, cY + 1)) {

                if (map[x - 1][y] != 1 && map[x - 1][y] != -7 && map[x - 1][y] != -5 && map[x - 1][y] != -4
                        && map[x - 1][y] != -2) {

                    if (map[x - 1][y] == 3 || map[x - 1][y] == 7)
                        map[x - 1][y] = 4;
                    else
                        map[x - 1][y] = map[x - 1][y] + 2;

                    count++;
                    X = x - 1;
                    Y = y;
                }

            }
            if (map[x - 1][y] == -2)
                count++;
        }
        if (y + 1 <= 3) {
            if (!w.isVisited(cX + 1, cY)) {
                if (map[x][y + 1] != 1 && map[x][y + 1] != -7 && map[x][y + 1] != -5 && map[x][y + 1] != -4
                        && map[x][y + 1] != -2) {

                    if (map[x][y + 1] == 3 || map[x][y + 1] == 7)
                        map[x][y + 1] = 4;
                    else
                        map[x][y + 1] = map[x][y + 1] + 2;
                    count++;
                    x = x;
                    Y = y + 1;

                }

            }
            if (map[x][y + 1] == -2)
                count++;
        }
        if (x + 1 <= 3) {
            if (!w.isVisited(cX, cY - 1)) {
                if (map[x + 1][y] != 1 && map[x + 1][y] != -7 && map[x + 1][y] != -5 && map[x + 1][y] != -4
                        && map[x + 1][y] != -2) {

                    if (map[x + 1][y] == 3 || map[x + 1][y] == 7)
                        map[x + 1][y] = 4;
                    else
                        map[x + 1][y] = map[x + 1][y] + 2;
                    count++;
                    x = x + 1;
                    Y = y;
                }

            }
            if (map[x + 1][y] == -2)
                count++;
        }
        if (y - 1 >= 0) {
            if (!w.isVisited(cX - 1, cY)) {
                if (map[x][y - 1] != 1 && map[x][y - 1] != -7 && map[x][y - 1] != -5 && map[x][y - 1] != -4
                        && map[x][y - 1] != -2) {
                    if (map[x][y - 1] == 3 || map[x][y - 1] == 7)
                        map[x][y - 1] = 4;
                    else
                        map[x][y - 1] = map[x][y - 1] + 2;
                    count++;
                    X = x;
                    Y = y - 1;
                }

            }
            if (map[x][y - 1] == -2)
                count++;
        }
        if (count == 1) {
            map[x][y] = 1;
            map[X][Y] = -2;
        }

        if (w.isVisited(cX, cY + 1)) {
            if ((x - 1) >= 0) {
                if ((y + 1) <= 3)
                    if ((!w.isVisited(cX + 1, cY + 1) && map[x - 1][y + 1] == 1)
                            || (map[x - 1][y] == 1 && !w.isVisited(cX, cY + 1))) {

                        t1 = 1;

                        d = 3;
                    } else if (y - 1 >= 0)
                        if ((!w.isVisited(cX - 1, cY + 1) && map[x - 1][y - 1] == 1)
                                || (map[x - 1][y] == 1 && !w.isVisited(cX, cY + 1))) {
                            {

                                t1 = 1;

                                d = 3;
                            }
                        }
            }

        }
        if (w.isVisited(cX + 1, cY)) {

            if ((y + 1) <= 3) {
                if ((x - 1) >= 0)
                    if ((!w.isVisited(cX + 1, cY + 1) && map[x - 1][y + 1] == 1)
                            || (map[x][y + 1] == 1 && !w.isVisited(cX + 1, cY))) {

                        t1 = 1;

                        d = 1;
                    } else if (x + 1 <= 3)
                        if ((!w.isVisited(cX + 1, cY - 1) && map[x + 1][y + 1] == 1)
                                || (map[x][y + 1] == 1 && !w.isVisited(cX + 1, cY))) {
                            {

                                t1 = 1;

                                d = 1;
                            }
                        }
            }
        }
        if (w.isVisited(cX, cY - 1)) {

            if ((x + 1) <= 3) {
                if ((y + 1) <= 3)
                    if ((!w.isVisited(cX + 1, cY - 1) && map[x + 1][y + 1] == 1)
                            || (map[x + 1][y] == 1 && !w.isVisited(cX, cY - 1))) {

                        t1 = 1;
                        d = 4;
                    } else if (y - 1 >= 0)
                        if ((!w.isVisited(cX - 1, cY - 1) && map[x - 1][y - 1] == 1)
                                || (map[x + 1][y] == 1 && !w.isVisited(cX, cY - 1))) {
                            {

                                t1 = 1;
                                d = 4;
                            }
                        }
            }

        }

        if (w.isVisited(cX - 1, cY)) {
            if ((y - 1) >= 0) {
                if ((x + 1) <= 3) {
                    if ((!w.isVisited(cX - 1, cY - 1) && map[x + 1][y - 1] == 1)
                            || (map[x][y - 1] == 1 && !w.isVisited(cX - 1, cY))) {
                        // move left
                        t1 = 1;
                        d = 2;
                    }
                }

                if ((x - 1) >= 0)
                    if ((!w.isVisited(cX - 1, cY + 1) && map[x - 1][y - 1] == 1)
                            || (map[x][y - 1] == 1 && !w.isVisited(cX - 1, cY))) {
                        // move left
                        t1 = 1;
                        d = 2;
                    }
            }
        } else {
            if ((y - 1) >= 0)
                if (map[x][y - 1] == 1 && !w.isVisited(cX - 1, cY)) {
                    // move left
                    t1 = 1;
                    d = 2;
                }

        }

        switch (d) {

            case 1:
                direction(1);
                break;
            case 2:
                direction(2);
                break;
            case 3:
                direction(3);
                break;
            case 4:
                direction(4);
                break;
        }

        if (t1 != 1) {
            pa = 0;
            count = 0;
            path(map, a, x, y, -1, 0);
            if (pa != 1)
                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        if (!w.isVisited(i + 1, j + 1))
                            count++;

            if (count <= 2 && pa != 1) {
                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        if (!w.isVisited(i + 1, j + 1)) {
                            minscore = 100;
                            pathDestination(map, a, x, y, -1, 0, 0, i, j);
                            switch (a[0]) {
                                case 1: // right
                                    direction(1);
                                    break;
                                case 2: // left
                                    direction(2);
                                    break;
                                case 3: // up
                                    direction(3);
                                    break;
                                case 4: // down
                                    direction(4);
                                    break;
                            }

                            break;
                        }

            } else if (pa != 1) {
                minvalue = 100;
                pb = 0;
                t = 1;
                pathbreeze(map, a, x, y, -1, 0);
            }
        }

    }

    public void strenchMethod(int[][] map, int cX, int cY, int x, int y) {

        int count = 0, Y = 0, X = 0, t1 = 0, d = 0, count1 = 0, d1 = 0;

        int[] a = new int[16];
        map[x][y] = -5;
        path = 0;
        k = 1;

        if (x - 1 >= 0) {
            if (!w.isVisited(cX, cY + 1)) {
                if (map[x - 1][y] != 1 && map[x - 1][y] != -7 && map[x - 1][y] != -4) {
                    if (!(map[x - 1][y] % 2 == 0 && map[x - 1][y] > 0)) {
                        if (map[x - 1][y] == 3 || map[x - 1][y] == 7) {

                            count1++;
                            X = x - 1;
                            Y = y;

                            d = 3;

                        } else {
                            X = x - 1;
                            Y = y;
                            map[x - 1][y] = 3;
                            d1 = 3;
                            count++;
                        }
                    }
                }
            }
        }

        if (y + 1 <= 3) {
            if (!w.isVisited(cX + 1, cY)) {
                if (map[x][y + 1] != 1 && map[x][y + 1] != -7 && map[x][y + 1] != -5 && map[x][y + 1] != -4) {
                    if (!(map[x][y + 1] % 2 == 0 && map[x][y + 1] > 0)) {
                        if (map[x][y + 1] == 3 || map[x][y + 1] == 7) {

                            count1++;
                            X = x;
                            Y = y + 1;

                            d = 1;

                        } else {
                            X = x;
                            Y = y + 1;
                            d1 = 1;
                            map[x][y + 1] = 3;
                            count++;
                        }
                    }
                }
            }

        }
        if (x + 1 <= 3) {
            if (!w.isVisited(cX, cY - 1)) {
                if (map[x + 1][y] != 1 && map[x + 1][y] != -7 && map[x + 1][y] != -4 && map[x + 1][y] != -5) {
                    if (!(map[x + 1][y] % 2 == 0 && map[x + 1][y] > 0)) {
                        if (map[x + 1][y] == 3 || map[x + 1][y] == 7) {

                            count1++;
                            X = x + 1;
                            d = 4;
                            Y = y;

                        } else {
                            X = x + 1;
                            Y = y;
                            d1 = 4;
                            map[x + 1][y] = 3;
                            count++;

                        }
                    }

                }
            }
        }

        if (y - 1 >= 0) {
            if (!w.isVisited(cX - 1, cY)) {
                if (map[x][y - 1] != 1 && map[x][y - 1] != -7 && map[x][y - 1] != -5 && map[x][y - 1] != -4) {
                    if (!(map[x][y - 1] % 2 == 0 && map[x][y - 1] > 0)) {
                        if (map[x][y - 1] == 3 || map[x][y - 1] == 7) {

                            count1++;
                            X = x;
                            y = y - 1;
                            d = 2;

                        } else {
                            X = x;
                            Y = y - 1;
                            map[x][y - 1] = 3;
                            d1 = 2;
                            count++;
                        }
                    }
                }
            }
        }
        if (x == 3 && y == 0) {
            arrow(1);
            if (w.hasStench(cX, cY)) {
                direction(1);
                map[3][1] = 1;
                map[2][0] = 3;
                map[3][0] = 1;

            } else {
                direction(1);
                map[3][1] = 1;
                map[2][0] = 1;
                map[3][0] = 1;

            }
            return;
        }

        if (count1 == 1) {

            count = 2;
            wumpus = 1;
            map[x][y] = 1;
            arrow(d);

            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++) {
                    if (map[i][j] == 3 || map[i][j] == -5)
                        map[i][j] = 1;
                    if (map[i][j] == 7)
                        map[i][j] = 2;
                }
            return;

        }

        if (count == 1) {
            wumpus = 1;
            map[x][y] = 1;
            arrow(d1);

            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++) {
                    if (map[i][j] == 3 || map[i][j] == -5)
                        map[i][j] = 1;
                    if (map[i][j] == 7)
                        map[i][j] = 2;
                }

        } else {
            if (w.isVisited(cX, cY + 1)) {
                if ((x - 1) >= 0) {
                    if ((y + 1) <= 3)
                        if ((!w.isVisited(cX + 1, cY + 1) && map[x - 1][y + 1] == 1)) {

                            t1 = 1;

                            d = 3;
                        } else if (y - 1 >= 0)
                            if ((!w.isVisited(cX - 1, cY + 1) && map[x - 1][y - 1] == 1)) {
                                {

                                    t1 = 1;

                                    d = 3;
                                }
                            }
                }

            }
            if (w.isVisited(cX + 1, cY)) {

                if ((y + 1) <= 3) {
                    if ((x - 1) >= 0)
                        if ((!w.isVisited(cX + 1, cY + 1) && map[x - 1][y + 1] == 1)) {

                            t1 = 1;

                            d = 1;
                        } else if (x + 1 <= 3)
                            if ((!w.isVisited(cX + 1, cY - 1) && map[x + 1][y + 1] == 1)) {
                                {

                                    t1 = 1;

                                    d = 1;
                                }
                            }
                }
            }
            if (w.isVisited(cX, cY - 1)) {

                if ((x + 1) <= 3) {
                    if ((y + 1) <= 3)
                        if ((!w.isVisited(cX + 1, cY - 1) && map[x + 1][y + 1] == 1)) {

                            t1 = 1;

                            d = 4;
                        } else if (y - 1 >= 0)
                            if ((!w.isVisited(cX - 1, cY - 1) && map[x - 1][y - 1] == 1)) {
                                {

                                    t1 = 1;

                                    d = 4;
                                }
                            }
                }

            }
            if (w.isVisited(cX - 1, cY)) {

                if ((y - 1) >= 0) {
                    if ((x + 1) <= 3) {
                        if ((map[x + 1][y - 1] == 1)) {
                            // move left
                            t1 = 1;
                            d = 2;

                        }
                    }

                    if ((x - 1) >= 0)
                        if ((map[x - 1][y - 1] == 1)) {
                            // move left
                            t1 = 1;
                            d = 2;

                        }
                }
            }

            switch (d) {

                case 1:
                    direction(1);
                    break;
                case 2:
                    direction(2);
                    break;
                case 3:
                    direction(3);
                    break;
                case 4:
                    direction(4);
                    break;
            }

            if (t1 != 1) {
                pa = 0;
                count = 0;
                path(map, a, x, y, -1, 0);
                if (pa != 1)
                    for (int i = 0; i < 4; i++)
                        for (int j = 0; j < 4; j++)
                            if (!w.isVisited(i + 1, j + 1))
                                count++;
                if (count <= 2) {
                    for (int i = 0; i < 4; i++)
                        for (int j = 0; j < 4; j++)
                            if (!w.isVisited(i + 1, j + 1)) {
                                minscore = 100;
                                pathDestination(map, a, x, y, -1, 0, 0, i, j);

                                switch (b[0]) {
                                    case 1: // right
                                        direction(1);
                                        break;
                                    case 2: // left
                                        direction(2);
                                        break;
                                    case 3: // up
                                        direction(3);
                                        break;
                                    case 4: // down
                                        direction(4);
                                        break;
                                }

                                break;
                            }

                } else if (pa != 1) {
                    minvalue = 100;
                    pb = 0;
                    t = 1;
                    pathbreeze(map, a, x, y, -1, 0);
                }
            }
        }

    }

    public void doAction() {
        int cX = w.getPlayerX();
        int cY = w.getPlayerY();

        int transformX = 4 - cY;
        int transformY = cX - 1;

        if (w.hasBreeze(cX, cY)) {
            System.out.println("I am in a Breeze");
        }
        if (w.hasStench(cX, cY)) {
            System.out.println("I am in a Stench");
        }
        if (w.hasPit(cX, cY)) {
            System.out.println("I am in a Pit");
        }
        if (w.getDirection() == World.DIR_RIGHT) {
            System.out.println("I am facing Right");
        }
        if (w.getDirection() == World.DIR_LEFT) {
            System.out.println("I am facing Left");
        }
        if (w.getDirection() == World.DIR_UP) {
            System.out.println("I am facing Up");
        }
        if (w.getDirection() == World.DIR_DOWN) {
            System.out.println("I am facing Down");
        }

        if (path == 1) {

            if (w.hasGlitter(cX, cY)) {
                w.doAction(World.A_GRAB);
                return;
            }

            if (k <= (fp))
                direction(b[k]);
            k++;
            if (k == (fp + 1)) {
                path = 0;
                k = 1;
            }

        } else {

            if (w.hasStench(cX, cY) && w.isInPit()) {

                if (w.hasGlitter(cX, cY)) {
                    w.doAction(World.A_GRAB);
                    return;
                }

                map[transformX][transformY] = -2;
                w.doAction(World.A_CLIMB);
                strenchMethod(map, cX, cY, transformX, transformY);

            }

            if (w.hasGlitter(cX, cY) && w.hasBreeze(cX, cY)) {
                w.doAction(World.A_GRAB);
                return;
            }
            if (w.hasBreeze(cX, cY) && w.hasStench(cX, cY)) {
                if (wumpus == 0)
                    breezeAndStrenchMethod(map, cX, cY, transformX, transformY);
                else
                    breezeMethod(map, cX, cY, transformX, transformY);
            } else if (w.hasBreeze(cX, cY) && map[transformX][transformY] != -4 && map[transformX][transformY] != -2) {
                breezeMethod(map, cX, cY, transformX, transformY);
            } else if (w.hasStench(cX, cY)) {
                strenchMethod(map, cX, cY, transformX, transformY);
            } else if (w.hasGlitter(cX, cY)) {
                w.doAction(World.A_GRAB);
                return;
            } else if (w.isInPit()) {
                int dir = 0;
                int count = 0;
                int[] a = new int[50];
                map[transformX][transformY] = -2;

                w.doAction(World.A_CLIMB);
                if (transformY + 1 <= 3)
                    if (!w.isVisited(cX + 1, cY)) {
                        dir = 1;
                        direction(1);
                    }
                if (dir != 1)
                    if (transformY - 1 >= 0)
                        if ((!w.isVisited(cX - 1, cY))) {
                            dir = 1;
                            direction(2);
                        }
                if (dir != 1)
                    if (transformX - 1 >= 0)
                        if (!w.isVisited(cX, cY + 1)) {
                            dir = 1;
                            direction(3);
                        }

                if (dir != 1)
                    if (transformX + 1 <= 3)
                        if (!w.isVisited(cX, cY - 1)) {
                            dir = 1;
                            direction(4);
                        }
                if (dir == 1) {
                    pa = 0;
                    path(map, a, transformX, transformY, -1, 0);
                }
                if (dir != 1)
                    if (pa != 1)
                        for (int i = 0; i < 4; i++)
                            for (int j = 0; j < 4; j++)
                                if (!w.isVisited(i + 1, j + 1))
                                    count++;
                if (dir != 1 && pa == 0)
                    if (count <= 2) {
                        for (int i = 0; i < 4; i++)
                            for (int j = 0; j < 4; j++)
                                if (!w.isVisited(i + 1, j + 1)) {
                                    minscore = 100;
                                    pathDestination(map, a, transformX, transformY, -1, 0, 0, i, j);

                                    switch (b[0]) {
                                        case 1:
                                            direction(1);
                                            break;
                                        case 2: // left
                                            direction(2);
                                            break;
                                        case 3: // up
                                            direction(3);
                                            break;
                                        case 4: // down
                                            direction(4);
                                            break;
                                        case 5: // shoot

                                            w.doAction(World.A_SHOOT);
                                            break;
                                        case 6:

                                            w.doAction(World.A_SHOOT);
                                            break;
                                        case 7:

                                            w.doAction(World.A_SHOOT);
                                            break;
                                        case 8:

                                            w.doAction(World.A_SHOOT);
                                            break;
                                        default: // random
                                            break;
                                    }

                                }

                    } else if (pa != 1) {
                        minvalue = 100;
                        pb = 0;
                        t = 1;
                        pathbreeze(map, a, transformX, transformY, -1, 0);

                    }
                return;
            } else {

                int count = 0, x1 = 0, y1 = 0;
                int dir = 0;

                int[] a = new int[50];
                if (map[transformX][transformY] != -4 && map[transformX][transformY] != -5
                        && map[transformX][transformY] != -7 && map[transformX][transformY] != -2)
                    map[transformX][transformY] = 1;

                if (transformX - 1 >= 0 && map[transformX - 1][transformY] != -4
                        && map[transformX - 1][transformY] != -5 && map[transformX - 1][transformY] != -7)
                    map[transformX - 1][transformY] = 1;
                if (transformY + 1 <= 3 && map[transformX][transformY + 1] != -4
                        && map[transformX][transformY + 1] != -5 && map[transformX][transformY + 1] != -7)
                    map[transformX][transformY + 1] = 1;
                if (transformX + 1 <= 3 && map[transformX + 1][transformY] != -4
                        && map[transformX + 1][transformY] != -5 && map[transformX + 1][transformY] != -7)
                    map[transformX + 1][transformY] = 1;
                if (transformY - 1 >= 0 && map[transformX][transformY - 1] != -4
                        && map[transformX][transformY - 1] != -5 && map[transformX][transformY - 1] != -7)
                    map[transformX][transformY - 1] = 1;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == -4) {
                            if ((i - 1 <= 3 && i - 1 >= 0))
                                if (map[i - 1][j] != 1 && (i - 1 <= 3 && i - 1 >= 0) && map[i - 1][j] != 3
                                        && map[i - 1][j] != -4 && map[i - 1][j] != -3 && map[i - 1][j] != -5) {
                                    count++;

                                    x1 = i - 1;
                                    y1 = j;
                                }
                            if ((i + 1 <= 3 && i + 1 >= 0))
                                if (map[i + 1][j] != 1 && (i + 1 <= 3 && i + 1 >= 0) && map[i + 1][j] != 3
                                        && map[i + 1][j] != -3 && map[i + 1][j] != -4 && map[i + 1][j] != -5) {
                                    count++;

                                    x1 = i + 1;
                                    y1 = j;
                                }
                            if ((j + 1 <= 3 && j + 1 >= 0))
                                if (map[i][j + 1] != 1 && (j + 1 <= 3 && j + 1 >= 0) && map[i][j + 1] != 3
                                        && map[i][j + 1] != -4 && map[i][j + 1] != -3 && map[i][j + 1] != -5) {
                                    count++;

                                    x1 = i;
                                    y1 = j + 1;
                                }
                            if ((j - 1 >= 0 && j - 1 >= 0))
                                if (map[i][j - 1] != 1 && (j - 1 >= 0 && j - 1 >= 0) && map[i][j - 1] != 3
                                        && map[i][j - 1] != -4 && map[i][j - 1] != -3 && map[i][j - 1] != -5) {
                                    count++;

                                    x1 = i;
                                    y1 = j - 1;
                                }
                            if (count == 1) {
                                map[x1][y1] = -2;
                                map[i][j] = 1;
                            }
                        }
                        count = 0;
                    }
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (map[i][j] == -5) {
                            if ((i - 1 <= 3 && i - 1 >= 0))
                                if (map[i - 1][j] != 1 && (i - 1 <= 3 && i - 1 >= 0) && map[i - 1][j] % 2 != 0
                                        && map[i - 1][j] != -4) {
                                    count++;

                                    x1 = i - 1;
                                    y1 = j;
                                }
                            if ((i + 1 <= 3 && i + 1 >= 0))
                                if (map[i + 1][j] != 1 && (i + 1 <= 3 && i + 1 >= 0) && map[i + 1][j] % 2 != 0
                                        && map[i + 1][j] != -4) {
                                    count++;

                                    x1 = i + 1;
                                    y1 = j;
                                }
                            if ((j + 1 <= 3 && j + 1 >= 0))
                                if (map[i][j + 1] != 1 && (j + 1 <= 3 && j + 1 >= 0) && map[i][j + 1] % 2 != 0
                                        && map[i][j + 1] != -4) {
                                    count++;

                                    x1 = i;
                                    y1 = j + 1;
                                }
                            if ((j - 1 <= 3 && j - 1 >= 0))
                                if (map[i][j - 1] != 1 && (j - 1 <= 3 && j - 1 >= 0) && map[i][j - 1] % 2 != 0
                                        && map[i][j - 1] != -4) {
                                    count++;

                                    x1 = i;
                                    y1 = j - 1;
                                }
                            if (count == 1) {
                                wumpus = 1;
                                map[x1][y1] = -3;

                            }

                        }
                        count = 0;
                    }
                }

                pa = 0;
                count = 0;
                if (transformY + 1 <= 3)
                    if (!w.isVisited(cX + 1, cY) && map[transformX][transformY + 1] == 1) {
                        dir = 1;
                        direction(1);
                    }
                if (dir != 1)
                    if (transformY - 1 >= 0)
                        if ((!w.isVisited(cX - 1, cY)) && map[transformX][transformY - 1] == 1) {
                            dir = 1;
                            direction(2);
                        }
                if (dir != 1)
                    if (transformX - 1 >= 0)
                        if (!w.isVisited(cX, cY + 1) && map[transformX - 1][transformY] == 1) {
                            dir = 1;
                            direction(3);
                        }

                if (dir != 1)
                    if (transformX + 1 <= 3)
                        if (!w.isVisited(cX, cY - 1) && map[transformX + 1][transformY] == 1) {
                            dir = 1;
                            direction(4);
                        }

                if (dir != 1) {
                    pa = 0;
                    path(map, a, transformX, transformY, -1, 0);
                }

                if (dir != 1)
                    if (pa != 1)
                        for (int i = 0; i < 4; i++)
                            for (int j = 0; j < 4; j++)
                                if (!w.isVisited(i + 1, j + 1))
                                    count++;

                if (dir != 1 && pa == 0)
                    if (count <= 2) {
                        for (int i = 0; i < 4; i++)
                            for (int j = 0; j < 4; j++)
                                if (!w.isVisited(i + 1, j + 1)) {
                                    minscore = 100;
                                    pathDestination(map, a, transformX, transformY, -1, 0, 0, i, j);

                                    switch (b[0]) {
                                        case 1:
                                            direction(1);
                                            break;
                                        case 2: // left
                                            direction(2);
                                            break;
                                        case 3: // up
                                            direction(3);
                                            break;
                                        case 4: // down
                                            direction(4);
                                            break;
                                    }

                                }

                    } else if (pa != 1) {
                        minvalue = 100;
                        pb = 0;
                        t = 1;
                        pathbreeze(map, a, transformX, transformY, -1, 0);

                    }

            }
        }

    }
    public int decideRandomMove() {
        return (int) (Math.random() * 4);
    }

}
