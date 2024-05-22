package org.codingdojo.yatzy1;

import java.util.function.Function;

public enum Yatzy1Category {
    CHANCE(Yatzy1::chance),
    YATZY(Yatzy1::yatzy)
    ;

    private final Function<DiceRoll, Integer> scoringFunction;

    Yatzy1Category(Function<DiceRoll, Integer> scoringFunction) {
        this.scoringFunction = scoringFunction;
    }

    public int score(DiceRoll diceRoll) {
        return this.scoringFunction.apply(diceRoll);
    }
}
