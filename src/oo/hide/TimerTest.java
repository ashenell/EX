package oo.hide;

import org.junit.jupiter.api.Test;

public class TimerTest {

    @Test
    public void timerExample() {

        Timer timer = new Timer();

        for (int i = 0; i < 1E9; i++) {
        }

        System.out.println(timer.getPassedTime());
    }



}
