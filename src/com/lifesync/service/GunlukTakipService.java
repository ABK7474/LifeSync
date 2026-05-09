package com.lifesync.service;

import com.lifesync.model.Antrenman;
import com.lifesync.model.Besin;
import com.lifesync.model.Egzersiz;
import com.lifesync.model.Ogun;
import com.lifesync.model.Sporcu;
import java.time.LocalDate;

/**
 * Günlük kalori alımı ve harcamasını hesaplayan yardımcı (utility) sınıf.
 * Nesne oluşturulamaz; tüm metotlar statik olarak çağrılır.
 *
 * Besin kütüphanesindeki kalori değerleri 100 gram başınadır.
 * Egzersiz kalori tahmini: set × tekrar × ağırlık × 0.1 kcal
 */
public final class GunlukTakipService {

    private GunlukTakipService() {}

    /**
     * Belirli bir gramda yenilen besinin kalori değerini hesaplar.
     * @param besin Besin nesnesi (değerleri 100g başınadır)
     * @param gram  Tüketilen gram miktarı
     */
    public static double besinKaloriHesapla(Besin besin, double gram) {
        return (gram / 100.0) * besin.getKalori();
    }

    /**
     * Bir egzersizden harcanan tahmini kaloriyi hesaplar.
     * Formül: set × tekrar × ağırlık × 0.1
     */
    public static double egzersizKaloriHesapla(Egzersiz egzersiz) {
        return egzersiz.getSetSayisi() * egzersiz.getTekrarSayisi()
               * egzersiz.getAgirlik() * 0.1;
    }

    /**
     * Sporcunun bugünkü tüm öğünlerinden aldığı toplam kaloriyi döndürür.
     */
    public static double gunlukAlinanKalori(Sporcu sporcu) {
        LocalDate bugun = LocalDate.now();
        double toplam = 0;
        for (Ogun ogun : sporcu.getOgunListesi()) {
            if (ogun.getTarih().equals(bugun)) {
                toplam += ogun.toplamKaloriHesapla();
            }
        }
        return toplam;
    }

    /**
     * Sporcunun bugünkü antrenmanlarındaki egzersizlerden harcadığı
     * toplam tahmini kaloriyi döndürür.
     */
    public static double gunlukHarcananKalori(Sporcu sporcu) {
        LocalDate bugun = LocalDate.now();
        double toplam = 0;
        for (Antrenman antrenman : sporcu.getAntrenmanListesi()) {
            if (antrenman.getTarih().equals(bugun)) {
                for (Egzersiz egzersiz : antrenman.getEgzersizListesi()) {
                    toplam += egzersizKaloriHesapla(egzersiz);
                }
            }
        }
        return toplam;
    }

    /**
     * Günlük net kalori durumunu hesaplayarak kullanıcıya anlamlı bir metin döndürür.
     */
    public static String netDurumHesapla(Sporcu sporcu) {
        double alinan   = gunlukAlinanKalori(sporcu);
        double harcanan = gunlukHarcananKalori(sporcu);
        double net      = alinan - harcanan;

        String durum;
        if      (net >  100) durum = "Pozitif Denge — Kilo Alma Egilimi";
        else if (net < -100) durum = "Negatif Denge — Kilo Verme Egilimi";
        else                 durum = "Notr Denge — Kilo Koruma";

        return String.format(
            "Alinan: %.1f kcal  |  Harcanan: %.1f kcal  |  Net: %+.1f kcal\n%s",
            alinan, harcanan, net, durum
        );
    }
}
