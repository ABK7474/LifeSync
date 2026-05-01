package com.lifesync;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
	public int getTakipId() { return takipId; }
	public void setTakipId(int takipId) { this.takipId = takipId; }
	public LocalDate getTarih() { return tarih; }
	public void setTarih(LocalDate tarih) { this.tarih = tarih; }
	public double getSuMiktari() { return suMiktari; }
	public void setSuMiktari(double suMiktari) { this.suMiktari = suMiktari; }
	public List<Supplement> getSupplementListesi() { return supplementListesi; }
	public void setSupplementListesi(List<Supplement> supplementListesi) { this.supplementListesi = supplementListesi; }
	public String getGunlukNot() { return gunlukNot; }
	public void setGunlukNot(String gunlukNot) { this.gunlukNot = gunlukNot; }
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
	
	public String hedefDurumuGetir(Sporcu sporcu) {
        if (sporcu == null || sporcu.getHedef() == null) {
            return "Hata: Sporcu bilgisi veya atanmış bir hedef bulunamadı.";
        }

        Hedef hedef = sporcu.getHedef();
        
        // 1. Su Hedefi Kontrolü (GunlukTakip sınıfının kendi verisi)
        double suFarki = hedef.getHedefSu() - this.suMiktari;
        String suDurumu = (suFarki <= 0) ? "Tamamlandı" : "Kalan " + suFarki + " L";

        // 2. Kalori ve Protein Hedefi Kontrolü (Sporcunun öğünlerinden çekilecek)
        double gunlukToplamKalori = 0;
        double gunlukToplamProtein = 0;

        // Sporcunun tüm öğünlerini gez, sadece GunlukTakip ile AYNI TARİHTE olanları topla
        for (Ogun ogun : sporcu.getOgunListesi()) {
            if (ogun.getTarih().equals(this.tarih)) {
                gunlukToplamKalori += ogun.toplamKaloriHesapla();
                
                // Ogun içindeki makroHesapla metodu Map dönüyor, oradan Proteini çekiyoruz
                if (ogun.makroHesapla().containsKey("Protein")) {
                    gunlukToplamProtein += ogun.makroHesapla().get("Protein");
                }
            }
        }

        double kaloriFarki = hedef.getHedefKalori() - gunlukToplamKalori;
        String kaloriDurumu = (kaloriFarki <= 0) ? "Aşıldı/Tamamlandı" : "Kalan " + kaloriFarki + " kcal";

        double proteinFarki = hedef.getHedefProtein() - gunlukToplamProtein;
        String proteinDurumu = (proteinFarki <= 0) ? "Tamamlandı" : "Kalan " + proteinFarki + " gr";

        // Tüm verileri bütünsel bir rapor olarak döndür
        return String.format("--- %s Tarihli Hedef Durumu ---\nSu: %s\nKalori: %s\nProtein: %s", 
                             this.tarih.toString(), suDurumu, kaloriDurumu, proteinDurumu);
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
