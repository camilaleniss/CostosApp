package modelo;

public class BaseAsignacion {
	
	private int baseAsignacion;
	private String UnidadesBase;
	
	public BaseAsignacion(int baseAsignacion, String unidadesBase) {
		this.baseAsignacion = baseAsignacion;
		UnidadesBase = unidadesBase;
	}

	public int getBaseAsignacion() {
		return baseAsignacion;
	}

	public void setBaseAsignacion(int baseAsignacion) {
		this.baseAsignacion = baseAsignacion;
	}

	public String getUnidadesBase() {
		return UnidadesBase;
	}

	public void setUnidadesBase(String unidadesBase) {
		UnidadesBase = unidadesBase;
	}

	
	
}
