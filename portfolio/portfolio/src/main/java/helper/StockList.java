package helper;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StockList {
	private ArrayList<String> stockList;
	private ArrayList<Integer> percentAlloc;
	private String prtID;
	
	public ArrayList<String> getStockList() {
		return stockList;
	}
	public void setStockList(ArrayList<String> stockList) {
		this.stockList = stockList;
	}
	public ArrayList<Integer> getPercentAlloc() {
		return percentAlloc;
	}
	public void setPercentAlloc(ArrayList<Integer> percentAlloc) {
		this.percentAlloc = percentAlloc;
	}
	public String getPrtID() {
		return prtID;
	}
	public void setPrtID(String prtID) {
		this.prtID = prtID;
	}
	
	
	@Override
	public String toString() {
		return "StockList [stockList=" + stockList + ", percentAlloc=" + percentAlloc + ", prtID=" + prtID + "]";
	}
	
	

}
