package exceptions.basic;

import java.io.IOException;

public class Code {

    public String readDataFrom(FakeFile file) {
        try {
            file.open();
            return file.read();
        } catch (IOException e) {
            return "some default value";
        } finally {
            file.close();
        }

    }

    public static Integer minimumElement(int[] integers) {
        if (integers == null || integers.length == 0) {
            throw new IllegalArgumentException();
        }

        int minimumElement = integers[0];

        for (int current : integers) {
            if (current < minimumElement) {
                minimumElement = current;
            }
        }

        return minimumElement;
    }

    public static boolean containsSingleLetters(String s) {
        if (s == null || s.length() == 0) {
            return false;
        } else if (s.length() == 1) {
            return true;
        }
        int index = 0;


        while (index < s.length() - 1) {
            if (s.charAt(index) == s.charAt(index + 1)) {
                return false;
            }

            index++;
        }

        return true;
    }
}
