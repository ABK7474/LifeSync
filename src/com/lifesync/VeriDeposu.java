package com.lifesync;

import java.util.ArrayList;
import java.util.List;

public class VeriDeposu {
    // Sistemin bellek içi veri tablosu
    private static List<Kullanici> kullaniciListesi = new ArrayList<>();
    
    // Varsayılan kütüphaneler (Bunlar sabit olduğu için dosyaya yazmaya gerek yoktur)
    private static List<Besin> besinKutuphanesi = new ArrayList<>();
    private static List<Egzersiz> egzersizKutuphanesi = new ArrayList<>();
    private static List<Supplement> supplementKutuphanesi = new ArrayList<>();
    
    // Mesajlaşma sistemi (bellekte tutulur)
    private static List<Mesaj> mesajListesi = new ArrayList<>();
    private static int mesajSayaci = 0;

    // SİSTEM BAŞLATMA VE KAPATMA (KALICILIK MANTIĞI)
    public static void sistemiBaslat() {
        // 0. SQLite veritabanını ve tablolarını hazırla
        DatabaseManager.initDatabase();
        // 1. Kütüphaneleri doldur
        baslangicVerileriniYukle();
        // 2. Diskteki (varsa) eski kullanıcı kayıtlarını RAM'e çek
        kullaniciListesi = DatabaseManager.kullanicilariYukle();
        // 3. Kullanıcılara ait atanan antrenman programlarını RAM'e çek
        DatabaseManager.antrenmanlariYukle(kullaniciListesi);
        // 4. Antrenman-Egzersiz ilişkilerini RAM'e çek
        DatabaseManager.antrenmanEgzersizleriniYukle(kullaniciListesi);
        // 5. Öğün ve besin verilerini RAM'e çek
        DatabaseManager.ogunleriYukle(kullaniciListesi);
        // 6. Günlük takip verilerini RAM'e çek
        DatabaseManager.gunlukTakipleriYukle(kullaniciListesi);
        // 7. Hedefleri RAM'e çek
        DatabaseManager.hedefleriYukle(kullaniciListesi);
        // 8. Mesaj geçmişini RAM'e çek
        mesajListesi = DatabaseManager.mesajlariYukle();
        // Mesaj ID sayacını son ID'ye göre güncelle
        for(Mesaj m : mesajListesi) {
            if(m.getMesajId() > mesajSayaci) mesajSayaci = m.getMesajId();
        }
    }

    public static void sistemiKapat() {
        // Kullanıcı uygulamadan çıkış yaparken bellekteki son durumu diske yaz
        DatabaseManager.kullanicilariKaydet(kullaniciListesi);
        DatabaseManager.antrenmanlariKaydet(kullaniciListesi);
        DatabaseManager.antrenmanEgzersizleriniKaydet(kullaniciListesi);
        DatabaseManager.ogunleriKaydet(kullaniciListesi);
        DatabaseManager.gunlukTakipleriKaydet(kullaniciListesi);
        DatabaseManager.hedefleriKaydet(kullaniciListesi);
        DatabaseManager.mesajlariKaydet(mesajListesi);
    }

    // KULLANICI İŞLEMLERİ
    public static void kullaniciEkle(Kullanici kullanici) {
        kullaniciListesi.add(kullanici);
        // Her yeni kullanıcı eklendiğinde veri kaybını önlemek için anında dosyaya yazılabilir (Tercihe bağlı)
        DatabaseManager.kullanicilariKaydet(kullaniciListesi); 
        DatabaseManager.antrenmanlariKaydet(kullaniciListesi);
    }
    
    public static boolean kullaniciSil(Kullanici kullanici) {
        if (kullanici == null) return false;
        
        // 1. Veritabanından sil
        boolean dbBasarili = DatabaseManager.kullaniciSil(kullanici.getKullaniciID());
        
        // 2. RAM'den sil
        if (dbBasarili) {
            kullaniciListesi.remove(kullanici);
            
            // Mesajları RAM'den temizle
            mesajListesi.removeIf(m -> m.getGonderenId() == kullanici.getKullaniciID() || m.getAliciId() == kullanici.getKullaniciID());
            return true;
        }
        return false;
    }

    public static List<Kullanici> getKullaniciListesi() {
        return kullaniciListesi;
    }

    public static Kullanici emailIleKullaniciBul(String email) {
        for (Kullanici k : kullaniciListesi) {
            if (k.getEmail().equalsIgnoreCase(email)) {
                return k;
            }
        }
        return null;
    }

    // Antrenman eklendiğinde veya güncellendiğinde dosyaya anında yansıtır
    public static void antrenmanlariDiskeYaz() {
        DatabaseManager.antrenmanlariKaydet(kullaniciListesi);
        DatabaseManager.antrenmanEgzersizleriniKaydet(kullaniciListesi);
    }

    // Öğün verilerini anında diske yazar
    public static void ogunleriDiskeYaz() {
        DatabaseManager.ogunleriKaydet(kullaniciListesi);
    }

