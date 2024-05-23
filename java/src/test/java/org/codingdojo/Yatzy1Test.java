package org.codingdojo;

import org.codingdojo.yatzy1.DiceRoll;
import org.junit.jupiter.api.Test;

import static org.codingdojo.yatzy1.Bet.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Yatzy1Test {

    @Test
    public void should_score_by_summing_all_dice_for_chance_category() {
        assertEquals(15, CHANCE.score(roll(2, 3, 4, 5, 1)));
        assertEquals(16, CHANCE.score(roll(3, 3, 4, 5, 1)));
        assertEquals(14, CHANCE.score(roll(1, 1, 3, 3, 6)));
        assertEquals(22, CHANCE.score(roll(5, 5, 5, 6, 1)));
        assertEquals(25, CHANCE.score(roll(5, 5, 5, 5, 5)));
    }

    @Test
    public void should_score_for_yatzy_category() {
        assertEquals(50, YATZY.score(roll(4, 4, 4, 4, 4)));
        assertEquals(50, YATZY.score(roll(6, 6, 6, 6, 6)));
    }

    @Test
    public void should_not_score_yatzy_category() {
        assertEquals(0, YATZY.score(roll(6, 6, 6, 6, 3)));
    }

    @Test
    public void should_score_for_ones_category() {
        assertEquals(1, ONES.score(roll(1, 2, 3, 4, 5)));
        assertEquals(2, ONES.score(roll(1, 2, 1, 4, 5)));
        assertEquals(4, ONES.score(roll(1, 2, 1, 1, 1)));
    }

    @Test
    public void should_not_score_for_ones_category() {
        assertEquals(0, ONES.score(roll(6, 2, 2, 4, 5)));
    }

    @Test
    public void should_score_for_twos_category() {
        assertEquals(4, TWOS.score(roll(1, 2, 3, 2, 6)));
        assertEquals(10, TWOS.score(roll(2, 2, 2, 2, 2)));
    }

    @Test
    public void should_not_score_for_twos_category() {
        assertEquals(0, TWOS.score(roll(4, 5, 3, 1, 6)));
    }

    @Test
    public void should_score_for_threes_category() {
        assertEquals(6, THREES.score(roll(1, 2, 3, 2, 3)));
        assertEquals(12, THREES.score(roll(2, 3, 3, 3, 3)));
    }

    @Test
    public void should_not_score_for_threes_category() {
        assertEquals(0, THREES.score(roll(2, 1, 4, 6, 5)));
    }

    @Test
    public void should_score_for_fours_category() {
        assertEquals(12, FOURS.score(roll(4, 4, 4, 5, 5)));
        assertEquals(8, FOURS.score(roll(4, 4, 5, 5, 5)));
        assertEquals(4, FOURS.score(roll(4, 5, 5, 5, 5)));
    }

    @Test
    public void should_not_score_for_fours_category() {
        assertEquals(0, FOURS.score(roll(2, 5, 5, 5, 5)));
    }

    @Test
    public void should_score_for_fives_category() {
        assertEquals(10, FIVES.score(roll(4, 4, 4, 5, 5)));
        assertEquals(15, FIVES.score(roll(4, 4, 5, 5, 5)));
        assertEquals(20, FIVES.score(roll(4, 5, 5, 5, 5)));
    }

    @Test
    public void should_not_score_for_fives_category() {
        assertEquals(0, FIVES.score(roll(4, 1, 2, 3, 6)));
    }

    @Test
    public void should_score_for_sixes_category() {
        assertEquals(6, SIXES.score(roll(4, 4, 6, 5, 5)));
        assertEquals(18, SIXES.score(roll(6, 5, 6, 6, 5)));
    }

    @Test
    public void should_not_score_for_sixes_category() {
        assertEquals(0, SIXES.score(roll(4, 4, 4, 5, 5)));
    }

    @Test
    public void should_score_for_one_pair_category_given_a_highest_pair() {
        assertEquals(6, ONE_PAIR.score(roll(3, 4, 3, 5, 6)));
        assertEquals(10, ONE_PAIR.score(roll(5, 3, 3, 3, 5)));
        assertEquals(12, ONE_PAIR.score(roll(5, 3, 6, 6, 5)));
        assertEquals(12, ONE_PAIR.score(roll(1, 2, 6, 6, 6)));
    }

    @Test
    public void should_not_score_for_one_pair_category() {
        assertEquals(0, ONE_PAIR.score(roll(1, 2, 3, 6, 5)));
    }

    @Test
    public void should_score_for_two_pair_category() {
        assertEquals(16, TWO_PAIR.score(roll(3, 3, 5, 4, 5)));
        assertEquals(16, TWO_PAIR.score(roll(3, 3, 5, 5, 5)));
    }

    @Test
    public void should_not_score_for_two_pair_category() {
        assertEquals(0, TWO_PAIR.score(roll(3, 2, 5, 1, 4)));
        assertEquals(0, TWO_PAIR.score(roll(3, 5, 5, 1, 4)));
        assertEquals(0, TWO_PAIR.score(roll(3, 3, 3, 3, 1)));
    }

    @Test
    public void should_score_for_three_of_a_kind_category() {
        assertEquals(9, THREE_OF_A_KIND.score(roll(3, 3, 3, 4, 5)));
        assertEquals(15, THREE_OF_A_KIND.score(roll(5, 3, 5, 4, 5)));
        assertEquals(9, THREE_OF_A_KIND.score(roll(3, 3, 3, 3, 5)));
        assertEquals(9, THREE_OF_A_KIND.score(roll(3, 3, 3, 3, 3)));
    }

    @Test
    public void should_not_score_for_three_of_a_kind_category() {
        assertEquals(0, THREE_OF_A_KIND.score(roll(1, 1, 2, 3, 3)));
    }

    @Test
    public void should_score_for_four_of_a_kind_category() {
        assertEquals(12, FOUR_OF_A_KIND.score(roll(3, 3, 3, 3, 5)));
        assertEquals(20, FOUR_OF_A_KIND.score(roll(5, 5, 5, 4, 5)));
        assertEquals(20, FOUR_OF_A_KIND.score(roll(5, 5, 5, 5, 5)));
    }

    @Test
    public void should_not_score_for_four_of_a_kind_category() {
        assertEquals(0, FOUR_OF_A_KIND.score(roll(3, 1, 3, 3, 5)));
    }

    @Test
    public void should_score_for_small_straight_category() {
        assertEquals(15, SMALL_STRAIGHT.score(roll(1, 2, 3, 4, 5)));
        assertEquals(15, SMALL_STRAIGHT.score(roll(2, 3, 4, 5, 1)));
    }

    @Test
    public void should_not_score_for_small_straight_category() {
        assertEquals(0, SMALL_STRAIGHT.score(roll(1, 2, 2, 4, 5)));
        assertEquals(0, SMALL_STRAIGHT.score(roll(2, 3, 4, 5, 6)));
    }

    @Test
    public void should_score_for_large_straight_category() {
        assertEquals(20, LARGE_STRAIGHT.score(roll(6, 2, 3, 4, 5)));
        assertEquals(20, LARGE_STRAIGHT.score(roll(2, 3, 4, 5, 6)));
    }

    @Test
    public void should_not_score_for_large_straight_category() {
        assertEquals(0, LARGE_STRAIGHT.score(roll(1, 2, 3, 4, 5)));
    }

    @Test
    public void should_score_for_full_house_category() {
        assertEquals(18, FULL_HOUSE.score(roll(6, 2, 2, 2, 6)));
    }

    @Test
    public void should_not_score_for_full_house_category() {
        assertEquals(0, FULL_HOUSE.score(roll(2, 3, 4, 5, 6)));
        assertEquals(0, FULL_HOUSE.score(roll(6, 6, 1, 2, 3)));
        assertEquals(0, FULL_HOUSE.score(roll(6, 6, 6, 2, 3)));
    }

    @Test
    public void should_not_score_for_full_house_category_given_a_yatzy() {
        assertEquals(0, FULL_HOUSE.score(roll(6, 6, 6, 6, 6)));
    }

    private static DiceRoll roll(int... args) {
        return new DiceRoll(args);
    }
}
