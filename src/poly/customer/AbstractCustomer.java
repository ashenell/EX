package poly.customer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;


public abstract sealed class AbstractCustomer
        implements Comparable<AbstractCustomer>
        permits GoldCustomer, RegularCustomer {

    protected String id;
    protected String name;
    protected int bonusPoints;
    protected LocalDate lastOrderDate;

    public AbstractCustomer(String id,
                            String name,
                            int bonusPoints,
                            LocalDate lastOrderDate) {
        this.id = id;
        this.name = name;
        this.bonusPoints = bonusPoints;
        this.lastOrderDate = lastOrderDate;
    }


    public void collectBonusPointsFrom(Order order) {
        if (order == null) {
            return;
        }

        int orderValue = (int) order.total();
        if (orderValue < 100) {
            return;
        }

        int pointsToAdd;
        if (this instanceof GoldCustomer) {
            pointsToAdd = orderValue + orderValue / 2;
        } else {
            pointsToAdd = orderValue;
            boolean isFrequent =
                    lastOrderDate != null
                            && ChronoUnit.DAYS.between(lastOrderDate, order.date()) < 30;
            if (isFrequent) {
                pointsToAdd += 100;
            }
        }

        bonusPoints += pointsToAdd;
        lastOrderDate = order.date();
    }


    public abstract String asString();



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBonusPoints() {
        return bonusPoints;
    }

    public LocalDate getLastOrderDate() {
        return lastOrderDate;
    }



    @Override
    public int compareTo(AbstractCustomer other) {
        return Integer.compare(this.bonusPoints, other.bonusPoints);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractCustomer that)) {
            return false;
        }
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && bonusPoints == that.bonusPoints;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bonusPoints);
    }

    @Override
    public String toString() {
        return asString();
    }
}
