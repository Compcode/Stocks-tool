package com.portfolio.management.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table(name = "recommended_asset_weights")
@Entity
public class RecommendedAssetWeights {
	
	@Id
	@Column(name = "asset_id")
	@JsonProperty("asset_id")
	private String asset_id;
	
	@Column(name = "portfolio_id")
	@JsonProperty("portfolio_id")
	private String portfolio_id;
	
	@Column(name = "weight")
	@JsonProperty("weight")
	private Double weight;	
	
	@Column(name = "amount")
	@JsonProperty("amount")
	private Double amount;


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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AssetWeights [asset_id=" + asset_id + ", portfolio_id=" + portfolio_id + ", weight=" + weight
				+ ", amount=" + amount + "]";
	}
	
	
	

}
