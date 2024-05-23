package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DiceRoll {
    private final int[] dice;

    public DiceRoll(int... args) {
        if (IntStream.of(args).count() != 5) {
            throw new IllegalArgumentException("Invalid dice roll size");
        }
        if (Arrays.stream(args).anyMatch(value->! List.of(1,2,3,4,5,6).contains(value))) {
            throw new IllegalArgumentException("Invalid dice roll value");
        }
        this.dice = IntStream.of(args).toArray();
    }

    public int sum() {
        return IntStream.of(dice).sum();
    }

    public boolean isYatzy() {
        return diceCount().values().stream().allMatch(count -> count == 5);
    }

    public int diceCountValue(int key) {
        return diceCount().getOrDefault(key, 0);
    }

    public List<Integer> retrievePairs() {
        return diceCountStream(2).sorted(Collections.reverseOrder()).toList();
    }

    public int retrieveNOfAKindValue(int number) {
        return diceCountStream(number).findFirst().orElse(0);
    }

    public boolean isStraight(int[] expected) {
        return Arrays.equals(IntStream.of(dice).sorted().toArray(), expected);
    }

    private Stream<Integer> diceCountStream(int number) {
        return diceCount().entrySet().stream().filter(entry -> entry.getValue() >= number).map(Map.Entry::getKey);
    }

    private Map<Integer, Integer> diceCount() {
        return IntStream.of(dice)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(dice -> 1)));
    }
}