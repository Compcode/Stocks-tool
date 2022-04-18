package com.portfolio.management.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "portfolio_prediction")
public class PortfolioPrediction {
	
	@Id
	@Column(name = "performance_id")
	private int performance_id;
	
	@Column(name = "portfolio_id")
	private String portfolio_id;
	
	@Column(name = "percentage_return")
	private double percentage_return;
	
	@Column(name = "latest_value")
	private double latest_value;
	
	@Column(name = "overall_gain")
	private double overall_gain;
	
	@Column(name = "investment_Cost")
	private double investment_Cost;
	
	@Column(name = "todays_gain")
	private double todays_gain;
	
	@Column(name = "portfolio_beta")
	private double portfolio_beta;
	
	@Column(name = "sharpe_ratio")
	private double sharpe_ratio;
	

	@Column(name = "jensens_alpha")
	private double jensens_alpha;
	
	@Column(name = "value_at_risk")
	private double value_at_risk;
	
	@Column(name = "month")
	private String month;

	public int getPerformance_id() {
		return performance_id;
	}

	public void setPerformance_id(int performance_id) {
		this.performance_id = performance_id;
	}

	public String getPortfolio_id() {
		return portfolio_id;
	}

	public void setPortfolio_id(String portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

	public double getPercentage_return() {
		return percentage_return;
	}

	public void setPercentage_return(double percentage_return) {
		this.percentage_return = percentage_return;
	}

	public double getLatest_value() {
		return latest_value;
	}

	public void setLatest_value(double latest_value) {
		this.latest_value = latest_value;
	}

	public double getOverall_gain() {
		return overall_gain;
	}

	public void setOverall_gain(double overall_gain) {
		this.overall_gain = overall_gain;
	}

	public double getInvestment_Cost() {
		return investment_Cost;
	}

	public void setInvestment_Cost(double investment_Cost) {
		this.investment_Cost = investment_Cost;
	}

	public double getTodays_gain() {
		return todays_gain;
	}

	public void setTodays_gain(double todays_gain) {
		this.todays_gain = todays_gain;
	}

	public double getPortfolio_beta() {
		return portfolio_beta;
	}

	public void setPortfolio_beta(double portfolio_beta) {
		this.portfolio_beta = portfolio_beta;
	}

	public double getSharpe_ratio() {
		return sharpe_ratio;
	}

	public void setSharpe_ratio(double sharpe_ratio) {
		this.sharpe_ratio = sharpe_ratio;
	}

	public double getJensens_alpha() {
		return jensens_alpha;
	}

	public void setJensens_alpha(double jensens_alpha) {
		this.jensens_alpha = jensens_alpha;
	}

	public double getValue_at_risk() {
		return value_at_risk;
	}

	public void setValue_at_risk(double value_at_risk) {
		this.value_at_risk = value_at_risk;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "PortfolioPrediction [performance_id=" + performance_id + ", portfolio_id=" + portfolio_id
				+ ", percentage_return=" + percentage_return + ", latest_value=" + latest_value + ", overall_gain="
				+ overall_gain + ", investment_Cost=" + investment_Cost + ", todays_gain=" + todays_gain
				+ ", portfolio_beta=" + portfolio_beta + ", sharpe_ratio=" + sharpe_ratio + ", jensens_alpha="
				+ jensens_alpha + ", value_at_risk=" + value_at_risk + ", month=" + month + "]";
	}
}
