package model;

public class BaseAsignacion {
	
	private int unidades;
	private String nombre;
	
	public BaseAsignacion (int unidades, String nombre) {
		this.unidades=unidades;
		this.nombre=nombre;
	}

	public int getUnidades() {
		return unidades;
	}
	
	public String getNombre() {
		return nombre;
	}
}
