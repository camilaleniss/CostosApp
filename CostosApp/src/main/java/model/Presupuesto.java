package model;

import java.util.HashMap;

public class Presupuesto {

	private HashMap<Double, BaseAsignacion> presupuestos;
	
	public Presupuesto() {
		presupuestos = new HashMap<Double, BaseAsignacion>();
	}
}
