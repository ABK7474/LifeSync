package com.lifesync;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Antrenor extends Kullanici {
    private List<Sporcu> sporcuListesi;

    // sporcuListesi'ni boş bir ArrayList olarak başlatır nesne oluşturulduğunda
    public Antrenor(int kullaniciId, String adSoyad, String email, String sifreHash) {
        super(kullaniciId, adSoyad, email, sifreHash);
        this.sporcuListesi = new ArrayList<>();
    }

    // Encapsulation: Sporcu listesini değiştirilemez olarak döndürür.
    // Dışarıdan doğrudan ekleme/silme yapılamaz, bunun için sporcuEkle/sporcuSil kullanılmalıdır.
    public List<Sporcu> sporcuGoruntule() {
        return Collections.unmodifiableList(this.sporcuListesi);
    }

    // Encapsulation: Tek tek sporcu ekleme (setSporcuListesi yerine)
    public void sporcuEkle(Sporcu sporcu) {
        if (sporcu == null) {
            throw new IllegalArgumentException("Sporcu referansı boş olamaz.");
        }
        this.sporcuListesi.add(sporcu);
    }

    // Encapsulation: Tek tek sporcu silme
    public void sporcuSil(int kullaniciId) {
        sporcuListesi.removeIf(s -> s.getKullaniciID() == kullaniciId);
    }

    // Mantıksal operasyon: Belirtilen antrenmanı, belirtilen sporcunun antrenman listesine ekler.
    public void programAta(Sporcu sporcu, Antrenman antrenman) {
        if (sporcu != null && antrenman != null) {
            sporcu.antrenmanEkle(antrenman);
        } else {
            throw new IllegalArgumentException("Sporcu veya Antrenman referansı boş olamaz.");
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

    @Override
    public String toString() {
        return "Antrenor{ad='" + getAdSoyad() + "', sporcuSayisi=" + sporcuListesi.size() + "}";
    }
}
