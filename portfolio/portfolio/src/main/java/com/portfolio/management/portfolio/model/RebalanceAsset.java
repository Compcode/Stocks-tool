package com.portfolio.management.portfolio.model;

import javax.persistence.*;

@Entity
@Table(name = "rebalance_asset")
public class RebalanceAsset {
	
	@Id
	@Column(name = "asset_id")
	private String asset_id;
	
	@Column(name = "portfolio_id")
	private String portfolio_id;
	
	@Column(name = "weight")
	private String weight;
	
	@Column(name = "portfolio_investment")
	private String portfolio_investment;
	
	@Column(name = "percent")
	private String percent;


	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public String getPortfolio_id() {
		return portfolio_id;
	}

	public void setPortfolio_id(String portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPortfolio_investment() {
		return portfolio_investment;
	}

	public void setPortfolio_investment(String portfolio_investment) {
		this.portfolio_investment = portfolio_investment;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "RebalanceAsset [asset_id=" + asset_id + ", portfolio_id=" + portfolio_id + ", weight=" + weight
				+ ", portfolio_investment=" + portfolio_investment + ", percent=" + percent + "]";
	}

	

		

}
