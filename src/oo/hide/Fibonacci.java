package oo.hide;

public class Fibonacci {
    private int nMinus2 = 0;
    private int nMinus1 = 1;
    public int nextValue() {
        int result = nMinus2;
        int next = nMinus1 + nMinus2;

        nMinus2 = nMinus1;
        nMinus1 = next;

        return result;
    }

}
