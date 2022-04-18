package com.portfolio.management.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "portfolio_info")
public class PortfolioInfo {
	
	@Id
	@Column(name = "portfolio_id")
	private String portfolio_id;
	
	@Column(name = "user_id")
	private int user_id;
	
	@Column(name = "portfolio_name")
	private String portfolio_name;
	
	@Column(name = "portfolio_investment")
	private double portfolio_investment;

	public String getPortfolio_id() {
		return portfolio_id;
	}

	public void setPortfolio_id(String portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getPortfolio_name() {
		return portfolio_name;
	}

	public void setPortfolio_name(String portfolio_name) {
		this.portfolio_name = portfolio_name;
	}

	public double getPortfolio_investment() {
		return portfolio_investment;
	}

	public void setPortfolio_investment(double portfolio_investment) {
		this.portfolio_investment = portfolio_investment;
	}

	@Override
	public String toString() {
		return "PortfolioInfo [portfolio_id=" + portfolio_id + ", user_id=" + user_id + ", portfolio_name="
				+ portfolio_name + ", portfolio_investment=" + portfolio_investment + "]";
	}
	
	

}
