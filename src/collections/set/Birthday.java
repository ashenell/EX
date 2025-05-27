package collections.set;

import org.junit.jupiter.api.Test;

import java.util.*;


public class Birthday {

    @Test
    public void runCode() {


        int size = 0;

        for (int i = 0; i < 1000; i++) {
            size += getGroupSize();
        }
        System.out.println(size / 1000);

    }

    public static int getGroupSize() {
        Random r = new Random();

        int randomDayOfYear = r.nextInt(365);

        Set<Integer> covered = new HashSet<>();

        while (!covered.contains(randomDayOfYear)) {

            covered.add(randomDayOfYear);
            randomDayOfYear = r.nextInt(365);
        }
        return covered.size();
    }

}
