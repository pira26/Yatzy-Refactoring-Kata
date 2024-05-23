package org.codingdojo.yatzy1;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public enum Bet {
    CHANCE(Bet::scoreChance),
    YATZY(Bet::scoreYatzy),
    ONES(Bet::scoreOnes),
    TWOS(Bet::scoreTwos),
    THREES(Bet::scoreThrees),
    FOURS(Bet::scoreFours),
    FIVES(Bet::scoreFives),
    SIXES(Bet::scoreSixes),
    ONE_PAIR(Bet::scoreOnePair),
    TWO_PAIR(Bet::scoreTwoPair),
    THREE_OF_A_KIND(Bet::scoreThreeOfAKind),
    FOUR_OF_A_KIND(Bet::scoreFourOfAKind),
    SMALL_STRAIGHT(Bet::scoreSmallStraight),
    LARGE_STRAIGHT(Bet::scoreLargeStraight),
    FULL_HOUSE(Bet::scoreFullHouse);

    private final Function<DiceRoll, Integer> scoringFunction;

    Bet(Function<DiceRoll, Integer> scoringFunction) {
        this.scoringFunction = scoringFunction;
    }

    public int applyBet(DiceRoll diceRoll) {
        return scoringFunction.apply(diceRoll);
    }

    private static int scoreChance(DiceRoll diceRoll) {
        return diceRoll.sum();
    }

    private static int scoreYatzy(DiceRoll diceRoll) {
        var YATZY = 50;
        if (diceRoll.isYatzy()) {
            return YATZY;
        }
        return 0;
    }

    private static int scoreOnes(DiceRoll diceRoll) {
        return diceRoll.diceCountValue(1);
    }

    private static int scoreTwos(DiceRoll diceRoll) {
        return diceRoll.diceCountValue(2) * 2;
    }

    private static int scoreThrees(DiceRoll diceRoll) {
        return diceRoll.diceCountValue(3) * 3;
    }

    private static int scoreFours(DiceRoll diceRoll) {
        return diceRoll.diceCountValue(4) * 4;
    }

    private static int scoreFives(DiceRoll diceRoll) {
        return diceRoll.diceCountValue(5) * 5;
    }

    private static int scoreSixes(DiceRoll diceRoll) {
        return diceRoll.diceCountValue(6) * 6;
    }

    private static int scoreOnePair(DiceRoll diceRoll) {
        List<Integer> pair = diceRoll.retrievePairs();
        if (!pair.isEmpty()) {
            return pair.get(0) * 2;
        }
        return 0;
    }

    private static int scoreTwoPair(DiceRoll diceRoll) {
        List<Integer> pairs = diceRoll.retrievePairs();
        if (pairs.size() >= 2) {
            return pairs.stream().mapToInt(pair -> pair * 2).sum();
        }
        return 0;
    }

    private static int scoreThreeOfAKind(DiceRoll diceRoll) {
        return diceRoll.retrieveNOfAKindValue(3) * 3;
    }

    private static int scoreFourOfAKind(DiceRoll diceRoll) {
        return diceRoll.retrieveNOfAKindValue(4) * 4;
    }

    private static int scoreSmallStraight(DiceRoll diceRoll) {
        var SMALL_STRAIGHT_LIST = new int[]{1, 2, 3, 4, 5};
        if (diceRoll.isStraight(SMALL_STRAIGHT_LIST)) {
            return diceRoll.sum();
        }

        return 0;
    }

    private static int scoreLargeStraight(DiceRoll diceRoll) {
        var LARGE_STRAIGHT_LIST = new int[]{2, 3, 4, 5, 6};
        if (diceRoll.isStraight(LARGE_STRAIGHT_LIST)) {
            return diceRoll.sum();
        }
        return 0;
    }

    private static int scoreFullHouse(DiceRoll diceRoll) {
        var threeOfAKindValue = diceRoll.retrieveNOfAKindValue(3);
        var isNotYatzy = !diceRoll.isYatzy();
        var hasAPair = !diceRoll.retrievePairs().isEmpty();
        var hasAThreeOfAKind = threeOfAKindValue != 0;
        if (hasAPair && hasAThreeOfAKind && isNotYatzy) {
            var arePairAndThreeOfAKindValuesDifferent = !Objects.equals(diceRoll.retrievePairs().get(0), threeOfAKindValue);
            if (arePairAndThreeOfAKindValuesDifferent) {
                return diceRoll.sum();
            }
            return 0;
        }
        return 0;
    }
}
