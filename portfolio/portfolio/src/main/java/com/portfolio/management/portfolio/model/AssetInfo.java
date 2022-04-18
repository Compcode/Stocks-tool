package com.portfolio.management.portfolio.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asset_info")
public class AssetInfo {
	
	
	@Id
	@Column(name = "asset_id")
	private String asset_id;
	
	@Column(name = "asset_name")
	private String asset_name;
	
	@Column(name= "asset_type")
	private String asset_type;


	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public String getAsset_name() {
		return asset_name;
	}

	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}

	public String getAsset_type() {
		return asset_type;
	}

	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}

	@Override
	public String toString() {
		return "AssetInfo [asset_id=" + asset_id + ", asset_name=" + asset_name + ", asset_type=" + asset_type + "]";
	}
	
	

}
