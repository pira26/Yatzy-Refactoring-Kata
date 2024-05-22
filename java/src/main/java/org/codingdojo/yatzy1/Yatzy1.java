package org.codingdojo.yatzy1;

public class Yatzy1 {
    public static int chance(DiceRoll diceRoll) {
        return diceRoll.sum();
    }

    public static int yatzy(DiceRoll diceRoll) {
        return diceRoll.yatzy();
    }

    public static int ones(DiceRoll diceRoll) {
        return diceRoll.ones();
    }

    public static int twos(DiceRoll diceRoll) {
        return diceRoll.twos();
    }

    public static int threes(DiceRoll diceRoll) {
        return diceRoll.threes();
    }

    public static int fours(DiceRoll diceRoll) {
        return diceRoll.fours();
    }

    public static int fives(DiceRoll diceRoll) {
        return diceRoll.fives();
    }

    public static int sixes(DiceRoll diceRoll) {
        return diceRoll.sixes();
    }

    public static int onePair(DiceRoll diceRoll) {
        return diceRoll.onePair();
    }

    public static int twoPair(DiceRoll diceRoll) {
        return diceRoll.twoPair();
    }

    public static int threeOfAKind(DiceRoll diceRoll) {
        return diceRoll.threeOfAKind();
    }

    public static int fourOfAKind(DiceRoll diceRoll) {
        return diceRoll.fourOfAKind();
    }

    public static int smallStraight(DiceRoll diceRoll) {
        return diceRoll.smallStraight();
    }

    public static int largeStraight(DiceRoll diceRoll) {
        return diceRoll.largeStraight();
    }

    public static int fullHouse(DiceRoll diceRoll) {
        return diceRoll.fullHouse();
    }
}
