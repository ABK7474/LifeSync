package com.lifesync;

import java.time.LocalDate;
import java.util.*;

public class GunlukTakip implements IOzetlenebilir{
  
	private int takipId;
	
	private LocalDate tarih;
	
	private double suMiktari;
	
	private List<Supplement> supplementListesi;
	
	private String gunlukNot;
	
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
	
	public void suEkle(double miktar)
	{
			
	}
	
	public void supplementEkle(Supplement supplement)
	{
		
	}
	
	public String hedefDurumuGetir()
	{
		return "DURUM";
	}
	
	public String ozetGetir()
	{
		return "OZET";
		
	}
}
