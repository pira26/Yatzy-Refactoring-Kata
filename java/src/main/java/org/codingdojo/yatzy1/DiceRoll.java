package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class DiceRoll {
    private final int[] DICE;
    private final int ZERO = 0;
    private final int PAIR = 2;

    public DiceRoll(int... arg) {
        this.DICE = IntStream.of(arg).toArray();
    }

    public int sum() {
        return IntStream.of(DICE).sum();
    }

    public int yatzy() {
        var YATZY = 50;
        if (isYatzy()) {
            return YATZY;
        }
        return ZERO;
    }

    public int ones() {
        return diceCountValue(1);
    }

    public int twos() {
        return diceCountValue(2) * 2;
    }

    public int threes() {
        return diceCountValue(3) * 3;
    }

    public int fours() {
        return diceCountValue(4) * 4;
    }

    public int fives() {
        return diceCountValue(5) * 5;
    }

    public int sixes() {
        return diceCountValue(6) * 6;
    }

    public int onePair() {
        List<Integer> pair = retrievePairs();
        if (!pair.isEmpty()) {
            return pair.get(0) * 2;
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
        return retrieveNOfAKindValue(3) * 3;
    }

    public int fourOfAKind() {
        return retrieveNOfAKindValue(4) * 4;
    }

    public int smallStraight() {
        var SMALL_STRAIGHT_LIST = new int[]{1, 2, 3, 4, 5};
        if (isStraight(SMALL_STRAIGHT_LIST)) {
            return sum();
        }

        return ZERO;
    }

    public int largeStraight() {
        var LARGE_STRAIGHT_LIST = new int[]{2, 3, 4, 5, 6};
        if (isStraight(LARGE_STRAIGHT_LIST)) {
            return sum();
        }
        return ZERO;
    }

    public int fullHouse() {
        int threeOfAKindValue = retrieveNOfAKindValue(3);
        boolean isNotYatzy = !isYatzy();
        boolean hasAPair = !retrievePairs().isEmpty();
        boolean hasAThreeOfAKind = threeOfAKindValue != 0;
        if (hasAPair && hasAThreeOfAKind && isNotYatzy) {
            boolean arePairAndThreeOfAKindValuesDifferent = !Objects.equals(retrievePairs().get(0), threeOfAKindValue);
            if (arePairAndThreeOfAKindValuesDifferent) {
                return sum();
            }
            return ZERO;
        }
        return ZERO;
    }

    private Map<Integer, Integer> diceCount() {
        return IntStream.of(DICE).boxed().collect(groupingBy(identity(), reducing(0, dice -> 1, Integer::sum)));
    }

    private boolean isYatzy() {
        return diceCount().values().stream().allMatch(count -> count == 5);
    }

    private int diceCountValue(int key) {
        return diceCount().getOrDefault(key, 0);
    }

    private List<Integer> retrievePairs() {
        return diceCountStream(PAIR).sorted(reverseOrder()).toList();
    }

    private int retrieveNOfAKindValue(int number) {
        return diceCountStream(number).findFirst().orElse(ZERO);
    }

    private Stream<Integer> diceCountStream(int number) {
        return diceCount().entrySet().stream().filter(entry -> entry.getValue() >= number).map(Map.Entry::getKey);
    }

    private boolean isStraight(int[] expected) {
        return Arrays.equals(IntStream.of(DICE).sorted().toArray(), expected);
    }
}