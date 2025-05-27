package generics.pair;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Runner {

    @Test
    public void pairExample() {
        Pair<String, Integer> pair = new Pair<>("key", 1);

        String key = pair.getFirst();
        Integer value = pair.getSecond();

        assertThat(key).isEqualTo("key");
        assertThat(value).isEqualTo(1);
    }
    @Test
    public void pairExample1() {
        Pair<Integer, Double> pair = new Pair<>(1, 1.0);

        Integer key = pair.getFirst();
        Double value = pair.getSecond();

        assertThat(key).isEqualTo(1);
        //assertThat(value).isEqualTo(1.0);
    }
}
