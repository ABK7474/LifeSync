package com.lifesync.model;

import com.lifesync.model.*;
import com.lifesync.service.*;
import com.lifesync.repository.*;
import com.lifesync.factory.*;
import com.lifesync.exception.*;
import com.lifesync.interfaces.*;
import com.lifesync.util.*;


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
