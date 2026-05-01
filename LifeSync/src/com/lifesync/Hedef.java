package com.lifesync;

public class Hedef {
    private double hedefKilo;
    private double hedefKalori;
    private double hedefProtein;
    private double hedefSu;

    public Hedef(double hedefKilo, double hedefKalori, double hedefProtein, double hedefSu) {
        this.hedefKilo = hedefKilo;
        this.hedefKalori = hedefKalori;
        this.hedefProtein = hedefProtein;
        this.hedefSu = hedefSu;
    }

    public double getHedefKilo() { return hedefKilo; }
    public void setHedefKilo(double hedefKilo) { this.hedefKilo = hedefKilo; }
    public double getHedefKalori() { return hedefKalori; }
    public void setHedefKalori(double hedefKalori) { this.hedefKalori = hedefKalori; }
    public double getHedefProtein() { return hedefProtein; }
    public void setHedefProtein(double hedefProtein) { this.hedefProtein = hedefProtein; }
    public double getHedefSu() { return hedefSu; }
    public void setHedefSu(double hedefSu) { this.hedefSu = hedefSu; }

    
    // Hedefe ne kadar yaklaşıldığını analiz eden rasyonel bir metot 
    public String hedefAnaliziYap(double guncelKilo, double alinanKalori, double alinanProtein) {
        double kiloFarki = Math.abs(this.hedefKilo - guncelKilo);
        double kaloriFarki = this.hedefKalori - alinanKalori;

        return "Hedef Kilo Farkı: " + kiloFarki + " kg | Kalan Kalori İhtiyacı: " + kaloriFarki + " kcal";
    }
}