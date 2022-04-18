package com.portfolio.management.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "portfolio_performance")
public class Portfolio_Performance {
	@Id
	@Column(name = "portfolio_id")
	private String portfolio_id;
	
	@JsonProperty("five_year_return")
	@Column(name = "five_year_return")
	private double five_year_return;
	
	@JsonProperty("sharpe_ratio")
	@Column(name = "sharpe_ratio")
	private double sharpe_ratio;
	
	@JsonProperty("value_at_risk")
	@Column(name = "value_at_risk")
	private double value_at_risk;
	
	@JsonProperty("portfolio_beta")
	@Column(name = "portfolio_beta")
	private double portfolio_beta;
	
	@JsonProperty("treynor_ratio")
	@Column(name = "treynor_ratio")
	private double treynor_ratio;
	
	@JsonProperty("jensens_alpha")
	@Column(name = "jensens_alpha")
	private double jensens_alpha;
	
	@Transient
	@JsonProperty("month")
	private String month;
	
	@Transient
	@JsonProperty("latest_value")
	private Double latest_value;
	
	
	@Transient
	@JsonProperty("overall_gain")
	private Double overall_gain;
	
	
	@Transient
	@JsonProperty("investment_Cost")
	private Double investment_Cost;
	
	
	@Transient
	@JsonProperty("todays_gain")
	private Double todays_gain;
	
	
	
	
	
	
	
	
	
	
	public Double getLatest_value() {
		return latest_value;
	}

	public void setLatest_value(Double latest_value) {
		this.latest_value = latest_value;
	}

	public Double getOverall_gain() {
		return overall_gain;
	}

	public void setOverall_gain(Double overall_gain) {
		this.overall_gain = overall_gain;
	}

	public Double getInvestment_Cost() {
		return investment_Cost;
	}

	public void setInvestment_Cost(Double investment_Cost) {
		this.investment_Cost = investment_Cost;
	}

	public Double getTodays_gain() {
		return todays_gain;
	}

	public void setTodays_gain(Double todays_gain) {
		this.todays_gain = todays_gain;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	@Transient
	@JsonProperty("Volatility")
	public double Volatility;

	public String getPortfolio_id() {
		return portfolio_id;
	}

	public void setPortfolio_id(String portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

	public double getFive_year_return() {
		return five_year_return;
	}

	public void setFive_year_return(double five_year_return) {
		this.five_year_return = five_year_return;
	}

	public double getSharpe_ratio() {
		return sharpe_ratio;
	}

	public void setSharpe_ratio(double sharpe_ratio) {
		this.sharpe_ratio = sharpe_ratio;
	}

	public double getValue_at_risk() {
		return value_at_risk;
	}

	public void setValue_at_risk(double value_at_risk) {
		this.value_at_risk = value_at_risk;
	}

	public double getPortfolio_beta() {
		return portfolio_beta;
	}

	public void setPortfolio_beta(double portfolio_beta) {
		this.portfolio_beta = portfolio_beta;
	}

	public double getTreynor_ratio() {
		return treynor_ratio;
	}

	public void setTreynor_ratio(double treynor_ratio) {
		this.treynor_ratio = treynor_ratio;
	}

	public double getJensens_alpha() {
		return jensens_alpha;
	}

	public void setJensens_alpha(double jensens_alpha) {
		this.jensens_alpha = jensens_alpha;
	}
	
	

	public Portfolio_Performance() {
		super();
	}

	@Override
	public String toString() {
		return "Portfolio_Performance [portfolio_id=" + portfolio_id + ", five_year_return=" + five_year_return
				+ ", sharpe_ratio=" + sharpe_ratio + ", value_at_risk=" + value_at_risk + ", portfolio_beta="
				+ portfolio_beta + ", treynor_ratio=" + treynor_ratio + ", jensens_alpha=" + jensens_alpha + ", month="
				+ month + ", Volatility=" + Volatility + "]";
	}

	
	
	

}
