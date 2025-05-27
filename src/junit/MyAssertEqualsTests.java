package junit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MyAssertEqualsTests {

    @Test
    public void testsPrimitiveEqualitySuccess() {
        myAssertEquals(1, 1);
    }

    @Test
    public void testsPrimitiveEqualityFailure() {
        assertThrows(AssertionError.class,
                () -> myAssertEquals(1, 2));
    }

    @Test
    public void testsObjectEqualitySuccess() {
        myAssertEquals(200L, 200L);
    }

    @Test
    public void testsObjectEqualityFailure() {
        assertThrows(AssertionError.class,
                () -> myAssertEquals(100L, 200L));
    }

    @Test
    public void testsArrayEqualitySuccess() {
        int[] a = {1, 2};
        int[] b = {1, 2};

        myAssertEquals(a, b);
    }

    @Test
    public void testsArrayEqualityFailure1() {
        int[] a = {1, 2};
        int[] b = {1};

        assertThrows(AssertionError.class,
                () -> myAssertEquals(a, b));
    }

    @Test
    public void testsArrayEqualityFailure2() {
        int[] a = {};
        int[] b = {1};

        assertThrows(AssertionError.class,
                () -> myAssertEquals(a, b));
    }

    @Test
    public void testsArrayEqualityFailure3() {
        int[] a = {1, 2};
        int[] b = {1, 3};

        assertThrows(AssertionError.class,
                () -> myAssertEquals(a, b));
    }



    public void myAssertEquals(int expected, int actual) {
        if (expected != actual) {
            String message = "%s is not equal to %s"
                    .formatted(actual, expected);
            throw new AssertionError(message);
        }
    }

    public void myAssertEquals(Long expected, Long actual) {
        if (!expected.equals(actual)) {
            String message = "%s is not equal to %s"
                    .formatted(actual, expected);
            throw new AssertionError(message);
        }
    }

    public void myAssertEquals(int[] expected, int[] actual) {
        String message = "%s is not equal to %s"
                .formatted(Arrays.toString(actual),
                        Arrays.toString(expected));
        if ( expected.length != actual.length) {
            throw new AssertionError(message);
        }
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) {
                throw new AssertionError(message);
            }
        }
    }
}
