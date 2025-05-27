package inheritance.analyser;

import inheritance.analyser.SalesRecord;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


public abstract sealed class AbstractSalesAnalyser permits
        EstonianTaxSalesAnalyser,
        TaxFreeSalesAnalyser,
        FinnishSalesAnalyser {


    protected abstract double getVatRate(LocalDate date);

    protected final List<SalesRecord> records;


    protected AbstractSalesAnalyser(List<SalesRecord> records) {
        this.records = records;
    }

    public Double getTotalSales() {
        double totalSales = 0.0;
        for (SalesRecord record : records) {
            double vatRate = getVatRate(record.date());
            double netPrice = record.productPrice() / (1 + vatRate);
            totalSales += netPrice * record.itemsSold();
        }
        System.out.println("Total sales is " + totalSales);

        DecimalFormat df = new DecimalFormat("#.##");

        return Double.parseDouble(df.format(totalSales));
    }

    public Double getTotalSalesByProductId(String id) {
        double totalSales = 0.0;
        for (SalesRecord record : records) {
            if (record.productId().equals(id)) {
                double vatRate = getVatRate(record.date());
                double netPrice = record.productPrice() / (1 + vatRate);
                totalSales += netPrice * record.itemsSold();
            }
        }
        return totalSales;
    }


    public List getTop3PopularItems(){
        Map<String, Integer> topSales = salesTotalByProductId();

        List<String> top3Items = topSales.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue()
                        .compareTo(e1.getValue()))
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println(top3Items);
        return top3Items;

    }
    public double getLargestTotalSalesAmountForSingleItem(){
        Map<String, Double> salesAmountByProduct = new HashMap<>();
        for (SalesRecord record : records) {
            double vatRate = getVatRate(record.date());
            double netPrice = record.productPrice() / (1 + vatRate);
            double salesAmount = netPrice * record.itemsSold();
            salesAmountByProduct.merge(record.productId(), salesAmount, Double::sum);
        }
        return salesAmountByProduct.values().stream()
                .max(Double::compare).orElse(0.0);
    }

    public Map<String, Integer> salesTotalByProductId(){
        Map<String, Integer> topSales = new HashMap<>();
        for (SalesRecord record : records) {
            String productId = record.productId();
            int currentCount = topSales.getOrDefault(productId, 0);
            topSales.put(productId, currentCount + record.itemsSold());
        }
        System.out.println(topSales);
        return topSales;
    }
}
