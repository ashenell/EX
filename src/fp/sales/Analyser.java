package fp.sales;

import org.assertj.core.data.MapEntry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analyser {

    private final Repository repository;

    private final AccountingService accountingService;

    public Analyser(Repository repository,
                    AccountingService accountingService) {
        this.repository = repository;
        this.accountingService = accountingService;
    }

    public Double getTotalSales() {
        return repository.getEntries().stream()
                .mapToDouble(Entry::amount)
                .sum();
    }


    public Double getSalesByCategory(String category) {
        return repository.getEntries().stream()
                .filter(e -> e.category().equals(category))
                .mapToDouble(Entry::amount)
                .sum();
    }

    public Double getSalesBetween(LocalDate start, LocalDate end) {
        return repository.getEntries().stream()
                .filter(e -> !e.date().isBefore(start) && !e.date().isAfter(end))
                .mapToDouble(Entry::amount)
                .sum();
    }

    public String mostExpensiveItems() {
        List<String> items = repository.getEntries().stream()
                .sorted(Comparator.comparingDouble(Entry::amount).reversed())
                .limit(3)
                .sorted(Comparator.comparing(Entry::productId))
                .map(Entry::productId)
                        .collect(Collectors.toList());
        return String.join(", ", items);
    }

    public String statesWithBiggestSales() {
        Map<String, Double> states = repository.getEntries().stream()
                .collect(Collectors.groupingBy(
                        Entry::state,
                        Collectors.summingDouble(Entry::amount)
                ));

        List<String> items = states.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        return String.join(", ", items);
    }

    public String findMostProfitableItems() {
//        var test = accountingService.getProfitMargin("TEC-MA-10000822");
//        var test2 = accountingService.getProfitMargin("TEC-AC-10002167");
//        System.out.println(test);
//        System.out.println(test2);
        Map<String, Double> pordIds = repository.getEntries().stream()
                .collect(Collectors.groupingBy(
                        Entry::productId,
                        Collectors.summingDouble(Entry::amount)
                ));

        List<Map.Entry<String, Double>> product = pordIds.entrySet().stream()
                .map(e -> Map.entry(
                        e.getKey(),
                        e.getValue() * accountingService.getProfitMargin(e.getKey())
                ))
                .toList();
//        System.out.println(product);

        List<String> items = product.stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
        return String.join(", ", items);
    }

    public List<Entry> getAllRecordsPaged(int pageNumber, int pageSize) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");


        return repository.getEntries().stream()
                .sorted(
                        Comparator.comparing(Entry::date)
                                .thenComparingInt(Entry::rowNo)
                )
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .toList();
    }

    public List<String> getCategoryList() {
        // only needed for icd0019app

        return List.of();
    }

    public int getRecordCount() {
        // only needed for icd0019app

        return 0;
    }

}
