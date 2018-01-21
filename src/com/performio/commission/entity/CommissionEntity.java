package com.performio.commission.entity;

import java.math.BigDecimal;

public class CommissionEntity 
{

	private double actual;
	private double target;
	private double base=-1;
	private double rate=-1;
	private double achevement;
	private String error= null; 
	private double motc;
	private double fromValue = 0;
	private BigDecimal commission = new BigDecimal(0);
	private String achievementFilePath ="";
	public final double ACHIEVEMENT_MAX=99.99;
	
	public double getActual() {
		return actual;
	}
	public void setActual(double actual) {
		this.actual = actual;
	}
	public double getTarget() {
		return target;
	}
	public void setTarget(double target) {
		this.target = target;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAchevement() 
	{
		this.achevement = this.actual/this.target;
		return achevement;
	}
	public void setAchevement(double achevement) {
		this.achevement = achevement;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public double getMotc() {
		return motc;
	}
	public void setMotc(double motc) {
		this.motc = motc;
	}
	public double getFromValue() {
		return fromValue;
	}
	public void setFromValue(double fromValue) {
		this.fromValue = fromValue;
	}
	public String getAchievementFilePath() {
		return achievementFilePath;
	}
	public void setAchievementFilePath(String achievementFilePath) {
		this.achievementFilePath = achievementFilePath;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	/**
	 * Validate Input parameters Actual, Target and MOTC.
	 */
	public void validate()
	{
		if (this.target <=0) 
    	{
			this.error ="Target field value must be greater then zero.";
        }
		else if (this.actual <0) 
    	{
			this.error ="Actual field value must be greater or equal to zero.";
        }
		else if (this.motc <=0 || this.motc>10000) 
    	{
    		this.error ="MOTC field value must be greater than 0 and 1ess than or equal to 10,000";
        }
		else if(getAchevement()>=this.ACHIEVEMENT_MAX)
		{
			this.error = "Achievement cannot be greater or equal to 99.99";
		}
	}
}
