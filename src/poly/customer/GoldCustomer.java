package poly.customer;

import java.time.LocalDate;

public final class GoldCustomer extends AbstractCustomer {

    public GoldCustomer(String id,
                        String name,
                        int bonusPoints,
                        LocalDate lastOrderDate) {
        super(id, name, bonusPoints, lastOrderDate);
    }

    @Override
    public void collectBonusPointsFrom(Order order) {
        if (order == null || order.total() < 100) {
            return;
        }
        bonusPoints += Math.round(order.total() * 1.5);
        lastOrderDate = order.date();
    }

    @Override
    public String asString() {
        return String.join(";", "GOLD", id, name,
                String.valueOf(bonusPoints),
                lastOrderDate == null ? "" : lastOrderDate.toString());
    }
}
