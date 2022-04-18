package com.portfolio.management.portfolio.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "chart_data")
public class ChartData {
	
	@Id
	@Column(name = "portfolio_id")
	private String portfolio_id;
	
	@Column(name = "date" )
	@JsonProperty("Date")
	private Date date;
	
	@Column(name = "monte_carlo")	
	@JsonProperty("Monte_Carlo")
	private double monte_carlo;
	
	@Column(name = "dl")
	@JsonProperty("DL")
	private double dl;
	
	@Column(name = "dynamic_dl")
	@JsonProperty("Dynamic_DL")
	private double dynamic_dl;
	
	@Column(name = "nifty50")
	@JsonProperty("nifty50")
	private double nifty50;
	
	
	
//	@Transient
//	@JsonProperty("Date")
//	private String DDate;
//	
	
//	public void convertStringToDate() {
//		   try {
//			this.date=new SimpleDateFormat("dd/MM/yyyy").parse(this.DDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//	}
	
	
	
	

//	public String getDDate() {
//		return DDate;
//	}
//
//	public void setDDate(String dDate) {
//		DDate = dDate;
//	}

	public double getNifty50() {
		return nifty50;
	}

	public void setNifty50(double nifty50) {
		this.nifty50 = nifty50;
	}

	public String getPortfolio_id() {
		return portfolio_id;
	}

	public void setPortfolio_id(String portfolio_id) {
		this.portfolio_id = portfolio_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMonte_carlo() {
		return monte_carlo;
	}

	public void setMonte_carlo(double monte_carlo) {
		this.monte_carlo = monte_carlo;
	}

	public double getDl() {
		return dl;
	}

	public void setDl(double dl) {
		this.dl = dl;
	}

	public double getDynamic_dl() {
		return dynamic_dl;
	}

	public void setDynamic_dl(double dynamic_dl) {
		this.dynamic_dl = dynamic_dl;
	}

	@Override
	public String toString() {
		return "ChartData [portfolio_id=" + portfolio_id + ", date=" + date + ", monte_carlo=" + monte_carlo + ", dl="
				+ dl + ", dynamic_dl=" + dynamic_dl + ", nifty50=" + nifty50 + "]";
	}

	
	
	

}
