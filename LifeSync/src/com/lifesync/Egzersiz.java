package com.lifesync;
public class Egzersiz {
    private int egzersizId;
    private String egzersizAdi;
    private int setSayisi;
    private int tekrarSayisi;
    private double agirlik;

    public Egzersiz(int egzersizId, String egzersizAdi, int setSayisi, int tekrarSayisi, double agirlik) {
        this.egzersizId = egzersizId;
        this.egzersizAdi = egzersizAdi;
        this.setSayisi = setSayisi;
        this.tekrarSayisi = tekrarSayisi;
        this.agirlik = agirlik;
    }

    public int getEgzersizId() {
        return egzersizId;
    }

    public void setEgzersizId(int egzersizId) {
        this.egzersizId = egzersizId;
    }

    public String getEgzersizAdi() {
        return egzersizAdi;
    }

    public void setEgzersizAdi(String egzersizAdi) {
        this.egzersizAdi = egzersizAdi;
    }

    public int getSetSayisi() {
        return setSayisi;
    }

    public void setSetSayisi(int setSayisi) {
        this.setSayisi = setSayisi;
    }

    public int getTekrarSayisi() {
        return tekrarSayisi;
    }

    public void setTekrarSayisi(int tekrarSayisi) {
        this.tekrarSayisi = tekrarSayisi;
    }

    public double getAgirlik() {
        return agirlik;
    }

    public void setAgirlik(double agirlik) {
        this.agirlik = agirlik;
    }

    public String bilgiGetir() {
        return "Egzersiz ID: " + egzersizId +
               ", Adı: " + egzersizAdi +
               ", Set: " + setSayisi +
               ", Tekrar: " + tekrarSayisi +
               ", Ağırlık: " + agirlik;
    }
}