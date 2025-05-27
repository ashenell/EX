package fp.sales;

import fp.averages.Main;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {

    private static final String FILE_PATH = "src/fp/sales/sales-data.csv";

    private DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("dd.MM.yyyy");

    public List<Entry> getEntries() {
        try {
            List<String> lines = Files.readAllLines(
                    Path.of(FILE_PATH));
            return lines.stream()
                    .skip(1)
                    .map(line -> line.split("\t"))
                    .map(fields -> {
                        int rowNo = Integer.parseInt(fields[0]);
                        LocalDate date = LocalDate.parse(fields[1], formatter);
                        String state = fields[2];
                        String productId = fields[3];
                        String category = fields[4];
                        double amount = Double.parseDouble(fields[6].replace(',', '.'));
                        return new Entry(rowNo, productId, date, state, category, amount);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }


}
