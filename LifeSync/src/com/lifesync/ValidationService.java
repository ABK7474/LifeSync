package com.lifesync;

public class ValidationService {
    public void kullaniciDogrula(Kullanici kullanici) throws GecersizVeriHatasi {

        if (kullanici.getEmail() == null || !kullanici.getEmail().contains("@")) {
            throw new GecersizVeriHatasi("Hata: Geçersiz e-posta adresi girdiniz!");
        }
        

        if (kullanici.getSifreHash() == null || kullanici.getSifreHash().length() < 4) {
            throw new GecersizVeriHatasi("Hata: Şifre en az 4 karakter olmalıdır!");
        }
        

        if (kullanici.getAdSoyad() == null || kullanici.getAdSoyad().trim().isEmpty()) {
            throw new GecersizVeriHatasi("Hata: Ad soyad alanı boş bırakılamaz!");
        }
    }

    public void sporcuDogrula(Sporcu sporcu) throws GecersizVeriHatasi {
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