    // Günlük takip verilerini anında diske yazar
    public static void gunlukTakipleriDiskeYaz() {
        DatabaseManager.gunlukTakipleriKaydet(kullaniciListesi);
    }

    // Hedefleri anında diske yazar
    public static void hedefleriDiskeYaz() {
        DatabaseManager.hedefleriKaydet(kullaniciListesi);
    }

    // Benzersiz kullanıcı ID'si üretir (MAX(id)+1 ile çakışma önlenir)
    public static int yeniKullaniciIdUret() {
        // Önce DB'den kontrol et, sonra RAM'deki listeyle karşılaştır
        int dbId = DatabaseManager.yeniKullaniciIdUret();
        int ramId = 1;
        for (Kullanici k : kullaniciListesi) {
            if (k.getKullaniciID() >= ramId) ramId = k.getKullaniciID() + 1;
        }
        return Math.max(dbId, ramId);
    }

    // Benzersiz mesaj ID'si üretir
    public static int yeniMesajId() {
        return ++mesajSayaci;
    }

    // Mesaj ekler ve aninda diske yazar
    public static void mesajEkle(Mesaj mesaj) {
        mesajListesi.add(mesaj);
        DatabaseManager.mesajlariKaydet(mesajListesi);
    }

    // KÜTÜPHANE İŞLEMLERİ (Öncekiyle Aynı)
    private static void baslangicVerileriniYukle() {
        if (besinKutuphanesi.isEmpty()) {
            besinKutuphanesi.add(new Besin(1, "Yulaf", 389, 16.9, 66.3, 6.9, 10.6));
            besinKutuphanesi.add(new Besin(2, "Tavuk Göğsü", 165, 31.0, 0.0, 3.6, 0.0));
            besinKutuphanesi.add(new Besin(3, "Pirinç", 130, 2.7, 28.0, 0.3, 0.4));
        }
        
        if (egzersizKutuphanesi.isEmpty()) {
            egzersizKutuphanesi.add(new Egzersiz(1, "Bench Press", 4, 10, 60.0));
            egzersizKutuphanesi.add(new Egzersiz(2, "Squat", 4, 8, 80.0));
            egzersizKutuphanesi.add(new Egzersiz(3, "Deadlift", 3, 5, 100.0));
        }
        
        if (supplementKutuphanesi.isEmpty()) {
            supplementKutuphanesi.add(new Supplement(1, "Whey Protein", 30.0, Birim.GRAM, KullanimZamani.ANTRENMAN_SONRASI));
            supplementKutuphanesi.add(new Supplement(2, "Kreatin", 5.0, Birim.GRAM, KullanimZamani.ANTRENMAN_ONCESI));
            supplementKutuphanesi.add(new Supplement(3, "BCAA", 10.0, Birim.GRAM, KullanimZamani.ANTRENMAN_ONCESI));
        }
    }

    public static List<Besin> getBesinKutuphanesi() { return besinKutuphanesi; }
    public static List<Egzersiz> getEgzersizKutuphanesi() { return egzersizKutuphanesi; }
    public static List<Supplement> getSupplementKutuphanesi() { return supplementKutuphanesi; }
    
    // IBilgiGetirebilir arayüzü ile polimorfizm kullanılarak tüm katalogları tek listede döndüren metod
    public static List<IBilgiGetirebilir> tumKataloguGetir() {
        List<IBilgiGetirebilir> tumKatalog = new ArrayList<>();
        tumKatalog.addAll(besinKutuphanesi);
        tumKatalog.addAll(egzersizKutuphanesi);
        tumKatalog.addAll(supplementKutuphanesi);
        return tumKatalog;
    }

    // MESAJ İŞLEMLERİ
    


    /** Belirli bir kullanıcıya gönderilen mesajları getirir. */
    public static List<Mesaj> kullaniciyaGelenMesajlar(int kullaniciId) {
        List<Mesaj> sonuc = new ArrayList<>();
        for (Mesaj m : mesajListesi) {
            if (m.getAliciId() == kullaniciId) {
                sonuc.add(m);
            }
        }
        return sonuc;
    }

    /** Belirli bir kullanıcının gönderdiği mesajları getirir. */
    public static List<Mesaj> kullanicidanGelenMesajlar(int kullaniciId) {
        List<Mesaj> sonuc = new ArrayList<>();
        for (Mesaj m : mesajListesi) {
            if (m.getGonderenId() == kullaniciId) {
                sonuc.add(m);
            }
        }
        return sonuc;
    }

    /** İki kullanıcı arasındaki tüm mesajları kronolojik sırayla getirir. */
    public static List<Mesaj> ikiKisiArasindakiMesajlar(int kisi1Id, int kisi2Id) {
        List<Mesaj> sonuc = new ArrayList<>();
        for (Mesaj m : mesajListesi) {
            if ((m.getGonderenId() == kisi1Id && m.getAliciId() == kisi2Id) ||
                (m.getGonderenId() == kisi2Id && m.getAliciId() == kisi1Id)) {
                sonuc.add(m);
            }
        }
        return sonuc;
    }
}