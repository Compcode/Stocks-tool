package com.portfolio.management.portfolio.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asset_data")
public class AssetData {
	
	@Id
	@Column(name = "asset_id")
	private String asset_id;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "clsoing_price")
	private double clsoing_price;

	

	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getClsoing_price() {
		return clsoing_price;
	}

	public void setClsoing_price(double clsoing_price) {
		this.clsoing_price = clsoing_price;
	}

	@Override
	public String toString() {
		return "AssetData [asset_id=" + asset_id + ", date=" + date + ", clsoing_price=" + clsoing_price + "]";
	}
	
	

}
