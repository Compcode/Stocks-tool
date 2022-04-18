package flask;

import java.util.ArrayList;
import java.util.Iterator;

public class MappKeyValue {
	
	private ArrayList<String> key;
	private ArrayList<Double> value;
	
	
	public MappKeyValue(ArrayList<String> key, ArrayList<Double> value) {
		super();
		this.key = key;
		this.value = value;
	}


	public ArrayList<String> getKey() {
		return key;
	}


	public void setKey(ArrayList<String> key) {
		this.key = key;
	}


	public ArrayList<Double> getValue() {
		return value;
	}


	public void setValue(ArrayList<Double> value) {
		this.value = value;
	}


	@Override
	public String toString() {
		
		Iterator<String> k =key.iterator();
		Iterator<Double> v = value.iterator();
		String tt="";
		
		while(k.hasNext() && v.hasNext()) {
			
			tt=tt+"\""+k.next()+"\""+":"+"\""+v.next()+"\""+", ";
			
		}
		
		return tt;
	}
	
	
	
	
	



}
