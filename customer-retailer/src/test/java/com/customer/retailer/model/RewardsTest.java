package com.customer.retailer.model;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RewardsTest {

    private Rewards rewards;

    @BeforeEach
    public void setUp() {
        rewards = new Rewards();
    }

    @Test
    public void testCustomerId() {
        rewards.setCustomerId(12345L);
        assertEquals(12345L, rewards.getCustomerId());
    }

    @Test
    public void testLastMonthRewardPoints() {
        rewards.setLastMonthRewardPoints(100L);
        assertEquals(100L, rewards.getLastMonthRewardPoints());
    }

    @Test
    public void testLastSecondMonthRewardPoints() {
        rewards.setLastSecondMonthRewardPoints(200L);
        assertEquals(200L, rewards.getLastSecondMonthRewardPoints());
    }

    @Test
    public void testLastThirdMonthRewardPoints() {
        rewards.setLastThirdMonthRewardPoints(300L);
        assertEquals(300L, rewards.getLastThirdMonthRewardPoints());
    }

    @Test
    public void testTotalRewards() {
        rewards.setTotalRewards(600L);
        assertEquals(600L, rewards.getTotalRewards());
    }

    @Test
    public void testAllFieldsTogether() {
        rewards.setCustomerId(1L);
        rewards.setLastMonthRewardPoints(50L);
        rewards.setLastSecondMonthRewardPoints(60L);
        rewards.setLastThirdMonthRewardPoints(70L);
        rewards.setTotalRewards(180L);

        assertEquals(1L, rewards.getCustomerId());
        assertEquals(50L, rewards.getLastMonthRewardPoints());
        assertEquals(60L, rewards.getLastSecondMonthRewardPoints());
        assertEquals(70L, rewards.getLastThirdMonthRewardPoints());
        assertEquals(180L, rewards.getTotalRewards());
    }


    @Test
    public void testZeroValues() {
        Rewards rewards = new Rewards();
        rewards.setCustomerId(0L);
        rewards.setLastMonthRewardPoints(0L);
        rewards.setLastSecondMonthRewardPoints(0L);
        rewards.setLastThirdMonthRewardPoints(0L);
        rewards.setTotalRewards(0L);

        assertEquals(0L, rewards.getCustomerId());
        assertEquals(0L, rewards.getLastMonthRewardPoints());
        assertEquals(0L, rewards.getLastSecondMonthRewardPoints());
        assertEquals(0L, rewards.getLastThirdMonthRewardPoints());
        assertEquals(0L, rewards.getTotalRewards());
    }

    @Test
    public void testNegativeValues() {
        Rewards rewards = new Rewards();
        rewards.setLastMonthRewardPoints(-10L);
        rewards.setLastSecondMonthRewardPoints(-20L);
        rewards.setLastThirdMonthRewardPoints(-30L);
        rewards.setTotalRewards(-60L);

        assertEquals(-10L, rewards.getLastMonthRewardPoints());
        assertEquals(-20L, rewards.getLastSecondMonthRewardPoints());
        assertEquals(-30L, rewards.getLastThirdMonthRewardPoints());
        assertEquals(-60L, rewards.getTotalRewards());
    }

    @Test
    public void testMaxLongValues() {
        Rewards rewards = new Rewards();
        rewards.setCustomerId(Long.MAX_VALUE);
        rewards.setLastMonthRewardPoints(Long.MAX_VALUE);
        rewards.setLastSecondMonthRewardPoints(Long.MAX_VALUE);
        rewards.setLastThirdMonthRewardPoints(Long.MAX_VALUE);
        rewards.setTotalRewards(Long.MAX_VALUE);

        assertEquals(Long.MAX_VALUE, rewards.getCustomerId());
        assertEquals(Long.MAX_VALUE, rewards.getLastMonthRewardPoints());
        assertEquals(Long.MAX_VALUE, rewards.getLastSecondMonthRewardPoints());
        assertEquals(Long.MAX_VALUE, rewards.getLastThirdMonthRewardPoints());
        assertEquals(Long.MAX_VALUE, rewards.getTotalRewards());
    }

    @Test
    public void testMinLongValues() {
        Rewards rewards = new Rewards();
        rewards.setCustomerId(Long.MIN_VALUE);
        rewards.setLastMonthRewardPoints(Long.MIN_VALUE);
        rewards.setLastSecondMonthRewardPoints(Long.MIN_VALUE);
        rewards.setLastThirdMonthRewardPoints(Long.MIN_VALUE);
        rewards.setTotalRewards(Long.MIN_VALUE);

        assertEquals(Long.MIN_VALUE, rewards.getCustomerId());
        assertEquals(Long.MIN_VALUE, rewards.getLastMonthRewardPoints());
        assertEquals(Long.MIN_VALUE, rewards.getLastSecondMonthRewardPoints());
        assertEquals(Long.MIN_VALUE, rewards.getLastThirdMonthRewardPoints());
        assertEquals(Long.MIN_VALUE, rewards.getTotalRewards());
    }
}
