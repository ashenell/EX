package collections.simulator;

import inheritance.constructor.Car;

import java.util.*;
import java.util.stream.Collectors;

public class Hand implements Iterable<Card>, Comparable<Hand> {

    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    public HandType getHandType() {
        Map<Card.CardValue, Long> valuesCount = cards.stream().collect(
                Collectors.groupingBy(Card::getValue, Collectors.counting()));
        long pairCount = valuesCount.values().stream().filter(count -> count == 2L).count();
        if (valuesCount.containsValue(3L) && valuesCount.containsValue(2L)) {
            return HandType.FULL_HOUSE;
        }
        else if (isStraight() && isFlush()) {
            return HandType.STRAIGHT_FLUSH;
        }
        else if (isStraight()){
            return HandType.STRAIGHT;
        }
        else if (isFlush()) {
            return HandType.FLUSH;
        }
        else if (valuesCount.containsValue(4L)) {
            return HandType.FOUR_OF_A_KIND;
        }
        else if(valuesCount.containsValue(3L)) {
            return HandType.TRIPS;
        }
        else if (pairCount == 1) {
            return HandType.ONE_PAIR;
        }
        else if (pairCount == 2) {
            return HandType.TWO_PAIRS;
        }
        return HandType.HIGH_CARD;
    }

    public boolean isFlush(){
        List<Integer> sortedValues = cards.stream().map(card -> card.getSuit().ordinal())
                .collect(Collectors.toList());
        int sum = sortedValues.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        System.out.println(sortedValues);
        int first = cards.get(0).getSuit().ordinal();
        int expectedSum = first * cards.size();
        System.out.println(expectedSum + " sum of " + sum + " cards");

        if ( sum - expectedSum == 0 ) {
            return true;
        }
        return false;
    }

    public boolean isStraight() {
        List<Integer> sortedValues = cards.stream()
                .map(card -> card.getValue().ordinal())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(cards.get(0).getValue().ordinal() == 12);

        if (sortedValues.size() != 5) {
            return false;
        }
        if (sortedValues.get(0) == 0 &&
                sortedValues.get(1) == 1 &&
                sortedValues.get(2) == 2 &&
                sortedValues.get(3) == 3 &&
                sortedValues.get(4) == 12) {
            return true;
        }

        for (int i = 1; i < sortedValues.size(); i++) {
            if (sortedValues.get(i) - sortedValues.get(i - 1) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(Card card) {
        return cards.contains(card);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public int compareTo(Hand other) {
        return 0;
    }
}
