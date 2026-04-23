package com.lifesync;
public class Besin {
	private int besinId;

	private String besinAdi;

	private double kalori;
	
	private double protein;

	private double karbonhidrat;

	private double yag;

	private double lif;

	//region Getters and Setters
	
	public int getBesinId() {
		return besinId;
	}

	public void setBesinId(int besinId) {
		this.besinId = besinId;
	}

	public String getBesinAdi() {
		return besinAdi;
	}

	public void setBesinAdi(String besinAdi) {
		this.besinAdi = besinAdi;
	}

	public double getKalori() {
		return kalori;
	}

	public void setKalori(double kalori) {
		this.kalori = kalori;
	}

	public double getProtein() {
		return protein;
	}

	public void setProtein(double protein) {
		this.protein = protein;
	}

	public double getKarbonhidrat() {
		return karbonhidrat;
	}

	public void setKarbonhidrat(double karbonhidrat) {
		this.karbonhidrat = karbonhidrat;
	}

	public double getYag() {
		return yag;
	}

	public void setYag(double yag) {
		this.yag = yag;
	}

	public double getLif() {
		return lif;
	}

	public void setLif(double lif) {
		this.lif = lif;
	}

	//endregion
	
	public String besinBilgisiGetir()
	{
		return "BILGI";
	}
	
}
