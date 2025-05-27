package generics.stack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StackTest {

    @Test
    public void elementsArePoppedInReverseOrder() {
        Stack<Integer> stack = new Stack();

        stack.push(1);
        stack.push(2);

        Integer first = stack.pop();
        Integer second = stack.pop();

        assertThat(first).isEqualTo(2);
        assertThat(second).isEqualTo(1);
    }
    @Test
    public void elementsArePoppedInReverseOrderD() {
        Stack<Double> stack = new Stack();

        stack.push(1.2);
        stack.push(2.3);

        Double first = stack.pop();
        Double second = stack.pop();

        assertThat(first).isEqualTo(2.3);
        assertThat(second).isEqualTo(1.2);
    }

}
