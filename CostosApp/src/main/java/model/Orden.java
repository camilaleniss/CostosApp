package model;

import java.util.HashMap;

public class Orden {
	
	private double md;
	private double mod;
	private HashMap<BaseAsignacion, Double> cif;
	
	public Orden() {
		md = 0;
		mod= 0;
		cif = new HashMap<BaseAsignacion, Double>();
		
	}
	
	
	

}
