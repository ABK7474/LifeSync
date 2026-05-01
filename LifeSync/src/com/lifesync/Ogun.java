package com.lifesync;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ogun implements IOzetlenebilir{
  
		private int ogunId;
		private LocalDate tarih;
		private OgunTuru ogunTuru;
		private List<Besin> besinListesi;
		
		Ogun(int ogunId, OgunTuru ogunTuru)
		{
			this.ogunId = ogunId;
	        this.ogunTuru = ogunTuru;
	        this.tarih = LocalDate.now();
	        this.besinListesi = new ArrayList<>();
		}
		
		//region Getters and Setters
		public int getOgunID() { return ogunId; }
		public void setOgunID(int ogunID) { this.ogunId = ogunID; }
		public LocalDate getTarih() { return tarih; }
		public void setTarih(LocalDate tarih) { this.tarih = tarih; }
		public OgunTuru getOgunTuru() { return ogunTuru; }
		public void setOgunTuru(OgunTuru ogunTuru) { this.ogunTuru = ogunTuru; }
		public List<Besin> getBesinListesi() { return besinListesi; }
		public void setBesinListesi(List<Besin> besinListesi) { this.besinListesi = besinListesi; }
		//endregion
		
		public void besinEkle(Besin besin)
		{
			besinListesi.add(besin);
		}
		
		public void besinSil(int besinId)
		{
			besinListesi.removeIf(b -> b.getBesinId() == besinId);
		}
		
		public double toplamKaloriHesapla()
		{
			double toplam = 0;
			
			for(Besin besin:besinListesi)
			{
				toplam += besin.getKalori();
			}
			return toplam;
		}
		
		public Map<String, Double> makroHesapla()
		{
			Map<String,Double> ozet = new HashMap<String, Double>();
			
			double protein=0,karbonhidrat=0,yag=0;
		
			for(Besin besin:besinListesi)
			{
				protein+=besin.getProtein();
				karbonhidrat+=besin.getKarbonhidrat();
				yag+=besin.getYag();		
			}
			
			ozet.put("Protein",protein);
			ozet.put("Karbonhidrat",karbonhidrat);
			ozet.put("Yag",yag);
			
			return ozet;
		}
		
		public String ozetGetir()
		{
			return ogunTuru + " Ozeti: " + besinListesi.size() + " Cesit besin tuketildi. " + "Toplam Kalori: " + toplamKaloriHesapla();
		}
}
