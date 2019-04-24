package model;

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
	
	
	
	
}
