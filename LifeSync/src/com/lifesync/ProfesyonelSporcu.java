package com.lifesync;

public class ProfesyonelSporcu extends Sporcu {
    private String lisansNumarasi;
    private String brans;

    public ProfesyonelSporcu(int kullaniciId, String adSoyad, String email, String sifreHash, double boy, double kilo, String lisansNumarasi, String brans) {
        super(kullaniciId, adSoyad, email, sifreHash, boy, kilo);
        this.lisansNumarasi = lisansNumarasi;
        this.brans = brans;
    }

    public String getLisansNumarasi() { return lisansNumarasi; }
    public void setLisansNumarasi(String lisansNumarasi) { this.lisansNumarasi = lisansNumarasi; }

    public String getBrans() { return brans; }
    public void setBrans(String brans) { this.brans = brans; }

    @Override
    public double gunlukKaloriIhtiyaciHesapla() {
        // Profesyonel sporcular (yüksek yoğunluklu çalıştıkları için) çarpan daha yüksektir
        return getKilo() * 24 * 1.8; 
    }

    @Override
    public String sporcuProfilOzeti() {
        return "Profesyonel Sporcu: " + getAdSoyad() + " | Branş: " + brans + " | Lisans No: " + lisansNumarasi;
    }
}