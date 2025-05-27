package collections.cache;

import oo.hide.Timer;
import org.junit.jupiter.api.Test;

public class Runner {

    @Test
    public void calculatesFibonacciOfN() {
        Fibonacci fib = new Fibonacci();

        Timer timer = new Timer();

        fib.fib(44);

        System.out.println(timer.getPassedTime());
    }

}
