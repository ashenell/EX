package inheritance.calc;

public class TaxFreePayCalculator extends PayCalculator {

    @Override
    protected double getTaxRate() {
        return 0.0;
    }

}
