package gol;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private Set<Point> liveCells = new HashSet<>();

    public void markAlive(int x, int y) {
        Point coord = new Point(x, y);
        liveCells.add(coord);
    }

    public boolean isAlive(int x, int y) {
        return liveCells.contains(new Point(x, y));
    }

    public void toggle(int x, int y) {
        Point coord = new Point(x, y);
        if (isAlive(x, y)) {
            liveCells.remove(coord);
        } else {
            liveCells.add(coord);
        }
    }

    public Integer getNeighbourCount(int x, int y) {
        Integer neighbourCount = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int newX = x + dx;
                int newY = y + dy;
                if ( isAlive(newX, newY)) {
                    neighbourCount++;
                }
            }
        }



        return neighbourCount;
    }

    public void nextFrame() {
        Set<Point> newLive = new HashSet<>();

        for (Point c : liveCells) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) {
                        continue;
                    }
                    int neighbourX = c.x() + dx;
                    int neighbourY = c.y() + dy;

                    boolean wasAlive = isAlive(neighbourX, neighbourY);
                    int neighbourCount = getNeighbourCount(neighbourX, neighbourY);

                    if (nextState(wasAlive, neighbourCount)) {
                        newLive.add(new Point(neighbourX, neighbourY));
                    }
                }
            }
        }

        liveCells = newLive;
    }

    public void clear() {
        liveCells.clear();
    }

    public boolean nextState(boolean isLiving, int neighborCount) {
        if (isLiving) {
            return neighborCount == 2 || neighborCount == 3;
        } else {
            return neighborCount == 3;
        }
    }

}
