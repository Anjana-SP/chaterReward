package com.customer.retailer.model;


/**
 * Represents the reward points earned by a customer over the last three months.
 * This model is typically used to calculate and display reward summaries
 * based on customer transactions.
 * 
 * Fields include reward points for the last month, second last month,
 * third last month, and the total reward points.
 * 
 * This class is a simple POJO (Plain Old Java Object) with getters and setters.
 * 
 * Example usage:
 * <pre>
 *     Rewards rewards = new Rewards();
 *     rewards.setCustomerId(101);
 *     rewards.setLastMonthRewardPoints(120);
 *     rewards.setTotalRewards(300);
 * </pre>
 * 
 */

public class Rewards {

	private long customerId;
	private long lastMonthRewardPoints;
    private long lastSecondMonthRewardPoints;
    private long lastThirdMonthRewardPoints;
    private long totalRewards;
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getLastMonthRewardPoints() {
		return lastMonthRewardPoints;
	}
	public void setLastMonthRewardPoints(long lastMonthRewardPoints) {
		this.lastMonthRewardPoints = lastMonthRewardPoints;
	}
	public long getLastSecondMonthRewardPoints() {
		return lastSecondMonthRewardPoints;
	}
	public void setLastSecondMonthRewardPoints(long lastSecondMonthRewardPoints) {
		this.lastSecondMonthRewardPoints = lastSecondMonthRewardPoints;
	}
	public long getLastThirdMonthRewardPoints() {
		return lastThirdMonthRewardPoints;
	}
	public void setLastThirdMonthRewardPoints(long lastThirdMonthRewardPoints) {
		this.lastThirdMonthRewardPoints = lastThirdMonthRewardPoints;
	}
	public long getTotalRewards() {
		return totalRewards;
	}
	public void setTotalRewards(long totalRewards) {
		this.totalRewards = totalRewards;
	}

    
}
