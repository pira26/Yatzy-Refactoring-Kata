package org.codingdojo.yatzy1;

import java.util.List;
import java.util.Map;

import static java.util.Collections.reverseOrder;
import static java.util.List.of;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.reducing;

public class DiceRoll {
    private final List<Integer> DICES;
    private final int ZERO = 0;
    private final int YATZY = 50;
    private final int PAIR = 2;

    public DiceRoll(int d1, int d2, int d3, int d4, int d5) {
        this.DICES = of(d1, d2, d3, d4, d5);
    }

    public int sum() {
        return DICES.stream().mapToInt(Integer::intValue).sum();
    }

    public Map<Integer, Integer> count() {
        return DICES.stream().collect(groupingBy(identity(), reducing(0, dice -> 1, Integer::sum)));
    }

    public boolean isYatzy() {
        return count().values().stream().allMatch(diceCount -> diceCount == 5);
    }

    public int yatzy() {
        if (isYatzy()) {
            return YATZY;
        }
        return ZERO;
    }

    private int getValue(int key) {
        return count().getOrDefault(key, 0);
    }

    public int ones() {
        return getValue(1);
    }

    public int twos() {
        return getValue(2) * 2;
    }

    public int threes() {
        return getValue(3) * 3;
    }

    public int fours() {
        return getValue(4) * 4;
    }

    public int fives() {
        return getValue(5) * 5;
    }

    public int sixes() {
        return getValue(6) * 6;
    }

    private List<Integer> retrievePairs() {
        return count()
            .entrySet().stream()
            .filter(entry -> entry.getValue() >= PAIR)
            .map(Map.Entry::getKey)
            .sorted(reverseOrder())
            .toList();
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
}