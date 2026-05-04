package com.lifesync;

public class AmatorSporcu extends Sporcu {
    private String deneyimSeviyesi; // Örn: Başlangıç, Orta

    public AmatorSporcu(int kullaniciId, String adSoyad, String email, String sifreHash, double boy, double kilo, String deneyimSeviyesi) {
        super(kullaniciId, adSoyad, email, sifreHash, boy, kilo);
        this.deneyimSeviyesi = deneyimSeviyesi;
    }

    public String getDeneyimSeviyesi() { return deneyimSeviyesi; }
    public void setDeneyimSeviyesi(String deneyimSeviyesi) { this.deneyimSeviyesi = deneyimSeviyesi; }

    @Override
    public double gunlukKaloriIhtiyaciHesapla() {
        // Amatör sporcular için temel bir kalori hesaplaması (Polymorphism örneği)
        return getKilo() * 24 * 1.3; 
    }

    @Override
    public String sporcuProfilOzeti() {
        return "Amatör Sporcu: " + getAdSoyad() + " | Seviye: " + deneyimSeviyesi;
    }
}
