package oo.hide;

public class PointSet {
    private Point[] elements;
    private int size;

    public PointSet(int capacity) {
        elements = new Point[capacity];
        size = 0;
    }

    public PointSet() {
        this(10);
    }

    public void add(Point point) {
        if (point == null) {
            if (contains(null)) {
                return;
            }
        } else if (contains(point)) {
            return;
        }
        if (size == elements.length) {
            Point[] temp = new Point[elements.length * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = elements[i];
            }
            elements = temp;
        }
        elements[size] = point;
        size++;
    }


    public int size() {
        return size;
    }

    public boolean contains(Point point) {
        for (int i = 0; i < size; i++) {
            if (point == null) {
                if (elements[i] == null) {
                    return true;
                }
            } else {
                if (elements[i] != null && elements[i].equals(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    public PointSet subtract(PointSet other) {
        PointSet result = new PointSet();

        for (int i = 0 ; i < size ; i++) {
            if (!other.contains(elements[i])) {
                result.add(elements[i]);
            }
        }

        return result;
    }

    public PointSet intersect(PointSet other) {
        PointSet result = new PointSet();
        for (int i = 0 ; i < size ; i++) {
            if (other.contains(elements[i])) {
                result.add(elements[i]);
            }
        }
        return result;
    }

    public void remove(Point point) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (point == null) {
                if (elements[i] == null) {
                    index = i;
                    break;
                }
            } else {
                if (elements[i] != null && elements[i].equals(point)) {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) {
            return;
        }
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
    }

    @Override
    public int hashCode() {
        return 0; // no need to change this
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                returnString += ", ";
            }
            if (elements[i] == null) {
                returnString += "null";
            } else {
                returnString += "(" + elements[i].x + ", " + elements[i].y + ")";
            }
        }
        return returnString;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PointSet other)) {
            return false;
        }
        if (this.size != other.size()) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            if (!other.contains(this.elements[i])) {
                return false;
            }
        }
        return true;
    }
}
