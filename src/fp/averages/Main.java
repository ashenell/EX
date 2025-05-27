package fp.averages;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;


public class Main {

    public static void main(String[] args) throws IOException {
        List<List<Integer>> rows = getData();
//        System.out.println(rows.getFirst());

        OptionalDouble average = rows.stream()
                .flatMap(row -> row.stream())
                .mapToInt(i -> i)
                .average();
        System.out.println(average);
        // find average of all the numbers

    }

    private static List<List<Integer>> getData() throws IOException {
        List<String> lines = Files.readAllLines(
                Path.of("src/fp/averages/data.csv"));

        return lines.stream()
                .map(line -> line.split(", "))
                .map(List::of)
                .map(Main::toNumberList)
                .toList();
    }

    private static List<Integer> toNumberList(List<String> numbers) {
        return numbers.stream().map(Integer::parseInt).toList();
    }

}
