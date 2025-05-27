package poly.customer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class RegularCustomer extends AbstractCustomer {

    public RegularCustomer(String id,
                           String name,
                           int bonusPoints,
                           LocalDate lastOrderDate) {
        super(id, name, bonusPoints, lastOrderDate);
    }

    public RegularCustomer(String id, String name, int bonusPoints) {
        this(id, name, bonusPoints, null);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order == null || order.total() < 100) {
            return;
        }

        double factor = 1.0;
        boolean isFrequent = lastOrderDate != null
                && ChronoUnit.DAYS.between(lastOrderDate, order.date()) < 30;
        if (isFrequent) {
            factor = 1.5;
        }

        bonusPoints += Math.round(order.total() * factor);
        lastOrderDate = order.date();
    }

    @Override
    public String asString() {
        return String.join(";", "REGULAR", id, name,
                String.valueOf(bonusPoints),
                lastOrderDate == null ? "" : lastOrderDate.toString());
    }
}
