package com.lifesync;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ogun implements IOzetlenebilir {

    private int ogunId;
    private LocalDate tarih;
    private OgunTuru ogunTuru;
    /** Besin → yenilen gram miktarı. Kütüphanedeki kalori değerleri 100g başınadır. */
    private Map<Besin, Double> besinGramHaritasi;

    public Ogun(int ogunId, OgunTuru ogunTuru) {
        this.ogunId = ogunId;
        this.ogunTuru = ogunTuru;
        this.tarih = LocalDate.now();
        this.besinGramHaritasi = new LinkedHashMap<>();
    }

    // region Getters and Setters
    public int getOgunID() { return ogunId; }
    
    public LocalDate getTarih() { return tarih; }
    public void setTarih(LocalDate tarih) { this.tarih = tarih; }
    
    public OgunTuru getOgunTuru() { return ogunTuru; }


    /** Besin → gram haritasını döndürür. */
    public Map<Besin, Double> getBesinGramHaritasi() { return besinGramHaritasi; }

    /** Geriye dönük uyumluluk: sadece besin nesnelerinin listesini döndürür. */
    public List<Besin> getBesinListesi() { return new ArrayList<>(besinGramHaritasi.keySet()); }
    // endregion

    /**
     * Öğüne besin ekler. Aynı besin tekrar eklenirse gramları toplanır.
     * @param besin Eklenecek besin
     * @param gram  Yenilen gram miktarı
     */
    public void besinEkle(Besin besin, double gram) {
        besinGramHaritasi.merge(besin, gram, Double::sum);
    }

    public void besinSil(int besinId) {
        besinGramHaritasi.entrySet().removeIf(e -> e.getKey().getBesinId() == besinId);
    }

    /**
     * Öğündeki toplam kaloriyi gram miktarına göre hesaplar.
     * Formül: (gram / 100) × besin.getKalori()
     */
    public double toplamKaloriHesapla() {
        double toplam = 0;
        for (Map.Entry<Besin, Double> giris : besinGramHaritasi.entrySet()) {
            toplam += (giris.getValue() / 100.0) * giris.getKey().getKalori();
        }
        return toplam;
    }

    /**
     * Öğündeki makro besin öğelerini gram miktarına göre hesaplar.
     */
    public Map<String, Double> makroHesapla() {
        Map<String, Double> ozet = new LinkedHashMap<>();
        double protein = 0, karbonhidrat = 0, yag = 0;
        for (Map.Entry<Besin, Double> giris : besinGramHaritasi.entrySet()) {
            double oran = giris.getValue() / 100.0;
            protein      += oran * giris.getKey().getProtein();
            karbonhidrat += oran * giris.getKey().getKarbonhidrat();
            yag          += oran * giris.getKey().getYag();
        }
        ozet.put("Protein", protein);
        ozet.put("Karbonhidrat", karbonhidrat);
        ozet.put("Yag", yag);
        return ozet;
    }

    @Override
    public String ozetGetir() {
        return ogunTuru + " Özeti: " + besinGramHaritasi.size() +
               " çeşit besin tüketildi. Toplam Kalori: " +
               String.format("%.1f", toplamKaloriHesapla()) + " kcal";
    }

    @Override
    public String toString() {
        return "Ogun{id=" + ogunId + ", tur=" + ogunTuru +
               ", tarih=" + tarih +
               ", toplamKalori=" + String.format("%.1f", toplamKaloriHesapla()) + "}";
    }
}
