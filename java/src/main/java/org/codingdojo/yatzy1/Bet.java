package org.codingdojo.yatzy1;

import java.util.List;
import java.util.Objects;

public enum Bet {
    CHANCE {
        public int score(DiceRoll roll) {
            return Bet.scoreChance(roll);
        }
    },
    YATZY {
        public int score(DiceRoll roll) {
            return Bet.scoreYatzy(roll);
        }
    },
    ONES {
        public int score(DiceRoll roll) {
            return Bet.scoreNValue(roll, 1);
        }
    },
    TWOS {
        public int score(DiceRoll roll) {
            return Bet.scoreNValue(roll, 2);
        }
    },
    THREES {
        public int score(DiceRoll roll) {
            return Bet.scoreNValue(roll, 3);
        }
    },
    FOURS {
        public int score(DiceRoll roll) {
            return Bet.scoreNValue(roll, 4);
        }
    },
    FIVES {
        public int score(DiceRoll roll) {
            return Bet.scoreNValue(roll, 5);
        }
    },
    SIXES {
        public int score(DiceRoll roll) {
            return Bet.scoreNValue(roll, 6);
        }
    },
    ONE_PAIR {
        public int score(DiceRoll roll) {
            return Bet.scoreOnePair(roll);
        }
    },
    TWO_PAIR {
        public int score(DiceRoll roll) {
            return Bet.scoreTwoPair(roll);
        }
    },
    THREE_OF_A_KIND {
        public int score(DiceRoll roll) {
            return Bet.scoreThreeOfAKind(roll);
        }
    },
    FOUR_OF_A_KIND {
        public int score(DiceRoll roll) {
            return Bet.scoreFourOfAKind(roll);
        }
    },
    SMALL_STRAIGHT {
        public int score(DiceRoll roll) {
            return Bet.scoreSmallStraight(roll);
        }
    },
    LARGE_STRAIGHT {
        public int score(DiceRoll roll) {
            return Bet.scoreLargeStraight(roll);
        }
    },
    FULL_HOUSE {
        public int score(DiceRoll roll) {
            return Bet.scoreFullHouse(roll);
        }
    };

    public abstract int score(DiceRoll roll);

    private static int scoreChance(DiceRoll diceRoll) {
        return diceRoll.sum();
    }

    private static int scoreYatzy(DiceRoll diceRoll) {
        if (diceRoll.isYatzy()) {
            return 50;
        }
        return 0;
    }

    private static int scoreNValue(DiceRoll diceRoll, int number) {
        return diceRoll.diceCountValue(number) * number;
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
        var hasAPair = !diceRoll.retrievePairs().isEmpty();
        var threeOfAKindValue = diceRoll.retrieveNOfAKindValue(3);
        var hasAThreeOfAKind = threeOfAKindValue != 0;
        var isNotYatzy = !diceRoll.isYatzy();
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
