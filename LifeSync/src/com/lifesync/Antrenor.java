package com.lifesync;

import java.util.ArrayList;
import java.util.List;

public class Antrenor extends Kullanici{
    private List<Sporcu> sporcuListesi;

     // sporcuListesi'ni boş bir ArrayList olarak başlatır nesne oluşturulduğunda
    public Antrenor(int kullaniciId, String adSoyad, String email, String sifreHash) {
        super(kullaniciId, adSoyad, email, sifreHash);
        this.sporcuListesi = new ArrayList<>();
    }

    // Antrenöre ait sporcuların listesini döndürür.
    public List<Sporcu> sporcuGoruntule() {
        return this.sporcuListesi;
    }

    // Mantıksal operasyon: Belirtilen antrenmanı, belirtilen sporcunun antrenman listesine ekler.
    public void programAta(Sporcu sporcu, Antrenman antrenman) {
        if (sporcu != null && antrenman != null) {
            sporcu.antrenmanEkle(antrenman);
        } else {
            throw new IllegalArgumentException("Sporcu veya Antrenman referansı null (boş) olamaz.");
        }
    }
    // Mantıksal operasyon: Sporcunun verilerini analiz edip string formatında rapor döner.
    public String ilerlemeIncele(Sporcu sporcu) {
        if (sporcu != null) {
            // İleride veritabanından çekilecek verilerle doldurulacak iş mantığı alanı
            return "Sporcu: " + sporcu.getAdSoyad() + " için ilerleme analizi tamamlandı.";
        }
        return "Geçersiz sporcu referansı.";
    }

    // Encapsulation (Kapsülleme) gereği private değişken için dışarıdan kontrollü erişim sağlayan Setter metodu
    public void setSporcuListesi(List<Sporcu> sporcuListesi) {
        this.sporcuListesi = sporcuListesi;
    }
}
