package com.lifesync;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Antrenman implements IOzetlenebilir {
    private int antrenmanId;
    private LocalDate tarih;
    private AntrenmanTuru antrenmanTuru;
    private List<Egzersiz> egzersizListesi;
    private boolean tamamlandi;

    public Antrenman(int antrenmanId, LocalDate tarih, AntrenmanTuru antrenmanTuru) {
        this.antrenmanId = antrenmanId;
        this.tarih = tarih;
        this.antrenmanTuru = antrenmanTuru;
        this.egzersizListesi = new ArrayList<Egzersiz>();
        this.tamamlandi = false;
    }
    //Getters and Setters
    public boolean isTamamlandi() {
        return tamamlandi;
    }

    public void setTamamlandi(boolean tamamlandi) { this.tamamlandi = tamamlandi; } 
    
    public int getAntrenmanId() { return antrenmanId; }
    
    public LocalDate getTarih() { return tarih; }
            
    public AntrenmanTuru getAntrenmanTuru() { return antrenmanTuru; }
        
    public List<Egzersiz> getEgzersizListesi() { return egzersizListesi; }

    public void egzersizEkle(Egzersiz egzersiz) {
        egzersizListesi.add(egzersiz);
    }

    public void egzersizSil(int egzersizId) {
        egzersizListesi.removeIf(e -> e.getEgzersizId() == egzersizId);
    }

    public double toplamHacimHesapla() { //Bu method antrenmandaki bütün egzersizlerin yaptığı toplam iş miktarını hesaplıyor.
        double toplamHacim = 0;

        for (Egzersiz egzersiz : egzersizListesi) {
            toplamHacim += egzersiz.getSetSayisi() * egzersiz.getTekrarSayisi() * egzersiz.getAgirlik();
        }

        return toplamHacim;
    }

    @Override
    public String ozetGetir() {
        String durum = tamamlandi ? "Tamamlandı" : "Bekliyor";
        return "Antrenman ID: " + antrenmanId +
                ", Tarih: " + tarih +
                ", Tür: " + antrenmanTuru +
                ", Egzersiz Sayısı: " + egzersizListesi.size() +
                ", Toplam Hacim: " + toplamHacimHesapla() +
                ", Durum: " + durum;
    }

    @Override
    public String toString() {
        return "Antrenman{id=" + antrenmanId + ", tarih=" + tarih + ", tur=" + antrenmanTuru + ", egzersizSayisi=" + egzersizListesi.size() + ", tamamlandi=" + tamamlandi + "}";
    }
}