package model;

import java.util.ArrayList;

public class Orden {
	
	private double md;
	private double mod;
	private ArrayList<Presupuesto> cif;
	
	public Orden() {
		md = 0;
		mod= 0;
		cif = new ArrayList<Presupuesto>();
		
	}
	
	public double getMd() {
		return md;
	}
	
	public double getMod() {
		return mod;
	}
	
	public ArrayList<Presupuesto> getCif(){
		return cif;
	}

	public void setMd(double md) {
		this.md = md;
	}

	public void setMod(double mod) {
		this.mod = mod;
	}

	public void setCif(ArrayList<Presupuesto> cif) {
		this.cif = cif;
	}
	
	public Presupuesto buscarCIF(BaseAsignacion bs){
		Presupuesto pp = new Presupuesto(0, null);
		boolean ya=false;
		for (int i = 0; i<cif.size() && !ya; i++) {
			if (cif.get(i).getBaseasignacion()==bs) {
				pp = cif.get(i);
				ya=true;
			}
		}
		return (ya) ? pp : null;
	}
	
	
	public boolean addCif(BaseAsignacion bs, double valor) {
		Presupuesto pp = buscarCIF(bs);
		if(pp!=null) {
			pp.setCifpresupuestado(valor);
			return true;
		}else {
			return false;
		}
	}
	
	public double getCIFAplicado() {
		return 0;
	}
	
}
