package model;

public class Presupuesto {
	
	private double cifpresupuestado;
	private BaseAsignacion baseasignacion;
	private int porcentaje;
	
	public Presupuesto(double cifpresupuestado, BaseAsignacion baseasignacion) {
		this.cifpresupuestado=cifpresupuestado;
		this.baseasignacion=baseasignacion;
		porcentaje=0;
	}
	
	public Presupuesto(double cifpresupuestado, BaseAsignacion baseasignacion, int porcentaje) {
		this.cifpresupuestado=cifpresupuestado;
		this.baseasignacion=baseasignacion;
		this.porcentaje=porcentaje;
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
	
	public int getPorcentaje() {
		return porcentaje;
	}
	
	
	
	
}
