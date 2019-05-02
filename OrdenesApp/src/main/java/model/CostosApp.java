package model;

import java.util.ArrayList;
import java.util.HashMap;

public class CostosApp {
	
	private Presupuesto tasacif;
	/**
	 * Cif aplicado del periodo 
	 */
	private double cifaplicado;
	
	private ArrayList<Double> cifreal;
	
	private HashMap<Integer, Orden> ordenes;
	
	private double variacion;
	
	public CostosApp() {
		cifaplicado = 0;
		cifreal = new ArrayList<Double>();
		variacion =0; 
		ordenes = new HashMap<Integer, Orden>();
	}
	
	public Presupuesto getTasasCif(){
		return tasacif;
	}
	
	public double getCifaplicado() {
		return cifaplicado;
	}
	
	public ArrayList<Double> getCifreal() {
		return cifreal;
	}
	
	public double getVariacion() {
		return variacion;
	}
	
	public void addPresupuesto(double cifpp, int unidades, String base) {
		tasacif = new Presupuesto (cifpp, new BaseAsignacion(unidades, base));
	}
	
	public Orden buscarOrden(int id) {
		return ordenes.get(id);
	}
	
	public void addOrden(int id) {
		ordenes.put(id, new Orden());
	}
	
	public void modifyMd(int id, double md) {
		Orden orden = buscarOrden(id);
		orden.setMd(md);
	}
	
	public void modifyMod(int id, double mod) {
		Orden orden = buscarOrden(id);
		orden.setMd(mod);
	}
	
	public void modifyCIF(int id,  double value) {
		Orden orden = buscarOrden(id);
		orden.setCif(value);
	}
	
	public void calcularCifAplicado() {
		cifaplicado=0;
		ArrayList<Orden> ordeness = (ArrayList<Orden>) ordenes.values();
		
		for (int i = 0; i<ordeness.size(); i++) {
			cifaplicado+=ordeness.get(i).getCif()*tasacif.getTasaCif();
		}
	}
	

}