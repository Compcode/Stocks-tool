package com.portfolio.management.portfolio.model;

import javax.persistence.*;

@Entity
@Table(name = "sector_data")


public class SectorData {
	@Id
	@Column(name= "company_name")
	private String company_name;
	
	@Column(name= "industry")
	private String industry;
	
	@Column(name= "asset_id")
	private String asset_id;
	
	@Column(name= "series")
	private String series;
	
	@Column(name= "isin_code")
	private String isin_code;

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getIsin_code() {
		return isin_code;
	}

	public void setIsin_code(String isin_code) {
		this.isin_code = isin_code;
	}

	@Override
	public String toString() {
		return "SectorData [company_name=" + company_name + ", industry=" + industry + ", asset_id=" + asset_id
				+ ", series=" + series + ", isin_code=" + isin_code + "]";
	}

	

	

}
