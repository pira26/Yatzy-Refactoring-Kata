package org.codingdojo.yatzy1;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.List.of;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class DiceRoll {
    private final List<Integer> DICE;
    private final int ZERO = 0;
    private final int YATZY = 50;
    private final int PAIR = 2;
    private final List<Integer> SMALL_STRAIGHT_LIST = of(1, 2, 3, 4, 5);
    private final int SMALL_STRAIGHT = 15;
    private final List<Integer> LARGE_STRAIGHT_LIST = of(2, 3, 4, 5, 6);
    private final int LARGE_STRAIGHT = 20;

    public DiceRoll(int d1, int d2, int d3, int d4, int d5) {
        this.DICE = of(d1, d2, d3, d4, d5);
    }

    public int sum() {
        return DICE.stream().mapToInt(Integer::intValue).sum();
    }

    public int yatzy() {
        if (isYatzy()) {
            return YATZY;
        }
        return ZERO;
    }

    public int ones() {
        return getDiceCountValue(1);
    }

    public int twos() {
        return getDiceCountValue(2) * 2;
    }

    public int threes() {
        return getDiceCountValue(3) * 3;
    }

    public int fours() {
        return getDiceCountValue(4) * 4;
    }

    public int fives() {
        return getDiceCountValue(5) * 5;
    }

    public int sixes() {
        return getDiceCountValue(6) * 6;
    }

    public int onePair() {
        List<Integer> pair = retrievePairs();
        if (!pair.isEmpty()) {
            return pair.getFirst() * 2;
        }
        return ZERO;
    }

    public int twoPair() {
        List<Integer> pairs = retrievePairs();
        if (pairs.size() >= PAIR) {
            return pairs.stream().mapToInt(pair -> pair * 2).sum();
        }
        return ZERO;
    }

    public int threeOfAKind() {
        return getNOfAKind(3) * 3;
    }

    public int fourOfAKind() {
        return getNOfAKind(4) * 4;
    }

    public int smallStraight() {
        if (Objects.equals(SMALL_STRAIGHT_LIST, sortDice())) {
            return SMALL_STRAIGHT;
        }

        return ZERO;
    }

    public int largeStraight() {
        if (Objects.equals(LARGE_STRAIGHT_LIST, sortDice())) {
            return LARGE_STRAIGHT;
        }
        return ZERO;
    }

    public int fullHouse() {
        int threeOfAKindValue = getNOfAKind(3);
        boolean isNotYatzy = !isYatzy();
        boolean hasAPair = !retrievePairs().isEmpty();
        boolean hasAThreeOfAKind = threeOfAKindValue != 0;
        if (hasAPair && hasAThreeOfAKind && isNotYatzy) {
            boolean isPairAndThreeOfAKindDifferent = !Objects.equals(retrievePairs().get(0), threeOfAKindValue);
            if (isPairAndThreeOfAKindDifferent) {
                return sum();
            }
            return ZERO;
        }
        return ZERO;
    }

    private Map<Integer, Integer> diceCount() {
        return DICE.stream().collect(groupingBy(identity(), reducing(0, dice -> 1, Integer::sum)));
    }

    private boolean isYatzy() {
        return diceCount().values().stream().allMatch(count -> count == 5);
    }

    private int getDiceCountValue(int key) {
        return diceCount().getOrDefault(key, 0);
    }

    private Stream<Integer> getDiceCountStream(int number) {
        return diceCount().entrySet().stream().filter(entry -> entry.getValue() >= number).map(Map.Entry::getKey);
    }

    private List<Integer> retrievePairs() {
        return getDiceCountStream(PAIR).sorted(reverseOrder()).toList();
    }

    private int getNOfAKind(int number) {
        return getDiceCountStream(number).findFirst().orElse(ZERO);
    }

    private List<Integer> sortDice() {
        return DICE.stream().sorted().toList();
    }
}