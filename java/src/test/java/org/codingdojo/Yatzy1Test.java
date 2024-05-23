package org.codingdojo;

import org.codingdojo.yatzy1.DiceRoll;
import org.junit.jupiter.api.Test;

import static org.codingdojo.yatzy1.Bet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Yatzy1Test {

    @Test
    public void should_score_by_summing_all_dice_for_chance_category() {
        assertEquals(15, CHANCE.applyBet(roll(2, 3, 4, 5, 1)));
        assertEquals(16, CHANCE.applyBet(roll(3, 3, 4, 5, 1)));
        assertEquals(14, CHANCE.applyBet(roll(1, 1, 3, 3, 6)));
        assertEquals(22, CHANCE.applyBet(roll(5, 5, 5, 6, 1)));
        assertEquals(25, CHANCE.applyBet(roll(5, 5, 5, 5, 5)));
    }

    @Test
    public void should_score_for_yatzy_category() {
        assertEquals(50, YATZY.applyBet(roll(4, 4, 4, 4, 4)));
        assertEquals(50, YATZY.applyBet(roll(6, 6, 6, 6, 6)));
    }

    @Test
    public void should_not_score_yatzy_category() {
        assertEquals(0, YATZY.applyBet(roll(6, 6, 6, 6, 3)));
    }

    @Test
    public void should_score_for_ones_category() {
        assertEquals(1, ONES.applyBet(roll(1, 2, 3, 4, 5)));
        assertEquals(2, ONES.applyBet(roll(1, 2, 1, 4, 5)));
        assertEquals(4, ONES.applyBet(roll(1, 2, 1, 1, 1)));
    }

    @Test
    public void should_not_score_for_ones_category() {
        assertEquals(0, ONES.applyBet(roll(6, 2, 2, 4, 5)));
    }

    @Test
    public void should_score_for_twos_category() {
        assertEquals(4, TWOS.applyBet(roll(1, 2, 3, 2, 6)));
        assertEquals(10, TWOS.applyBet(roll(2, 2, 2, 2, 2)));
    }

    @Test
    public void should_not_score_for_twos_category() {
        assertEquals(0, TWOS.applyBet(roll(4, 5, 3, 1, 6)));
    }

    @Test
    public void should_score_for_threes_category() {
        assertEquals(6, THREES.applyBet(roll(1, 2, 3, 2, 3)));
        assertEquals(12, THREES.applyBet(roll(2, 3, 3, 3, 3)));
    }

    @Test
    public void should_not_score_for_threes_category() {
        assertEquals(0, THREES.applyBet(roll(2, 1, 4, 6, 5)));
    }

    @Test
    public void should_score_for_fours_category() {
        assertEquals(12, FOURS.applyBet(roll(4, 4, 4, 5, 5)));
        assertEquals(8, FOURS.applyBet(roll(4, 4, 5, 5, 5)));
        assertEquals(4, FOURS.applyBet(roll(4, 5, 5, 5, 5)));
    }

    @Test
    public void should_not_score_for_fours_category() {
        assertEquals(0, FOURS.applyBet(roll(2, 5, 5, 5, 5)));
    }

    @Test
    public void should_score_for_fives_category() {
        assertEquals(10, FIVES.applyBet(roll(4, 4, 4, 5, 5)));
        assertEquals(15, FIVES.applyBet(roll(4, 4, 5, 5, 5)));
        assertEquals(20, FIVES.applyBet(roll(4, 5, 5, 5, 5)));
    }

    @Test
    public void should_not_score_for_fives_category() {
        assertEquals(0, FIVES.applyBet(roll(4, 1, 2, 3, 6)));
    }

    @Test
    public void should_score_for_sixes_category() {
        assertEquals(6, SIXES.applyBet(roll(4, 4, 6, 5, 5)));
        assertEquals(18, SIXES.applyBet(roll(6, 5, 6, 6, 5)));
    }

    @Test
    public void should_not_score_for_sixes_category() {
        assertEquals(0, SIXES.applyBet(roll(4, 4, 4, 5, 5)));
    }

    @Test
    public void should_score_for_one_pair_category_given_a_highest_pair() {
        assertEquals(6, ONE_PAIR.applyBet(roll(3, 4, 3, 5, 6)));
        assertEquals(10, ONE_PAIR.applyBet(roll(5, 3, 3, 3, 5)));
        assertEquals(12, ONE_PAIR.applyBet(roll(5, 3, 6, 6, 5)));
        assertEquals(12, ONE_PAIR.applyBet(roll(1, 2, 6, 6, 6)));
    }

    @Test
    public void should_not_score_for_one_pair_category() {
        assertEquals(0, ONE_PAIR.applyBet(roll(1, 2, 3, 6, 5)));
    }

    @Test
    public void should_score_for_two_pair_category() {
        assertEquals(16, TWO_PAIR.applyBet(roll(3, 3, 5, 4, 5)));
        assertEquals(16, TWO_PAIR.applyBet(roll(3, 3, 5, 5, 5)));
    }

    @Test
    public void should_not_score_for_two_pair_category() {
        assertEquals(0, TWO_PAIR.applyBet(roll(3, 2, 5, 1, 4)));
        assertEquals(0, TWO_PAIR.applyBet(roll(3, 5, 5, 1, 4)));
        assertEquals(0, TWO_PAIR.applyBet(roll(3, 3, 3, 3, 1)));
    }

    @Test
    public void should_score_for_three_of_a_kind_category() {
        assertEquals(9, THREE_OF_A_KIND.applyBet(roll(3, 3, 3, 4, 5)));
        assertEquals(15, THREE_OF_A_KIND.applyBet(roll(5, 3, 5, 4, 5)));
        assertEquals(9, THREE_OF_A_KIND.applyBet(roll(3, 3, 3, 3, 5)));
        assertEquals(9, THREE_OF_A_KIND.applyBet(roll(3, 3, 3, 3, 3)));
    }

    @Test
    public void should_not_score_for_three_of_a_kind_category() {
        assertEquals(0, THREE_OF_A_KIND.applyBet(roll(1, 1, 2, 3, 3)));
    }

    @Test
    public void should_score_for_four_of_a_kind_category() {
        assertEquals(12, FOUR_OF_A_KIND.applyBet(roll(3, 3, 3, 3, 5)));
        assertEquals(20, FOUR_OF_A_KIND.applyBet(roll(5, 5, 5, 4, 5)));
        assertEquals(20, FOUR_OF_A_KIND.applyBet(roll(5, 5, 5, 5, 5)));
    }

    @Test
    public void should_not_score_for_four_of_a_kind_category() {
        assertEquals(0, FOUR_OF_A_KIND.applyBet(roll(3, 1, 3, 3, 5)));
    }

    @Test
    public void should_score_for_small_straight_category() {
        assertEquals(15, SMALL_STRAIGHT.applyBet(roll(1, 2, 3, 4, 5)));
        assertEquals(15, SMALL_STRAIGHT.applyBet(roll(2, 3, 4, 5, 1)));
    }

    @Test
    public void should_not_score_for_small_straight_category() {
        assertEquals(0, SMALL_STRAIGHT.applyBet(roll(1, 2, 2, 4, 5)));
        assertEquals(0, SMALL_STRAIGHT.applyBet(roll(2, 3, 4, 5, 6)));
    }

    @Test
    public void should_score_for_large_straight_category() {
        assertEquals(20, LARGE_STRAIGHT.applyBet(roll(6, 2, 3, 4, 5)));
        assertEquals(20, LARGE_STRAIGHT.applyBet(roll(2, 3, 4, 5, 6)));
    }

    @Test
    public void should_not_score_for_large_straight_category() {
        assertEquals(0, LARGE_STRAIGHT.applyBet(roll(1, 2, 3, 4, 5)));
    }

    @Test
    public void should_score_for_full_house_category() {
        assertEquals(18, FULL_HOUSE.applyBet(roll(6, 2, 2, 2, 6)));
    }

    @Test
    public void should_not_score_for_full_house_category() {
        assertEquals(0, FULL_HOUSE.applyBet(roll(2, 3, 4, 5, 6)));
        assertEquals(0, FULL_HOUSE.applyBet(roll(6, 6, 1, 2, 3)));
        assertEquals(0, FULL_HOUSE.applyBet(roll(6, 6, 6, 2, 3)));
    }

    @Test
    public void should_not_score_for_full_house_category_given_a_yatzy() {
        assertEquals(0, FULL_HOUSE.applyBet(roll(6, 6, 6, 6, 6)));
    }

    private static DiceRoll roll(int... args) {
        return new DiceRoll(args);
    }
}
