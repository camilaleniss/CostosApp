package modelo;

public class Orden {
	
	private String numero;
	private double md;
	private double mod;
	private double cif;
	
	public Orden(String numero, double md, double mod, double cif) {
		this.numero=numero;
		this.md= md;
		this.mod=mod;
		this.cif=cif;
		
	}
	
	public String getNum() {
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
	
	public void setCif(double cif, double tasa) {
		this.cif = cif*tasa;
	}
	
	public double totalCif() {
		return md+mod+cif;
	}


	@Override
	public String toString() {
		return numero + ";$" + md + ";$" + mod + ";$" + String.format("%.2f", cif) + ";$" + String.format("%.2f", totalCif());
	}
	
}
