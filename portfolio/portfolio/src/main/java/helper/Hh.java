package helper;

import com.portfolio.management.portfolio.model.PortfolioInfo;

public class Hh {
	private PortfolioInfo portfolioInfo;
	private StockList stockList;
	
	public PortfolioInfo getP() {
		return portfolioInfo;
	}
	public void setP(PortfolioInfo p) {
		this.portfolioInfo = p;
	}
	public StockList getS() {
		return stockList;
	}
	public void setS(StockList s) {
		this.stockList = s;
	}
	@Override
	public String toString() {
		return "hh [p=" + portfolioInfo + ", s=" + stockList + "]";
	}
}
