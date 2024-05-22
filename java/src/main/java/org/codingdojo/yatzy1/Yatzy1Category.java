package org.codingdojo.yatzy1;

import java.util.function.Function;

public enum Yatzy1Category {
    CHANCE(Yatzy1::chance),
    YATZY(Yatzy1::yatzy),
    ONES(Yatzy1::ones),
    TWOS(Yatzy1::twos),
    THREES(Yatzy1::threes),
    FOURS(Yatzy1::fours),
    FIVES(Yatzy1::fives),
    SIXES(Yatzy1::sixes),
    ONE_PAIR(Yatzy1::onePair),
    TWO_PAIR(Yatzy1::twoPair),
    THREE_OF_A_KIND(Yatzy1::threeOfAKind),
    FOUR_OF_A_KIND(Yatzy1::fourOfAKind),
    ;

    private final Function<DiceRoll, Integer> scoringFunction;

    Yatzy1Category(Function<DiceRoll, Integer> scoringFunction) {
        this.scoringFunction = scoringFunction;
    }

    public int score(DiceRoll diceRoll) {
        return this.scoringFunction.apply(diceRoll);
    }
}
