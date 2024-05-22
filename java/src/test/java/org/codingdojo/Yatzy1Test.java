package org.codingdojo;

import org.codingdojo.yatzy1.Yatzy1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Yatzy1Test {

    @Test
    public void should_assert_chance() {
        assertEquals(15, Yatzy1.chance(2, 3, 4, 5, 1));
        assertEquals(16, Yatzy1.chance(3, 3, 4, 5, 1));
        assertEquals(14, Yatzy1.chance(1, 1, 3, 3, 6));
        assertEquals(22, Yatzy1.chance(5, 5, 5, 6, 1));
        assertEquals(25, Yatzy1.chance(5, 5, 5, 5, 5));
    }

    @Test
    public void should_assert_yatzy() {
        assertEquals(50, Yatzy1.yatzy(4, 4, 4, 4, 4));
        assertEquals(50, Yatzy1.yatzy(6, 6, 6, 6, 6));
        assertEquals(0, Yatzy1.yatzy(6, 6, 6, 6, 3));
    }

    @Test
    public void should_assert_ones() {
        assertEquals(1, Yatzy1.ones(1, 2, 3, 4, 5));
        assertEquals(2, Yatzy1.ones(1, 2, 1, 4, 5));
        assertEquals(0, Yatzy1.ones(6, 2, 2, 4, 5));
        assertEquals(4, Yatzy1.ones(1, 2, 1, 1, 1));
    }

    @Test
    public void should_assert_twos() {
        assertEquals(4, Yatzy1.twos(1, 2, 3, 2, 6));
        assertEquals(10, Yatzy1.twos(2, 2, 2, 2, 2));
    }

    @Test
    public void should_assert_threes() {
        assertEquals(6, Yatzy1.threes(1, 2, 3, 2, 3));
        assertEquals(12, Yatzy1.threes(2, 3, 3, 3, 3));
        assertEquals(0, Yatzy1.threes(2, 1, 4, 6, 5));
    }

    @Test
    public void should_assert_fours() {
        assertEquals(12, Yatzy1.fours(4, 4, 4, 5, 5));
        assertEquals(8, Yatzy1.fours(4, 4, 5, 5, 5));
        assertEquals(4, Yatzy1.fours(4, 5, 5, 5, 5));
        assertEquals(0, Yatzy1.fours(2, 5, 5, 5, 5));
    }

    @Test
    public void should_assert_fives() {
        assertEquals(10, Yatzy1.fives(4, 4, 4, 5, 5));
        assertEquals(15, Yatzy1.fives(4, 4, 5, 5, 5));
        assertEquals(20, Yatzy1.fives(4, 5, 5, 5, 5));
        assertEquals(0, Yatzy1.fives(4, 1, 2, 3, 6));
    }

    @Test
    public void should_assert_sixes() {
        assertEquals(0, Yatzy1.sixes(4, 4, 4, 5, 5));
        assertEquals(6, Yatzy1.sixes(4, 4, 6, 5, 5));
        assertEquals(18, Yatzy1.sixes(6, 5, 6, 6, 5));
    }

    @Test
    public void should_assert_one_pair() {
        assertEquals(6, Yatzy1.onePair(3, 4, 3, 5, 6));
        assertEquals(10, Yatzy1.onePair(5, 3, 3, 3, 5));
        assertEquals(12, Yatzy1.onePair(5, 3, 6, 6, 5));
        assertEquals(0, Yatzy1.onePair(1, 2, 3, 6, 5));
    }

    @Test
    public void should_assert_two_pair() {
        assertEquals(16, Yatzy1.twoPair(3, 3, 5, 4, 5));
        assertEquals(16, Yatzy1.twoPair(3, 3, 5, 5, 5));
        assertEquals(0, Yatzy1.twoPair(3, 2, 5, 1, 4));
        assertEquals(0, Yatzy1.twoPair(3, 5, 5, 1, 4));
        assertEquals(0, Yatzy1.twoPair(3, 3, 3, 3, 1));
    }

    @Test
    public void should_assert_three_of_a_kind() {
        assertEquals(9, Yatzy1.threeOfAKind(3, 3, 3, 4, 5));
        assertEquals(15, Yatzy1.threeOfAKind(5, 3, 5, 4, 5));
        assertEquals(9, Yatzy1.threeOfAKind(3, 3, 3, 3, 5));
        assertEquals(9, Yatzy1.threeOfAKind(3, 3, 3, 3, 3));
        assertEquals(0, Yatzy1.threeOfAKind(1, 1, 2, 3, 3));
    }

    @Test
    public void should_assert_four_of_a_kind() {
        assertEquals(0, Yatzy1.fourOfAKind(3, 1, 3, 3, 5));
        assertEquals(12, Yatzy1.fourOfAKind(3, 3, 3, 3, 5));
        assertEquals(20, Yatzy1.fourOfAKind(5, 5, 5, 4, 5));
        assertEquals(20, Yatzy1.fourOfAKind(5, 5, 5, 5, 5));
    }

    @Test
    public void should_assert_small_straight() {
        assertEquals(15, Yatzy1.smallStraight(1, 2, 3, 4, 5));
        assertEquals(15, Yatzy1.smallStraight(2, 3, 4, 5, 1));
        assertEquals(0, Yatzy1.smallStraight(1, 2, 2, 4, 5));
        assertEquals(0, Yatzy1.smallStraight(2, 3, 4, 5, 6));
    }

    @Test
    public void should_assert_large_straight() {
        assertEquals(20, Yatzy1.largeStraight(6, 2, 3, 4, 5));
        assertEquals(20, Yatzy1.largeStraight(2, 3, 4, 5, 6));
        assertEquals(0, Yatzy1.largeStraight(1, 2, 3, 4, 5));
    }

    @Test
    public void should_assert_full_house() {
        assertEquals(18, Yatzy1.fullHouse(6, 2, 2, 2, 6));
        assertEquals(0, Yatzy1.fullHouse(2, 3, 4, 5, 6));
    }
}
