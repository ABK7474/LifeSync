package com.lifesync.model;

public abstract class Kullanici {
	private int kullaniciID;
	private String adSoyad;
	private String email;
	private String sifre;
	
	public Kullanici(int kullaniciID, String adSoyad, String email, String sifre) {
		this.kullaniciID = kullaniciID;
		this.adSoyad = adSoyad;
		this.email = email;
		this.sifre = sifre;
	}
	
	public int getKullaniciID() { return kullaniciID; }
	
	public String getAdSoyad() { return adSoyad; }
	
	public String getEmail() { return email; }
	
	public String getSifre() { return sifre; }
	
	@Override
	public String toString() {
		return "Kullanici{id=" + kullaniciID + ", ad='" + adSoyad + "', email='" + email + "'}";
	}
}
