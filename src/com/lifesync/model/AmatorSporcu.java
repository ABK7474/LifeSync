package com.lifesync.model;

import com.lifesync.model.*;
import com.lifesync.service.*;
import com.lifesync.repository.*;
import com.lifesync.factory.*;
import com.lifesync.exception.*;
import com.lifesync.interfaces.*;
import com.lifesync.util.*;



public class AmatorSporcu extends Sporcu {
    private String deneyimSeviyesi; // Örn: Başlangıç, Orta

    public AmatorSporcu(int kullaniciId, String adSoyad, String email, String sifre, double boy, double kilo, String deneyimSeviyesi) {
        super(kullaniciId, adSoyad, email, sifre, boy, kilo);
        this.deneyimSeviyesi = deneyimSeviyesi;
    }

    public String getDeneyimSeviyesi() { return deneyimSeviyesi; }

    @Override
    public double gunlukKaloriIhtiyaciHesapla() {
        // Amatör sporcular için temel bir kalori hesaplaması
        return getKilo() * 24 * 1.3; 
    }

    @Override
    public String sporcuProfilOzeti() {
        return "Amatör Sporcu: " + getAdSoyad() + " | Seviye: " + deneyimSeviyesi;
    }

    @Override
    public String toString() {
        return "AmatorSporcu{ad='" + getAdSoyad() + "', seviye='" + deneyimSeviyesi + "', boy=" + getBoy() + ", kilo=" + getKilo() + "}";
    }
}
