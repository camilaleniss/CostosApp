package model;

import java.util.ArrayList;
import java.util.HashMap;

public class CostosApp {
	/**
	 * ArrayList que contiene las tasascif presupuestadas
	 * La sumatoria del porcentaje de cada tasacif debe dar 100
	 */
	private ArrayList<Presupuesto> tasascif;
	/**
	 * Cif aplicado del periodo 
	 */
	private double cifaplicado;
	
	private ArrayList<Double> cifreal;
	
	private HashMap<Integer, Orden> ordenes;
	
	private double variacion;
	
	public CostosApp() {
		tasascif = new ArrayList<Presupuesto>();
		cifaplicado = 0;
		cifreal = new ArrayList<Double>();
		variacion =0; 
		ordenes = new HashMap<Integer, Orden>();
	}
	
	public ArrayList<Presupuesto> getTasasCif(){
		return tasascif;
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
		BaseAsignacion bass= new BaseAsignacion(unidades, base);
		tasascif.add(new Presupuesto(cifpp, bass));
	}
	
	public Orden buscarOrden(int id) {
		return ordenes.get(id);
	}
	
	public void addOrden(int id) {
		ordenes.put(id, new Orden());
		Orden orden = ordenes.get(id);
		orden.setCif(tasascif);
	}
	
	public void modifyMd(int id, double md) {
		Orden orden = buscarOrden(id);
		orden.setMd(md);
	}
	
	public void modifyMod(int id, double mod) {
		Orden orden = buscarOrden(id);
		orden.setMd(mod);
	}
	
	public void modifyCIF(int id, BaseAsignacion ba, double value) {
		Orden orden = buscarOrden(id);
		orden.addCif(ba, value);
	}
	
	public void calcularCifAplicado() {
		cifaplicado=0;
		ArrayList<Orden> ordeness = (ArrayList<Orden>) ordenes.values();
		for (int i = 0; i<ordeness.size(); i++) {
			cifaplicado+=ordeness.get(i).getCIFAplicado();
		}
	}
	

}
