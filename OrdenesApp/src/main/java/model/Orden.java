package model;

public class Orden {
	
	private double md;
	private double mod;
	private double cif;
	
	public Orden() {
		md = 0;
		mod= 0;
		cif = 0;
		
	}
	
	public double getMd() {
		return md;
	}
	
	public double getMod() {
		return mod;
	}
	
	public double getCif(){
		return cif;
	}

	public void setMd(double md) {
		this.md = md;
	}

	public void setMod(double mod) {
		this.mod = mod;
	}

	public void setCif(double cif) {
		this.cif = cif;
	}
	
}
