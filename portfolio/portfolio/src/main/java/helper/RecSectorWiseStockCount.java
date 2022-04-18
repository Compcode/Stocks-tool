package helper;

import java.math.BigInteger;

public class RecSectorWiseStockCount implements java.io.Serializable {
	private String stockName;
	private BigInteger sector_wise_count;
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public BigInteger getSector_wise_count() {
		return sector_wise_count;
	}
	public void setSector_wise_count(BigInteger sector_wise_count) {
		this.sector_wise_count = sector_wise_count;
	}
	@Override
	public String toString() {
		return "RecSectorWiseStockCount [stockName=" + stockName + ", sector_wise_count=" + sector_wise_count + "]";
	}
	
	public RecSectorWiseStockCount(String stockName, BigInteger sector_wise_count) {
		super();
		this.stockName = stockName;
		this.sector_wise_count = sector_wise_count;
	}

}
