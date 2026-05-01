package com.lifesync;


import java.util.ArrayList;
import java.util.List;

public abstract class Sporcu extends Kullanici {
    private double boy;
    private double kilo;
    private Hedef hedef;
    private List<Antrenman> antrenmanListesi;
    private List<GunlukTakip> gunlukTakipListesi;

    public Sporcu(int kullaniciId, String adSoyad, String email, String sifreHash, double boy, double kilo) {
        super(kullaniciId, adSoyad, email, sifreHash);
        this.boy = boy;
        this.kilo = kilo;
        this.antrenmanListesi = new ArrayList<>();
        this.gunlukTakipListesi = new ArrayList<>();
    }

    // Getters ve Setters (ValidationService'in hata vermemesi için gerekli)
    public double getBoy() { return boy; }
    public void setBoy(double boy) { this.boy = boy; }
    
    public double getKilo() { return kilo; }
    public void setKilo(double kilo) { this.kilo = kilo; }

    public void antrenmanEkle(Antrenman antrenman) {
        antrenmanListesi.add(antrenman);
    }

    public void gunlukTakipEkle(GunlukTakip takip) {
        gunlukTakipListesi.add(takip);
    }

    // POLİMORFİZM İÇİN SOYUT METOT: Her alt sınıf kendi seviyesine göre hesaplayacak
    public abstract double gunlukKaloriIhtiyaciHesapla();
    
    // POLİMORFİZM İÇİN SOYUT METOT: Her alt sınıf kendi özetini farklı dönecek
    public abstract String sporcuProfilOzeti();
}
