package inheritance.analyser;

import java.time.LocalDate;
import java.util.List;


public final class EstonianTaxSalesAnalyser extends AbstractSalesAnalyser {

    public EstonianTaxSalesAnalyser(List<SalesRecord> records) {
        super(records);
    }
    @Override
    protected double getVatRate(LocalDate date) {
        if (date.isBefore(LocalDate.of(2024, 1, 1))){
            return 0.20;
        } else if (date.isBefore(LocalDate.of(2025, 7, 1))){
            return 0.22;
        } else {
            return 0.24;
        }

    }

}
