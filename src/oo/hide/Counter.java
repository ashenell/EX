package oo.hide;

public class Counter {

    private int currentValue;
    private final int step;

    public Counter(int start, int step) {
        this.currentValue = start;
        this.step = step;
    }

    public int nextValue() {
        int result = currentValue;

        currentValue += step;

        return result;
    }
}
