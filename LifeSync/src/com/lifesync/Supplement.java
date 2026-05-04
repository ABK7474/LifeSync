package com.lifesync;
public class Supplement implements IBilgiGetirebilir{
  
	private int supplementId;
	private String supplementAdi;
	private double miktar;
	private Birim birim;
	private KullanimZamani kullanimZamani;

	public Supplement(int supplementId,String supplementAdi,double miktar,Birim birim,KullanimZamani kullanimZamani)
	{
		this.supplementId=supplementId;
		this.supplementAdi=supplementAdi;
		this.miktar=miktar;
		this.birim=birim;
		this.kullanimZamani=kullanimZamani;
	}
	
	//region Getters and Setters
	public int getSupplementId() { return supplementId; }
	public void setSupplementId(int supplementId) { this.supplementId = supplementId; }
	public String getSupplementAdi() { return supplementAdi; }
	public void setSupplementAdi(String supplementAdi) { this.supplementAdi = supplementAdi; }
	public double getMiktar() { return miktar; }
	public void setMiktar(double miktar) { this.miktar = miktar; }
	public Birim getBirim() { return birim; }
	public void setBirim(Birim birim) { this.birim = birim; }
	public KullanimZamani getKullanimZamani() { return kullanimZamani; }
	public void setKullanimZamani(KullanimZamani kullanimZamani) { this.kullanimZamani = kullanimZamani; }
	//endregion
	
	public String bilgiGetir(){
		return  "Supplement Adi: " + supplementAdi +
				", Miktar :" + miktar + " " + birim +
				", Kullanim Zamani: " + kullanimZamani;
	}
}