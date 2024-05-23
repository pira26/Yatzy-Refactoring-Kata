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
    private final int[] DICE;

    public DiceRoll(int... args) {
        this.DICE = IntStream.of(args).toArray();
    }

    public int sum() {
        return IntStream.of(DICE).sum();
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
        return Arrays.equals(IntStream.of(DICE).sorted().toArray(), expected);
    }

    private Map<Integer, Integer> diceCount() {
        return IntStream.of(DICE)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(dice -> 1)));
    }

    private Stream<Integer> diceCountStream(int number) {
        return diceCount().entrySet().stream().filter(entry -> entry.getValue() >= number).map(Map.Entry::getKey);
    }
}