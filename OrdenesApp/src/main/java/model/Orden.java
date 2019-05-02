package model;

public class Orden {
	
	private int numero;
	private double md;
	private double mod;
	private double cif;
	
	public Orden(int numero, double md, double mod, double cif) {
		this.numero=numero;
		this.md= md;
		this.mod=mod;
		this.cif=cif;
		
	}
	
	public int getNum() {
		return numero;
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
	
	@Override
	public String toString() {
		return "Orden #"+numero+"\nMaterial directo: "+md+"\nMano de obra directa:"+mod+"\nCIF:"+cif;
	}
	
}
