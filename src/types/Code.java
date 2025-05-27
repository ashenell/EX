package types;

import java.util.Arrays;
import java.util.Random;

public class Code {

    public static void main(String[] args) {

        int[] numbers = {1, 3, -2, 9};
        String randomChars = "abccbc";
        String digits = "aa22aab";

        System.out.println(sum(numbers)); // 11
        System.out.println(average(numbers));
        System.out.println(minimumElement(numbers));
        System.out.println(asString(numbers));
        System.out.println(mode(randomChars));
        System.out.println(squareDigits(digits));
        System.out.println(isIsolated(0, 9));
        System.out.println(isolatedSquareCount());
        System.out.println(isolatedSquareCount());
    }

    public static int sum(int[] numbers) {
        Integer sum = 0;

        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    public static double average(int[] numbers) {
        Double average = 0.0;
        for (int num : numbers) {
            average += num;
        }
        average /= numbers.length;
        return average;
    }

    public static Integer minimumElement(int[] integers) {
        if (integers.length == 0){
            return null;
        }

        int length = integers.length;
        int res = integers[0];


        for (int i = 0; i < length; i++) {
            res = Math.min(res, integers[i]);
        }


        return res;
    }

    public static String asString(int[] elements) {
        String res = "";

        for (int i = 0; i < elements.length; i++) {
            if (i == elements.length - 1) {
                res += elements[i];
            }
            else {
                res += elements[i] + ", ";
            }
        }

        return res;
    }

    public static Character mode(String input) {
        if (input.length() == 0) {
            return null;
        }
        Character character = null;
        for (int i = 0; i < input.length(); i++) {
            char singleChar = input.charAt(i);
            if (input.indexOf(singleChar, i + 1) != -1) {
                character = singleChar;
            }
        }
        return character;
    }

    public static String squareDigits(String s) {
        char[] chars = s.toCharArray();
        StringBuilder result = new StringBuilder();


        for (char c : chars) {


            if (Character.isDigit(c)) {
                int isDigit = Integer.parseInt(Character.toString(c));
                int calcSquare = isDigit * isDigit;
                result.append(calcSquare);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static int isolatedSquareCount() {
        boolean[][] matrix = getSampleMatrix();

        int isolatedCount = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (isIsolated(i, j)) {
                    isolatedCount++;
                }
            }
        }

        return isolatedCount;
    }

    public static boolean isIsolated(int row, int col) {
        boolean[][] matrix = getSampleMatrix();

        printMatrix(matrix);

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) {
                    continue;
                }
                if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) {
                    continue;
                }
                if (matrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printMatrix(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleMatrix() {
        boolean[][] matrix = new boolean[10][10];

        Random r = new Random(5);
        for (boolean[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                row[i] = r.nextInt(5) < 2;
            }
        }

        return matrix;
    }
}
