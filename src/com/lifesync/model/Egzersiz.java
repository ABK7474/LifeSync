package com.lifesync.model;

import com.lifesync.interfaces.IBilgiGetirebilir;

public class Egzersiz implements IBilgiGetirebilir{
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

    public int getEgzersizId() { return egzersizId; }
        
    public String getEgzersizAdi() { return egzersizAdi; }

    public int getSetSayisi() { return setSayisi; }
        
    public int getTekrarSayisi() { return tekrarSayisi; }
        
    public double getAgirlik() { return agirlik; }
    
    public String bilgiGetir() {
        return "Egzersiz ID: " + egzersizId +
                ", Adı: " + egzersizAdi +
                ", Set: " + setSayisi +
                ", Tekrar: " + tekrarSayisi +
                ", Ağırlık: " + agirlik;
    }

    @Override
    public String toString() {
        return "Egzersiz{id=" + egzersizId + ", ad='" + egzersizAdi + "', set=" + setSayisi + ", tekrar=" + tekrarSayisi + ", agirlik=" + agirlik + "}";
    }
}
