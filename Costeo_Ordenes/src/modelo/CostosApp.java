package modelo;

import java.util.ArrayList;

public class CostosApp {
	/**
	 * Presupuesto del periodo
	 */
	private Presupuesto tasacif;
	/**
	 * Cif aplicado del periodo 
	 */
	private double cifaplicado;
	
	private ArrayList<CIFReal> cifreal;
	
	private ArrayList<Orden> ordenes;
	
	private Orden ordenSeleccionada;
	
	private double variacion;
	
	private ArrayList<String> tipoBase;

	
	public CostosApp() {
		cifaplicado = 0;
		cifreal = new ArrayList<CIFReal>();
		variacion =0; 
		ordenes = new ArrayList<Orden>();
	}
	
	public Presupuesto getTasasCif(){
		return tasacif;
	}
	
	public double getCifaplicado() {
		return cifaplicado;
	}
	
	public ArrayList<CIFReal> getCifreal() {
		return cifreal;
	}
	
	public double getVariacion() {
		return variacion;
	}
	
	public ArrayList<Orden> getOrdenes(){
		return ordenes;
	}
	
	public Orden getOrdenSeleccionada() {
		return ordenSeleccionada;
	}
	
	public void setOrdenSeleccionada(Orden ordenSeleccionada) {
		this.ordenSeleccionada = ordenSeleccionada;
	}
	
	public void addPresupuesto(double cifpp, int unidades, String base) {
		tasacif = new Presupuesto (cifpp, new BaseAsignacion(unidades, base));
	}
	
	public Orden buscarOrden(int id) {
		return ordenes.get(id);
	}
	
	public void addOrden(int id, double md, double mod, double cif) {
		ordenes.add(new Orden(id, md, mod, cif*tasacif.getTasaCif()));
		
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
	
	public double calcularCifAplicado() {
		cifaplicado=0;

		for (int i = 0; i<ordenes.size(); i++) {
			cifaplicado+=ordenes.get(i).getCif();
		}
		return cifaplicado;
	}
	
	public void addCIFReal(String nombre, double real) {
		CIFReal nuevo=new CIFReal(nombre, real);
		cifreal.add(nuevo);
	}
	
	public ArrayList<String> getUnidadesBase() {
		return tipoBase;
	}

	public void unidadesTasa() {
		tipoBase=new ArrayList<String>();
		tipoBase.add(Presupuesto.H_MOD);
		tipoBase.add(Presupuesto.H_Maq);
	}
	
	
	public double calcularCIFReal() {
		double real=0;
		for (int i = 0; i<cifreal.size(); i++) {
			real+=cifreal.get(i).getValor();
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
	
	public void eliminarOrden(int nombreOrden) {
		boolean terminar=false;
		for (int i = 0; i < ordenes.size()&&!terminar; i++) {
			if(ordenes.get(i).getNum()==nombreOrden) {
				ordenes.remove(i);
				terminar=true;
			}
		}
		
	}

	public void editarOrden(int nombreOrden,Orden nueva) {
		boolean terminar=false;
		for (int i = 0; i < ordenes.size()&&!terminar; i++) {
			if(ordenes.get(i).getNum()==nombreOrden) {
				ordenes.get(i).setCif(nueva.getCif());
				ordenes.get(i).setMd(nueva.getMd());
				ordenes.get(i).setMod(nueva.getMod());
				terminar=true;
			}
		}
	}
	
	
	public void addUnidadesTasa(String unidades) {
		tipoBase.add(unidades);
	}
	
	public void eliminarTasa(String tasa) {
		boolean terminar=false;
		for (int i = 0; i < tipoBase.size()&&!terminar; i++) {
			if(tipoBase.get(i).equals(tasa)) {
				tipoBase.remove(i);
				terminar=true;
			}
		}
	}
	
	
	public void eliminarCifreal(String nombre) {
		boolean terminar=false;
		for (int i = 0; i < cifreal.size()&&!terminar; i++) {
			if(cifreal.get(i).getNombre().equals(nombre)) {
				
				cifreal.remove(i);
				terminar=true;
			}
		}
	}
	
	public void editarTasa() {
		
	}
}