package com.lifesync;

public abstract class Kullanici {
	private int kullaniciID;
	private String adSoyad;
	private String email;
	private String sifreHash;
	
	public Kullanici() {}
	
	public Kullanici(int kullaniciID, String adSoyad, String email, String sifreHash) {
		this.kullaniciID = kullaniciID;
		this.adSoyad = adSoyad;
		this.email = email;
		this.sifreHash = sifreHash;
	}
	
	public void profilGuncelle(String adSoyad, String email) {
		this.adSoyad = adSoyad;
		this.email = email;
	}
	
	public int getKullaniciID() { return kullaniciID; }
	public void setKullaniciID(int kullaniciID) { this.kullaniciID = kullaniciID; }
	public String getAdSoyad() { return adSoyad; }
	public void setAdSoyad(String adSoyad) { this.adSoyad = adSoyad; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getSifreHash() { return sifreHash; }
	public void setSifreHash(String sifreHash) { this.sifreHash = sifreHash; }
}
