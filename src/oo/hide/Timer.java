package oo.hide;

public class Timer {

    private long start;
    public Timer() {
        start = System.currentTimeMillis();
    }

    public String getPassedTime() {
        long passedMills = System.currentTimeMillis() - start;

        return Double.valueOf(passedMills) / 1000 + " sec";
    }
}
