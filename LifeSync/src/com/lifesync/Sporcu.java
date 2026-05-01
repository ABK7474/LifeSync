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

    public double getBoy() { return boy; }
    public void setBoy(double boy) { this.boy = boy; }
    public double getKilo() { return kilo; }
    public void setKilo(double kilo) { this.kilo = kilo; }
    public Hedef getHedef() { return hedef; }
    public void setHedef(Hedef hedef) { this.hedef = hedef; }
    
    
    // Hedefin durumunu kontrol eden rasyonel iş mantığı
    public String genelHedefDurumuGetir() {
        if (this.hedef == null) {
            return "Uyarı: Sporcuya henüz bir hedef atanmamıştır.";
        }
        return "Güncel Hedef -> Kilo: " + hedef.getHedefKilo() + "kg | Günlük Kalori: " + hedef.getHedefKalori() + "kcal";
    }

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