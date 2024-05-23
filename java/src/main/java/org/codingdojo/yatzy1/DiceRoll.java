package org.codingdojo.yatzy1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DiceRoll {
    private final int[] DICE;

    public DiceRoll(int... args) {
        this.DICE = IntStream.of(args).toArray();
    }

    public int chance() {
        return sum();
    }

    public int yatzy() {
        var YATZY = 50;
        if (isYatzy()) {
            return YATZY;
        }
        return 0;
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
        return 0;
    }

    public int twoPair() {
        List<Integer> pairs = retrievePairs();
        if (pairs.size() >= 2) {
            return pairs.stream().mapToInt(pair -> pair * 2).sum();
        }
        return 0;
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

        return 0;
    }

    public int largeStraight() {
        var LARGE_STRAIGHT_LIST = new int[]{2, 3, 4, 5, 6};
        if (isStraight(LARGE_STRAIGHT_LIST)) {
            return sum();
        }
        return 0;
    }

    public int fullHouse() {
        var threeOfAKindValue = retrieveNOfAKindValue(3);
        var isNotYatzy = !isYatzy();
        var hasAPair = !retrievePairs().isEmpty();
        var hasAThreeOfAKind = threeOfAKindValue != 0;
        if (hasAPair && hasAThreeOfAKind && isNotYatzy) {
            var arePairAndThreeOfAKindValuesDifferent = !Objects.equals(retrievePairs().get(0), threeOfAKindValue);
            if (arePairAndThreeOfAKindValuesDifferent) {
                return sum();
            }
            return 0;
        }
        return 0;
    }

    private int sum() {
        return IntStream.of(DICE).sum();
    }

    private boolean isYatzy() {
        return diceCount().values().stream().allMatch(count -> count == 5);
    }

    private int diceCountValue(int key) {
        return diceCount().getOrDefault(key, 0);
    }

    private List<Integer> retrievePairs() {
        return diceCountStream(2).sorted(Collections.reverseOrder()).toList();
    }

    private int retrieveNOfAKindValue(int number) {
        return diceCountStream(number).findFirst().orElse(0);
    }

    private Stream<Integer> diceCountStream(int number) {
        return diceCount().entrySet().stream().filter(entry -> entry.getValue() >= number).map(Map.Entry::getKey);
    }

    private Map<Integer, Integer> diceCount() {
        return IntStream.of(DICE)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(dice -> 1)));
    }

    private boolean isStraight(int[] expected) {
        return Arrays.equals(IntStream.of(DICE).sorted().toArray(), expected);
    }
}