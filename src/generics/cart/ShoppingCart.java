package generics.cart;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart<T extends CartItem> {

    private final Map<String, Double> couponToDiscount = Map.of(
            "Sale5", 5.0,
            "Sale8", 8.0,
            "Sale10", 10.0);

    private List<T> items = new ArrayList<>();
    private ArrayList<Double> percentage = new ArrayList();
    private ArrayList<Double> couponActive = new ArrayList();


    public void add(T item) {
        items.add(item);
    }

    public void removeById(String id) {
        items.removeIf(item -> item.id().equals(id));
    }

    public Double getTotal() {
        List<CartEntry> contents = getContents();
        System.out.println(contents);
        double total = 0.0;
        for (CartEntry entry : contents) {
//            System.out.println(entry);
            total += entry.item.price() * entry.quantity;
        }
        for (Double percent : percentage) {
            total *= (1 - percent / 100.0);
            System.out.println(total);
        }
        return total;

    }


    public List<CartEntry> getContents() {
        List<CartEntry> contents = new ArrayList<>();
        for (T item : items) {
            boolean itemFound = false;
            for (CartEntry entry : contents) {
                if (entry.item.id().equals(item.id())) {
                    entry.quantity++;
                    itemFound = true;
                    break;
                }
            }
            if (!itemFound) {
                contents.add(new CartEntry(item, 1));
            }

        }
        return contents;
    }

    public void increaseQuantity(String id) {
        for (T item : items) {
            if (item.id().equals(id)) {
                items.add(item);
                return;
            }
        }


    }

    public void applyDiscountPercentage(Double discount) {
            percentage.add(discount);
    }

    public boolean applyCoupon(String coupon) {
        System.out.println(couponToDiscount.get(coupon));
        if (couponToDiscount.get(coupon) == null) {
            return false;
        }
        Double discount = couponToDiscount.get(coupon);

        if (couponActive.isEmpty()) {
            couponActive.add(discount);
            System.out.println(couponActive);
        } else {
            couponActive.removeLast();
            couponActive.add(discount);
        }

        return true;
    }

    public Double getTotalDiscount() {
        Double total = 1.0;
        for (Double discount : percentage) {
            total *= (1 - discount / 100.0);
        }
        for (Double discount : couponActive) {
            total *= (1 - discount / 100.0);
        }
        System.out.println(percentage);
        System.out.println(couponActive);
        return (1 - total) * 100;
    }

    public void removeLastDiscount() {
        percentage.removeLast();
        //System.out.println(getTotal());
    }

    public void addAll(List<T> items) {
        for (T item : items){
            add(item);
        }
    }

    @Override
    public String toString() {
        return getContents().stream()
                .map(CartEntry::toString)
                .collect(Collectors.joining(", "));
    }

}
