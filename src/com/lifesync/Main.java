package com.lifesync;

import com.lifesync.exception.KimlikDogrulamaHatasi;
import com.lifesync.gui.MainFrame;
import com.lifesync.repository.VeriDeposu;
import com.lifesync.service.AuthService;


public class Main {
    public static void main(String[] args) {
        System.out.println("--- LİFESYNC VERİTABANI BAŞLATICISI (SEEDER) ---\n");

        // 1. Sistemi başlat (SQLite ve dosyaları yükler)
        VeriDeposu.sistemiBaslat();
        
        int mevcutKullaniciSayisi = VeriDeposu.getKullaniciListesi().size();
        System.out.println("Mevcut Kullanıcı Sayısı: " + mevcutKullaniciSayisi);

        // Eğer sistemde kullanıcı varsa, seed işlemine gerek yoktur
        if (mevcutKullaniciSayisi > 0) {
            System.out.println("Veritabanı zaten dolu. Başlatıcı işlem yapmadan çıkıyor.");
            System.out.println("Uygulamayı başlatmak için lütfen 'com.lifesync.gui.MainFrame' sınıfını çalıştırın.");
            return;
        }

        System.out.println("\nVeritabanı boş. Örnek veriler (Dummy Data) oluşturuluyor...\n");

        try {
            // Örnek Antrenörler
            AuthService.kayitOl("ANTRENOR", VeriDeposu.yeniKullaniciIdUret(), "Fatih Terim", "fatih@koc.com", "1234", 0, 0, null);
            AuthService.kayitOl("ANTRENOR", VeriDeposu.yeniKullaniciIdUret(), "Jose Mourinho", "jose@koc.com", "1234", 0, 0, null);
            System.out.println("[+] Antrenörler oluşturuldu.");

            // Örnek Amatör Sporcular
            AuthService.kayitOl("AMATOR", VeriDeposu.yeniKullaniciIdUret(), "Ahmet Yılmaz", "ahmet@mail.com", "pass123", 180.5, 75.0, "Orta Seviye");
            AuthService.kayitOl("AMATOR", VeriDeposu.yeniKullaniciIdUret(), "Ayşe Demir", "ayse@mail.com", "pass123", 165.0, 60.0, "Başlangıç");
            System.out.println("[+] Amatör Sporcular oluşturuldu.");
            
            // Örnek Profesyonel Sporcular
            AuthService.kayitOl("PROFESYONEL", VeriDeposu.yeniKullaniciIdUret(), "Mete Gazoz", "mete@mail.com", "pass123", 185.0, 80.0, "Okçuluk");
            System.out.println("[+] Profesyonel Sporcular oluşturuldu.");

            System.out.println("\n--- SİSTEM KAPANIŞI ---");
            AuthService.cikisYap();
            VeriDeposu.sistemiKapat();
            
            System.out.println("\nBaşlatıcı (Seeder) işlemi BAŞARIYLA TAMAMLANDI.");
            System.out.println("Lütfen uygulamanın masaüstü arayüzünü (GUI) açmak için 'com.lifesync.gui.MainFrame' sınıfını çalıştırın.");

        } catch (KimlikDogrulamaHatasi e) {
            System.err.println("Veri oluşturma sırasında hata: " + e.getMessage());
        }
    }
}
