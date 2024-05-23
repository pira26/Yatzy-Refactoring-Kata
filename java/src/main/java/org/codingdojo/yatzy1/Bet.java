package org.codingdojo.yatzy1;

import java.util.function.Function;

public enum Bet {
    CHANCE(DiceRoll::chance),
    YATZY(DiceRoll::yatzy),
    ONES(DiceRoll::ones),
    TWOS(DiceRoll::twos),
    THREES(DiceRoll::threes),
    FOURS(DiceRoll::fours),
    FIVES(DiceRoll::fives),
    SIXES(DiceRoll::sixes),
    ONE_PAIR(DiceRoll::onePair),
    TWO_PAIR(DiceRoll::twoPair),
    THREE_OF_A_KIND(DiceRoll::threeOfAKind),
    FOUR_OF_A_KIND(DiceRoll::fourOfAKind),
    SMALL_STRAIGHT(DiceRoll::smallStraight),
    LARGE_STRAIGHT(DiceRoll::largeStraight),
    FULL_HOUSE(DiceRoll::fullHouse)
    ;

    private final Function<DiceRoll, Integer> scoringFunction;

    Bet(Function<DiceRoll, Integer> scoringFunction) {
        this.scoringFunction = scoringFunction;
    }

    public int score(DiceRoll diceRoll) {
        return this.scoringFunction.apply(diceRoll);
    }
}
