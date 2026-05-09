package com.lifesync.service;

import com.lifesync.model.*;
import com.lifesync.service.*;
import com.lifesync.repository.*;
import com.lifesync.factory.*;
import com.lifesync.exception.*;
import com.lifesync.interfaces.*;
import com.lifesync.util.*;


/**
 * Doğrulama işlemlerini gerçekleştiren yardımcı (utility) sınıf.
 * Nesne oluşturulamaz; tüm metotlar statik olarak çağrılır.
 * 
 * Eski tasarımda bu bir interface'di ve MainFrame tarafından implement ediliyordu.
 * Ancak tüm metotlar default olduğu için gerçek bir soyutlama sağlamıyordu.
 * OOP prensiplerini karşılamak için final utility class'a dönüştürüldü.
 */
public final class ValidationService {
    
    // Nesne oluşturmayı engelle (Utility Class Prensibi)
    private ValidationService() {}

    public static void kullaniciDogrula(Kullanici kullanici) throws GecersizVeriHatasi {

        if (kullanici.getEmail() == null || !kullanici.getEmail().contains("@")) {
            throw new GecersizVeriHatasi("Hata: Geçersiz e-posta adresi girdiniz!");
        }

        if (kullanici.getSifre() == null || kullanici.getSifre().length() < 4) {
            throw new GecersizVeriHatasi("Hata: Şifre en az 4 karakter olmalıdır!");
        }

        if (kullanici.getAdSoyad() == null || kullanici.getAdSoyad().trim().isEmpty()) {
            throw new GecersizVeriHatasi("Hata: Ad soyad alanı boş bırakılamaz!");
        }
    }

    public static void sporcuDogrula(Sporcu sporcu) throws GecersizVeriHatasi {
        if (sporcu == null) {
            throw new GecersizVeriHatasi("Hata: Sporcu bilgileri bulunamadı!");
        }

        if (sporcu.getBoy() <= 100 || sporcu.getBoy() >= 225) {
            throw new GecersizVeriHatasi("Hata: Geçersiz boy değeri girdiniz! Boy 100 ile 225 cm arasında olmalıdır.");
        }

        if (sporcu.getKilo() <= 40 || sporcu.getKilo() >= 200) {
            throw new GecersizVeriHatasi("Hata: Geçersiz kilo değeri girdiniz! Kilo 40 ile 200 kg arasında olmalıdır.");
        }
    }
}
