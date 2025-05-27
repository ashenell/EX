package oo.hide;

import java.util.Objects;

public class Point {

    public final int x;
    public final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point[x=%s, y=%s]".formatted(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point other)) {
            return false;
        }

        return Objects.equals(x, other.x) && Objects.equals(y, other.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
