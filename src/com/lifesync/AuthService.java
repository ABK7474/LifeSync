package com.lifesync;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthService {

    // Sistemde o an oturumu açık olan kullanıcıyı (Session) bellekte tutar
    private static Kullanici aktifKullanici = null;

    /**
     * SHA-256 ile şifreyi hash'ler.
     * Gerçek dünya uygulamalarında BCrypt kullanılır; bu proje için SHA-256 yeterlidir.
     */
    private static String sifreHashle(String sifre) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(sifre.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 her JVM'de mevcuttur, buraya düşmez
            throw new RuntimeException("SHA-256 desteklenmiyor", e);
        }
    }

    /**
     * 1. KAYIT OL MANTIĞI
     * E-posta benzersizliğini kontrol eder, Factory ile nesne üretir ve veriyi dosyaya yazar.
     * Şifre SHA-256 ile hash'lenerek saklanır.
     */
    public static Kullanici kayitOl(String tip, int id, String adSoyad, String email, String sifre, double boy, double kilo, String ekstraBilgi) throws KimlikDogrulamaHatasi {
        
        // Mantıksal Kural 1: E-posta benzersizlik kontrolü (Aynı e-posta ile iki kişi kayıt olamaz)
        if (VeriDeposu.emailIleKullaniciBul(email) != null) {
            throw new KimlikDogrulamaHatasi("Hata: Bu e-posta adresi sistemde zaten kayıtlı.");
        }

        try {
            // Şifreyi hash'le
            String hashliSifre = sifreHashle(sifre);

            // Mantıksal Kural 2: Nesne üretimini Factory tasarım desenine devret
            Kullanici yeniKullanici = KullaniciFactory.kullaniciOlustur(tip, id, adSoyad, email, hashliSifre, boy, kilo, ekstraBilgi);
            
            // Mantıksal Kural 3: Yeni kullanıcıyı RAM'e ekle ve veri kaybını önlemek için anında fiziksel dosyaya yaz
            VeriDeposu.kullaniciEkle(yeniKullanici);
            DatabaseManager.kullanicilariKaydet(VeriDeposu.getKullaniciListesi());
            
            return yeniKullanici;
            
        } catch (IllegalArgumentException e) {
            // Factory'den gelecek olası "Geçersiz Tip" hatalarını yakala ve Exception fırlat
            throw new KimlikDogrulamaHatasi("Kayıt İşlemi Başarısız: " + e.getMessage());
        }
    }

    /**
     * 2. GİRİŞ YAP MANTIĞI
     * VeriDeposunda kullanıcıyı arar, şifreyi doğrular ve oturumu başlatır.
     * Geriye dönük uyumluluk: Eski plain-text şifrelerle de giriş yapılabilir,
     * başarılı girişte şifre otomatik olarak hash'lenir.
     */
    public static Kullanici girisYap(String email, String sifre) throws KimlikDogrulamaHatasi {
        
        // E-posta adresine göre kullanıcıyı getir
        Kullanici kullanici = VeriDeposu.emailIleKullaniciBul(email);

        if (kullanici == null) {
            throw new KimlikDogrulamaHatasi("Hata: Bu e-posta adresine ait bir hesap bulunamadı.");
        }

        String hashliSifre = sifreHashle(sifre);
        
        if (kullanici.getSifreHash().equals(hashliSifre)) {
            // Hash ile eşleşti — doğrudan giriş
            aktifKullanici = kullanici;
        } else if (kullanici.getSifreHash().equals(sifre)) {
            // Plain-text ile eşleşti — geriye dönük uyumluluk
            // Şifreyi hash'le ve güncelle (migration)
            kullanici.setSifreHash(hashliSifre);
            DatabaseManager.kullanicilariKaydet(VeriDeposu.getKullaniciListesi());
            aktifKullanici = kullanici;
            System.out.println("Sistem Bilgisi: " + kullanici.getAdSoyad() + " şifresi SHA-256'ya güncellendi.");
        } else {
            throw new KimlikDogrulamaHatasi("Hata: Girdiğiniz şifre hatalı.");
        }

        System.out.println("Sistem Bilgisi: Başarıyla giriş yapıldı. Aktif Oturum -> " + aktifKullanici.getAdSoyad());
        
        return aktifKullanici;
    }

    /**
     * 3. ÇIKIŞ YAP MANTIĞI
     * Oturumu kapatır ve son durum verilerini kalıcı dosyaya yazar.
     */
    public static void cikisYap() {
        if (aktifKullanici != null) {
            System.out.println("Sistem Bilgisi: " + aktifKullanici.getAdSoyad() + " oturumu kapattı.");
            
            // Çıkış yaparken, kullanıcının içeride geçirdiği sürede listelere (Ogun, GunlukTakip) eklediği verileri diske kaydet
            DatabaseManager.kullanicilariKaydet(VeriDeposu.getKullaniciListesi());
            
            // Oturumu sıfırla
            aktifKullanici = null;
        } else {
            System.out.println("Sistem Bilgisi: Kapatılacak aktif bir oturum bulunmuyor.");
        }
    }

    /**
     * 4. OTURUM KONTROLÜ
     * GUI (Arayüz) tarafında, "Profil", "Antrenmanlarım" gibi sayfalara geçerken verilerin 
     * kimden çekileceğini bilmek için bu metot kullanılır.
     */
    public static Kullanici getAktifKullanici() {
        return aktifKullanici;
    }

    /**
     * 5. HESAP SİLME MANTIĞI
     * Kullanıcının şifresini doğrulayarak sistemden ve veritabanından tamamen siler.
     */
    public static void hesapSil(String girilenSifre) throws KimlikDogrulamaHatasi {
        if (aktifKullanici == null) {
            throw new KimlikDogrulamaHatasi("Hata: Oturum açık değil.");
        }
        
        String hashliSifre = sifreHashle(girilenSifre);
        
        // Hash ile veya plain-text ile doğrula (geriye dönük uyumluluk)
        if (!aktifKullanici.getSifreHash().equals(hashliSifre) && !aktifKullanici.getSifreHash().equals(girilenSifre)) {
            throw new KimlikDogrulamaHatasi("Hata: Girdiğiniz şifre hatalı. Hesap silinemedi.");
        }
        
        // VeriDeposundan ve Veritabanından sil
        boolean silindiMi = VeriDeposu.kullaniciSil(aktifKullanici);
        
        if (silindiMi) {
            System.out.println("Sistem Bilgisi: " + aktifKullanici.getAdSoyad() + " hesabı silindi.");
            aktifKullanici = null;
        } else {
            throw new KimlikDogrulamaHatasi("Hata: Hesap veritabanından silinirken bir sorun oluştu.");
        }
    }
}