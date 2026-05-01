package com.lifesync;

import java.time.LocalDate;
import java.util.*;


public class GunlukTakip implements IOzetlenebilir{
  
	private int takipId;
	
	private LocalDate tarih;
	
	private double suMiktari;
	
	private List<Supplement> supplementListesi;
	
	private String gunlukNot;
	
	Birim litre= Birim.LITRE;
	Birim adet= Birim.ADET;
	
	GunlukTakip(int takipId)
	{
		this.takipId=takipId;
		this.suMiktari=0;
		this.tarih = LocalDate.now();
		this.supplementListesi=new ArrayList<>();
	}
	
	
	//region Getters and Settters

	public int getTakipId() {
		return takipId;
	}

	public void setTakipId(int takipId) {
		this.takipId = takipId;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}

	public double getSuMiktari() {
		return suMiktari;
	}

	public void setSuMiktari(double suMiktari) {
		this.suMiktari = suMiktari;
	}

	public List<Supplement> getSupplementListesi() {
		return supplementListesi;
	}

	public void setSupplementListesi(List<Supplement> supplementListesi) {
		this.supplementListesi = supplementListesi;
	}

	public String getGunlukNot() {
		return gunlukNot;
	}

	public void setGunlukNot(String gunlukNot) {
		this.gunlukNot = gunlukNot;
	}
	
	//endregion
	
	public void gunlukNotEkle(String not)
	{
		this.gunlukNot=not;
	}
	
	public void suEkle(double miktar) // litre cinsinden girin.
	{
		this.suMiktari+=miktar;
	}
	
	public void supplementEkle(Supplement supplement)
	{
		supplementListesi.add(supplement);
	}
	
	public String hedefDurumuGetir(Sporcu sporcu) //Sporcu bilgilerine göre gelecek olan su ihtiyacı karşılanmış mı onu hesaplayacağım
	{
		if (sporcu == null) {
            return "Hata: Sporcu bilgisi geçersiz.";
        }
        
        Hedef sporcuHedefi = sporcu.getHedef();
        
        if (sporcuHedefi == null) {
            return "Hata: Bu sporcunun henüz belirlenmiş bir hedefi yok.";
        }

        double hedeflenenSu = sporcuHedefi.getHedefSu();
        double fark = hedeflenenSu - this.suMiktari;

        if (fark <= 0) {
            return "Başarılı: Günlük su içme hedefi (" + hedeflenenSu + "L) karşılandı!";
        } else {
            return "Eksik: Günlük su hedefine ulaşmak için " + fark + "L daha su içmelisiniz.";
        }
	}
	
	public String ozetGetir()
	{
		String ozet =   tarih + " Tarihindeki Ozet: " + 
						suMiktari + " " + litre + " su içildi. " + 
						supplementListesi.size() + " " + adet + " supplement kullanildi.";
		
				if(gunlukNot != null && !gunlukNot.isEmpty())
				{
					ozet += " Gunun Notu: " + gunlukNot;
				}
				else
				{
					ozet += " Gunun Notu: " + "Bu gune ait bir not dusulmedi.";
				}
				
				
		return ozet;		
		
	}
}
