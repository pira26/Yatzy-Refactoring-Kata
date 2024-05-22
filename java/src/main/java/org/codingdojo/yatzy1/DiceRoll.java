package org.codingdojo.yatzy1;

import java.util.List;

import static java.util.List.of;

public class DiceRoll {

    private final List<Integer> dices;

    public DiceRoll(int d1, int d2, int d3, int d4, int d5) {
        this.dices = of(d1, d2, d3, d4, d5);
    }

    public int sum() {
        return dices.stream().mapToInt(Integer::intValue).sum();
    }
}
