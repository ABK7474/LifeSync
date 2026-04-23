package com.lifesync;

import java.time.LocalDate;
import java.util.*;


public class Ogun implements IOzetlenebilir{
  
		private int ogunID;
		
		private LocalDate tarih;
		
		private OgunTuru ogunTuru;
		
		private List<Besin> besinListesi;
		
		//region Getters and Setters

		public int getOgunID() {
			return ogunID;
		}

		public void setOgunID(int ogunID) {
			this.ogunID = ogunID;
		}

		public LocalDate getTarih() {
			return tarih;
		}

		public void setTarih(LocalDate tarih) {
			this.tarih = tarih;
		}

		public OgunTuru getOgunTuru() {
			return ogunTuru;
		}

		public void setOgunTuru(OgunTuru ogunTuru) {
			this.ogunTuru = ogunTuru;
		}

		public List<Besin> getBesinListesi() {
			return besinListesi;
		}

		public void setBesinListesi(List<Besin> besinListesi) {
			this.besinListesi = besinListesi;
		}
		
		//endregion
		
		public void besinEkle(Besin besin)
		{
			
		}
		
		public void besinSil(int besinId)
		{
			
		}
		
		public double toplamKaloriHesapla()
		{
			return 0;
		}
		
		public Map<String, Double> makroHesapla()
		{
			Map<String,Double> ozet = new HashMap<String, Double>();
			return ozet;
		}
		
		public String ozetGetir()
		{
			return "OZET";
		}
		
		
		
		
}
