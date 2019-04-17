package model;

import java.util.ArrayList;

public class CostosApp {
	
	private ArrayList<Presupuesto> tasascif;
	private double cifaplicado;
	private double cifreal;
	private double variacion;
	
	public CostosApp() {
		tasascif = new ArrayList<Presupuesto>();
		cifaplicado = 0;
		cifreal = 0;
		variacion =0; 
	}

}
