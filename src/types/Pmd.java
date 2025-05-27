package types;
import java.util.logging.Logger;
import java.util.logging.Level;

/*
@author Rauno Vaher
 */

public class Pmd {
    private static final Logger LOGGER = Logger.getLogger(Pmd.class.getName());

    public static void main(String[] args) {
        final boolean[][] matrix = getSampleMatrix();

        printMatrix(matrix);
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("Do something 1: " + containsTrueCell(matrix));
            LOGGER.info("Do something 2: " + findFirstTrueCell(matrix));
            LOGGER.info("Do simething 3: " + countTrueRow(matrix));
        }
    }




    private static void printMatrix(final boolean[][] sampleMatrix) {
        if (LOGGER.isLoggable(Level.INFO)){
            for (final boolean[] row : sampleMatrix) {
                StringBuilder test = new StringBuilder();
                for (final boolean element : row) {
                    test.append(element ? 'X' : 'O');
                }
                LOGGER.info(test.toString());
            }

        }

    }

    // intentionally bad code
    /*
    Some method
    @param takes one argument in matrix
    @return boolean result if condition is meet.
     */
    public static boolean containsTrueCell(final boolean[][] matrix) {
        boolean result = false;
        for (final boolean[] row : matrix) {
            for (final boolean cell : row) {
                if (cell) {
                    result = true;
                }
            }
        }
        return result;
    }

    // intentionally bad code
    // intentionally bad code
    /*
    Some method
    @param takes one argument in matrix
    @return integer type default value is set to -1
     */
    public static int findFirstTrueCell(final boolean[][] matrix) {
        int counter = -1;
        for (final boolean[] row : matrix) {
            for (final boolean cell : row) {
                if (cell) {
                    counter = 1;
                }
                counter++;
            }
        }
        return counter;
    }

    // intentionally bad code
    public static int countTrueRow(final boolean[][] matrix) {
        int testCount = 0;
        for (final boolean[] row : matrix) {
            final int count = avoidingNestedLoopExample(row);
            if (count > 0) {
                testCount =  count;
            }
        }
        return testCount;
    }

    private static int avoidingNestedLoopExample(final boolean[] row) {
        int count = 0;
        for (final boolean cell : row) {
            if (cell) {
                count++;
            }
        }
        return count;
    }

    private static boolean[][] getSampleMatrix() {
        final boolean[][] matrix = new boolean[3][3];

        matrix[2][1] = true;
        matrix[2][2] = true;

        return matrix;
    }

}
