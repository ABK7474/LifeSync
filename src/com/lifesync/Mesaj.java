package com.lifesync;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Antrenör ve sporcu arasındaki mesajlaşmayı temsil eden sınıf.
 * Her mesaj bir gönderen, alıcı, içerik ve zaman damgası içerir.
 */
public class Mesaj {
    private int mesajId;
    private int gonderenId;
    private int aliciId;
    private String gonderenAdi;
    private String aliciAdi;
    private String mesajIcerigi;
    private LocalDateTime tarih;

    public Mesaj(int mesajId, int gonderenId, int aliciId, String gonderenAdi, String aliciAdi, String mesajIcerigi) {
        this.mesajId = mesajId;
        this.gonderenId = gonderenId;
        this.aliciId = aliciId;
        this.gonderenAdi = gonderenAdi;
        this.aliciAdi = aliciAdi;
        this.mesajIcerigi = mesajIcerigi;
        this.tarih = LocalDateTime.now();
    }

    // Getters Setters
    public int getMesajId() { return mesajId; }
    public int getGonderenId() { return gonderenId; }
    public int getAliciId() { return aliciId; }
    public String getGonderenAdi() { return gonderenAdi; }
    public String getAliciAdi() { return aliciAdi; }
    public String getMesajIcerigi() { return mesajIcerigi; }
    
    public LocalDateTime getTarih() { return tarih; }
    public void setTarih(LocalDateTime tarih) { this.tarih = tarih; }

    /**
     * Mesajı okunabilir formatta döndürür.
     */
    public String formatliGoster() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "[" + tarih.format(fmt) + "] " + gonderenAdi + ": " + mesajIcerigi;
    }

    @Override
    public String toString() {
        return "Mesaj{id=" + mesajId + ", gonderen='" + gonderenAdi + "', alici='" + aliciAdi + "'}";
    }
}
