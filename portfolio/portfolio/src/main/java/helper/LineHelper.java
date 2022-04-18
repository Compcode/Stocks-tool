package helper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LineHelper {
	private List<Double> monteCarlo;
	private List<Double> dl;
	private List<Double> dynamicDl;
	private List<Double> nifty50;

	private List<Date> dt;
	
	public List<Double> getMonteCarlo() {
		return monteCarlo;
	}
	public void setMonteCarlo(List<Double> monteCarlo) {
		this.monteCarlo = monteCarlo;
	}
	public List<Double> getDl() {
		return dl;
	}
	public void setDl(List<Double> dl) {
		this.dl = dl;
	}
	public List<Double> getDynamicDl() {
		return dynamicDl;
	}
	public void setDynamicDl(List<Double> dynamicDl) {
		this.dynamicDl = dynamicDl;
	}
	public List<Date> getDt() {
		return dt;
	}
	public void setDt(List<Date> dt) {
		this.dt = dt;
	}
	public List<Double> getNifty50() {
		return nifty50;
	}
	public void setNifty50(List<Double> nifty50) {
		this.nifty50 = nifty50;
	}
	@Override
	public String toString() {
		return "LineHelper [monteCarlo=" + monteCarlo + ", dl=" + dl + ", dynamicDl=" + dynamicDl + ", nifty50="
				+ nifty50 + ", dt=" + dt + "]";
	}
	
	

	
	
	
	
	
	
	
	

}
