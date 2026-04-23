package com.lifesync;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Antrenman {
    private int antrenmanId;
    private LocalDate tarih;
    private AntrenmanTuru antrenmanTuru;
    private List<Egzersiz> egzersizListesi;

    public Antrenman(int antrenmanId, LocalDate tarih, AntrenmanTuru antrenmanTuru) {
        this.antrenmanId = antrenmanId;
        this.tarih = tarih;
        this.antrenmanTuru = antrenmanTuru;
        this.egzersizListesi = new ArrayList<Egzersiz>();
    }
    //Getters and Setters
    public int getAntrenmanId() {
        return antrenmanId;
    }

    public void setAntrenmanId(int antrenmanId) {
        this.antrenmanId = antrenmanId;
    }

    public LocalDate getTarih() {
        return tarih;
    }

    public void setTarih(LocalDate tarih) {
        this.tarih = tarih;
    }

    public AntrenmanTuru getAntrenmanTuru() {
        return antrenmanTuru;
    }

    public void setAntrenmanTuru(AntrenmanTuru antrenmanTuru) {
        this.antrenmanTuru = antrenmanTuru;
    }

    public List<Egzersiz> getEgzersizListesi() {
        return egzersizListesi;
    }

    public void egzersizEkle(Egzersiz egzersiz) {
        egzersizListesi.add(egzersiz);
    }

    public void egzersizSil(int egzersizId) {
        for (int i = 0; i < egzersizListesi.size(); i++) {
            if (egzersizListesi.get(i).getEgzersizId() == egzersizId) {
                egzersizListesi.remove(i);
                break;
            }
        }
    }

    public double toplamHacimHesapla() { //Bu method antrenmandaki bütün egzersizlerin yaptığı toplam iş miktarını hesaplıyor.
        double toplamHacim = 0;

        for (Egzersiz egzersiz : egzersizListesi) {
            toplamHacim += egzersiz.getSetSayisi() * egzersiz.getTekrarSayisi() * egzersiz.getAgirlik();
        }

        return toplamHacim;
    }

    public String ozetGetir() {
        return "Antrenman ID: " + antrenmanId +
               ", Tarih: " + tarih +
               ", Tür: " + antrenmanTuru +
               ", Egzersiz Sayısı: " + egzersizListesi.size() +
               ", Toplam Hacim: " + toplamHacimHesapla();
    }
}