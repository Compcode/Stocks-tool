package helper;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChartDataJson {
	
	private String portfolio_id;
	
	private Date date;
	
	@JsonProperty("Monte_Carlo")
	private ArrayList<Double> monte_carlo;
	
	@JsonProperty("DL")
	private ArrayList<Double> dl;
	
	@JsonProperty("Dynamic_DL")
	private ArrayList<Double> dynamic_dl;
	
	@JsonProperty("Date")
	private ArrayList<String> DDate;

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

	public ArrayList<Double> getMonte_carlo() {
		return monte_carlo;
	}

	public void setMonte_carlo(ArrayList<Double> monte_carlo) {
		this.monte_carlo = monte_carlo;
	}

	public ArrayList<Double> getDl() {
		return dl;
	}

	public void setDl(ArrayList<Double> dl) {
		this.dl = dl;
	}

	public ArrayList<Double> getDynamic_dl() {
		return dynamic_dl;
	}

	public void setDynamic_dl(ArrayList<Double> dynamic_dl) {
		this.dynamic_dl = dynamic_dl;
	}

	public ArrayList<String> getDDate() {
		return DDate;
	}

	public void setDDate(ArrayList<String> dDate) {
		DDate = dDate;
	}

	@Override
	public String toString() {
		return "ChartDataJson [portfolio_id=" + portfolio_id + ", date=" + date + ", monte_carlo=" + monte_carlo
				+ ", dl=" + dl + ", dynamic_dl=" + dynamic_dl + ", DDate=" + DDate + "]";
	}
	
	
	
	


}
