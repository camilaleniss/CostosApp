package model;

import java.text.DecimalFormat;

public class Presupuesto {
	
	private double cifpresupuestado;
	private BaseAsignacion baseasignacion;
	
	public Presupuesto(double cifpresupuestado, BaseAsignacion baseasignacion) {
		this.cifpresupuestado=cifpresupuestado;
		this.baseasignacion=baseasignacion;
	}
	
	public double getCifpresupuestado() {
		return cifpresupuestado;
	}

	public void setCifpresupuestado(double cifpresupuestado) {
		this.cifpresupuestado = cifpresupuestado;
	}

	public BaseAsignacion getBaseasignacion() {
		return baseasignacion;
	}

	public void setBaseasignacion(BaseAsignacion baseasignacion) {
		this.baseasignacion = baseasignacion;
	}
	
	public double getTasaCif() {
		return cifpresupuestado/baseasignacion.getUnidades();
	}
	
	public String toString() {
		
		DecimalFormat formato1 = new DecimalFormat("#.0");
		return "$"+formato1.format(getTasaCif())+"/"+baseasignacion.getNombre();
	}
	
}