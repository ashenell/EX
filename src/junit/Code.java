package junit;

public class Code {

    public static boolean isSpecial(int candidate) {
        return candidate % 11 <= 3;
    }

    public static int longestStreak(String inputString) {
        if (inputString.length() <= 1) {
            return inputString.length();
        }

        int counter = 1;
        int result = 1;
        Character previous = null;

        for (Character current : inputString.toCharArray()) {
            if (current.equals(previous)) {
                counter++;
            } else {
                counter = 1;
            }

            previous = current;

            if (counter > result) {
                result = counter;
            }
        }
        return result;
    }

    public static Character mode(String inputString) {
        if (inputString == null || inputString.length() == 0) {
            return null;
        }

        int modeCount = 0;
        Character mode = 0;

        for (char currentChar : inputString.toCharArray()) {
            int count = getCharacterCount(inputString, currentChar);

            if (count > modeCount) {
                modeCount = count;
                mode = currentChar;
            }
        }
        return mode;

    }

    public static int getCharacterCount(String inputString, char needle) {
        int count = 0;
        for (char current : inputString.toCharArray()) {
            if (needle == current) {
                count++;
            }
        }
        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        int[] temp = new int[integers.length];
        int count = 0;

        for (int value : integers) {
            if (!contains(temp, value, count)) {
                temp[count] = value;
                count++;
            }
        }

        return removeDuplicateElements(count, temp);
    }

    public static int[] removeDuplicateElements(int length, int[] integers) {
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = integers[i];
        }
        return result;
    }

    public static boolean contains(int[] array, int value, int validCount) {
        for (int i = 0; i < validCount; i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        int[] filtered = removeDuplicates(integers);
        int sum = 0;
        for (int value : filtered) {
            sum += value;
        }
        return sum;
    }

}
