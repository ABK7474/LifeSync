package com.lifesync;

public class KullaniciFactory {

    // Fabrika Tasarım Deseni (Factory Method Pattern) uygulaması
    public static Kullanici kullaniciOlustur(String tip, int id, String ad, String email, String sifre, double boy, double kilo, String ekstraBilgi1) {
        
        if (tip == null || tip.trim().isEmpty()) {
            throw new IllegalArgumentException("Hata: Kullanıcı tipi boş olamaz.");
        }

        switch (tip.toUpperCase()) {
            case "ANTRENOR":
                // Antrenör nesnesi oluşturulurken boy, kilo ve ekstra bilgiye ihtiyaç yoktur.
                return new Antrenor(id, ad, email, sifre);
                
            case "AMATOR":
                // ekstraBilgi1, amatör sporcular için deneyimSeviyesi olarak kullanılır.
                return new AmatorSporcu(id, ad, email, sifre, boy, kilo, ekstraBilgi1);
                
            case "PROFESYONEL":
                // ekstraBilgi1, profesyonel sporcular için yaristigiAlan olarak kullanılır.
                return new ProfesyonelSporcu(id, ad, email, sifre, boy, kilo, ekstraBilgi1);
                
            default:
                throw new IllegalArgumentException("Hata: Geçersiz kullanıcı tipi (" + tip + ").");
        }
    }
}