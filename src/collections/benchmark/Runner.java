package collections.benchmark;

import oo.hide.Timer;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class Runner {

    @Test
    public void benchmarkDifferentImplementations() {

        Set<Integer> set1 = new MySet();
        Set<Integer> set2 = new TreeSet<>();
        Set<Integer> set3 = new HashSet<>();

        Timer timer = new Timer();

        for (int i = 0; i < 30000; i++) {
            set3.add(i);
        }


        System.out.println(timer.getPassedTime());
    }

}
