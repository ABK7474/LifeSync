package com.lifesync;

public class ProfesyonelSporcu extends Sporcu {
    private String yaristigiAlan; // Örn: Open, Klasik Fizik, Men's Fizik

    public ProfesyonelSporcu(int kullaniciId, String adSoyad, String email, String sifre, double boy, double kilo, String yaristigiAlan) {
        super(kullaniciId, adSoyad, email, sifre, boy, kilo);
        this.yaristigiAlan = yaristigiAlan;
    }

    public String getYaristigiAlan() {  return yaristigiAlan; }
    

    @Override
    public double gunlukKaloriIhtiyaciHesapla() {
        // Profesyonel vücut geliştiriciler için yarışma/büyüme dönemlerine özel yüksek yoğunluklu kalori hesabı
        return getKilo() * 24 * 1.8; 
    }

    @Override
    public String sporcuProfilOzeti() {
        return "Profesyonel Sporcu: " + getAdSoyad() + " | Yarıştığı Alan: " + yaristigiAlan;
    }

    @Override
    public String toString() {
        return "ProfesyonelSporcu{ad='" + getAdSoyad() + "', alan='" + yaristigiAlan + "', boy=" + getBoy() + ", kilo=" + getKilo() + "}";
    }
}