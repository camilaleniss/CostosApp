package model;

import java.util.ArrayList;
import java.util.HashMap;

public class CostosApp {
	/**
	 * Presupuesto del periodo
	 */
	private Presupuesto tasacif;
	/**
	 * Cif aplicado del periodo 
	 */
	private double cifaplicado;
	
	private ArrayList<Double> cifreal;
	
	private ArrayList<Orden> ordenes;
	
	private double variacion;
	
	public CostosApp() {
		cifaplicado = 0;
		cifreal = new ArrayList<Double>();
		variacion =0; 
		ordenes = new ArrayList<Orden>();
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
	
	public ArrayList<Orden> getOrdenes(){
		return ordenes;
	}
	
	public void addPresupuesto(double cifpp, int unidades, String base) {
		tasacif = new Presupuesto (cifpp, new BaseAsignacion(unidades, base));
	}
	
	public Orden buscarOrden(int id) {
		return ordenes.get(id);
	}
	
	public void addOrden(int id, double md, double mod, double cif) {
		ordenes.add(new Orden(id, md, mod, cif));
		
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

		for (int i = 0; i<ordenes.size(); i++) {
			cifaplicado+=ordenes.get(i).getCif()*tasacif.getTasaCif();
		}
	}
	
	public void addCIFReal(double real) {
		cifreal.add(real);
	}
	
	public double calcularCIFReal() {
		double real=0;
		for (int i = 0; i<cifreal.size(); i++) {
			real+=cifreal.get(i);
		}
		return real;
	}
	
	public void calcularVariacion() {
		calcularCifAplicado();
		variacion = cifaplicado-calcularCIFReal();
	}
	
	public ArrayList<String> toArrayListOrdenes() {
		ArrayList<String> lista = new ArrayList<String>();
		for (int i=0; i<ordenes.size(); i++) {
			lista.add(ordenes.get(i).toString());
		}
		return lista;
	}
	

}