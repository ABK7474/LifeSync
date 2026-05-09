package com.lifesync;

public class Antrenor extends Kullanici {

    public Antrenor(int kullaniciId, String adSoyad, String email, String sifre) {
        super(kullaniciId, adSoyad, email, sifre);
    }

    // Mantıksal operasyon: Sporcunun verilerini analiz edip string formatında rapor döner.
    public String ilerlemeIncele(Sporcu sporcu) {
        if (sporcu != null) {
            // İleride veritabanından çekilecek verilerle doldurulacak iş mantığı alanı
            return "Sporcu: " + sporcu.getAdSoyad() + " için ilerleme analizi tamamlandı.";
        }
        return "Geçersiz sporcu referansı.";
    }

    @Override
    public String toString() {
        return "Antrenor{ad='" + getAdSoyad() + "'}";
    }
}
