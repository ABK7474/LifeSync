package com.lifesync;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:lifesync.db";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            // Kullanıcılar tablosu
            String sqlKullanicilar = "CREATE TABLE IF NOT EXISTS kullanicilar ("
                    + "id INTEGER PRIMARY KEY,"
                    + "tip TEXT NOT NULL,"
                    + "ad_soyad TEXT NOT NULL,"
                    + "email TEXT NOT NULL,"
                    + "sifre TEXT NOT NULL,"
                    + "boy REAL,"
                    + "kilo REAL,"
                    + "ekstra_bilgi TEXT"
                    + ");";
            stmt.execute(sqlKullanicilar);

            // Antrenmanlar tablosu
            String sqlAntrenmanlar = "CREATE TABLE IF NOT EXISTS antrenmanlar ("
                    + "id INTEGER PRIMARY KEY,"
                    + "sporcu_id INTEGER,"
                    + "tarih TEXT,"
                    + "tur TEXT,"
                    + "tamamlandi INTEGER,"
                    + "FOREIGN KEY(sporcu_id) REFERENCES kullanicilar(id)"
                    + ");";
            stmt.execute(sqlAntrenmanlar);

            // Mesajlar tablosu
            String sqlMesajlar = "CREATE TABLE IF NOT EXISTS mesajlar ("
                    + "id INTEGER PRIMARY KEY,"
                    + "gonderen_id INTEGER,"
                    + "alici_id INTEGER,"
                    + "gonderen_adi TEXT,"
                    + "alici_adi TEXT,"
                    + "icerik TEXT,"
                    + "tarih TEXT,"
                    + "FOREIGN KEY(gonderen_id) REFERENCES kullanicilar(id),"
                    + "FOREIGN KEY(alici_id) REFERENCES kullanicilar(id)"
                    + ");";
            stmt.execute(sqlMesajlar);

            // Ogunler tablosu
            stmt.execute("CREATE TABLE IF NOT EXISTS ogunler (id INTEGER PRIMARY KEY, sporcu_id INTEGER, tarih TEXT, ogun_turu TEXT, FOREIGN KEY(sporcu_id) REFERENCES kullanicilar(id))");

            // Ogun-Besin iliskisi
            stmt.execute("CREATE TABLE IF NOT EXISTS ogun_besinler (id INTEGER PRIMARY KEY AUTOINCREMENT, ogun_id INTEGER, besin_id INTEGER, gram REAL, FOREIGN KEY(ogun_id) REFERENCES ogunler(id))");

            // Antrenman-Egzersiz iliskisi
            stmt.execute("CREATE TABLE IF NOT EXISTS antrenman_egzersizler (id INTEGER PRIMARY KEY AUTOINCREMENT, antrenman_id INTEGER, egzersiz_id INTEGER, FOREIGN KEY(antrenman_id) REFERENCES antrenmanlar(id))");

            // Gunluk Takip tablosu
            stmt.execute("CREATE TABLE IF NOT EXISTS gunluk_takip (id INTEGER PRIMARY KEY, sporcu_id INTEGER, tarih TEXT, su_miktari REAL, gunluk_not TEXT, FOREIGN KEY(sporcu_id) REFERENCES kullanicilar(id))");

            // GunlukTakip-Supplement iliskisi
            stmt.execute("CREATE TABLE IF NOT EXISTS gunluk_takip_supplementler (id INTEGER PRIMARY KEY AUTOINCREMENT, takip_id INTEGER, supplement_id INTEGER, FOREIGN KEY(takip_id) REFERENCES gunluk_takip(id))");

            // Hedefler tablosu
            stmt.execute("CREATE TABLE IF NOT EXISTS hedefler (sporcu_id INTEGER PRIMARY KEY, hedef_kilo REAL, hedef_kalori REAL, hedef_protein REAL, hedef_su REAL, FOREIGN KEY(sporcu_id) REFERENCES kullanicilar(id))");

            System.out.println("Sistem Bilgisi: SQLite Veritabanı bağlantısı ve tablolar hazır.");
        } catch (SQLException e) {
            System.err.println("Veritabanı Başlatma Hatası: " + e.getMessage());
        }
    }

    // --- KULLANICI İŞLEMLERİ ---

    public static void kullanicilariKaydet(List<Kullanici> kullanicilar) {
        String sql = "INSERT OR REPLACE INTO kullanicilar(id, tip, ad_soyad, email, sifre, boy, kilo, ekstra_bilgi) VALUES(?,?,?,?,?,?,?,?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            for (Kullanici k : kullanicilar) {
                pstmt.setInt(1, k.getKullaniciID());
                pstmt.setString(3, k.getAdSoyad());
                pstmt.setString(4, k.getEmail());
                pstmt.setString(5, k.getSifreHash());

                if (k instanceof AmatorSporcu) {
                    AmatorSporcu as = (AmatorSporcu) k;
                    pstmt.setString(2, "AMATOR");
                    pstmt.setDouble(6, as.getBoy());
                    pstmt.setDouble(7, as.getKilo());
                    pstmt.setString(8, as.getDeneyimSeviyesi());
                } else if (k instanceof ProfesyonelSporcu) {
                    ProfesyonelSporcu ps = (ProfesyonelSporcu) k;
                    pstmt.setString(2, "PROFESYONEL");
                    pstmt.setDouble(6, ps.getBoy());
                    pstmt.setDouble(7, ps.getKilo());
                    pstmt.setString(8, ps.getYaristigiAlan());
                } else if (k instanceof Antrenor) {
                    pstmt.setString(2, "ANTRENOR");
                    pstmt.setDouble(6, 0.0);
                    pstmt.setDouble(7, 0.0);
                    pstmt.setString(8, "YOK");
                }
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Kullanıcı Kaydetme Hatası: " + e.getMessage());
        }
    }

    public static List<Kullanici> kullanicilariYukle() {
        List<Kullanici> liste = new ArrayList<>();
        String sql = "SELECT * FROM kullanicilar";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                String tip = rs.getString("tip");
                int id = rs.getInt("id");
                String ad = rs.getString("ad_soyad");
                String email = rs.getString("email");
                String sifre = rs.getString("sifre");
                double boy = rs.getDouble("boy");
                double kilo = rs.getDouble("kilo");
                String ekstra = rs.getString("ekstra_bilgi");
                
                Kullanici k = KullaniciFactory.kullaniciOlustur(tip, id, ad, email, sifre, boy, kilo, ekstra);
                if (k != null) liste.add(k);
            }
        } catch (SQLException e) {
            System.err.println("Kullanıcı Yükleme Hatası: " + e.getMessage());
        }
        return liste;
    }

    /**
     * Sadece profil bilgilerini (ad, boy, kilo) günceller.
     * Şifre alanına DOKUNMAZ — UPDATE ile yalnızca ilgili sütunlar değiştirilir.
     */
    public static void profilGuncelle(Kullanici kullanici) {
        String sql = "UPDATE kullanicilar SET ad_soyad = ?, boy = ?, kilo = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kullanici.getAdSoyad());
            if (kullanici instanceof Sporcu) {
                ps.setDouble(2, ((Sporcu) kullanici).getBoy());
                ps.setDouble(3, ((Sporcu) kullanici).getKilo());
            } else {
                ps.setDouble(2, 0);
                ps.setDouble(3, 0);
            }
            ps.setInt(4, kullanici.getKullaniciID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Profil Güncelleme Hatası: " + e.getMessage());
        }
    }

    public static boolean kullaniciSil(int kullaniciId) {
        String[] deleteQueries = {
            "DELETE FROM mesajlar WHERE gonderen_id = ? OR alici_id = ?",
            "DELETE FROM ogun_besinler WHERE ogun_id IN (SELECT id FROM ogunler WHERE sporcu_id = ?)",
            "DELETE FROM ogunler WHERE sporcu_id = ?",
            "DELETE FROM antrenman_egzersizler WHERE antrenman_id IN (SELECT id FROM antrenmanlar WHERE sporcu_id = ?)",
            "DELETE FROM antrenmanlar WHERE sporcu_id = ?",
            "DELETE FROM gunluk_takip_supplementler WHERE takip_id IN (SELECT id FROM gunluk_takip WHERE sporcu_id = ?)",
            "DELETE FROM gunluk_takip WHERE sporcu_id = ?",
            "DELETE FROM hedefler WHERE sporcu_id = ?",
            "DELETE FROM kullanicilar WHERE id = ?"
        };

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false); // Transaction başlat
            
            try {
                int affectedRows = 0;
                for (String sql : deleteQueries) {
                    try (PreparedStatement ps = conn.prepareStatement(sql)) {
                        ps.setInt(1, kullaniciId);
                        if (sql.contains("OR alici_id = ?")) {
                            ps.setInt(2, kullaniciId);
                        }
                        int affected = ps.executeUpdate();
                        if (sql.equals("DELETE FROM kullanicilar WHERE id = ?")) {
                            affectedRows = affected;
                        }
                    }
                }
                
                conn.commit(); // Başarılıysa onayla
                return affectedRows > 0;
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Kullanıcı silinirken hata (rollback): " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Bağlantı hatası: " + e.getMessage());
            return false;
        }
    }

    // --- ANTRENMAN İŞLEMLERİ ---

    public static void antrenmanlariKaydet(List<Kullanici> kullanicilar) {
        String sql = "INSERT OR REPLACE INTO antrenmanlar(id, sporcu_id, tarih, tur, tamamlandi) VALUES(?,?,?,?,?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            for (Kullanici k : kullanicilar) {
                if (k instanceof Sporcu) {
                    Sporcu s = (Sporcu) k;
                    for (Antrenman a : s.getAntrenmanListesi()) {
                        pstmt.setInt(1, a.getAntrenmanId());
                        pstmt.setInt(2, s.getKullaniciID());
                        pstmt.setString(3, a.getTarih().toString());
                        pstmt.setString(4, a.getAntrenmanTuru().name());
                        pstmt.setInt(5, a.isTamamlandi() ? 1 : 0);
                        pstmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Antrenman Kaydetme Hatası: " + e.getMessage());
        }
    }

    public static void antrenmanlariYukle(List<Kullanici> kullanicilar) {
        String sql = "SELECT * FROM antrenmanlar";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                int id = rs.getInt("id");
                int sporcuId = rs.getInt("sporcu_id");
                LocalDate tarih = LocalDate.parse(rs.getString("tarih"));
                AntrenmanTuru tur = AntrenmanTuru.valueOf(rs.getString("tur"));
                boolean tamamlandi = rs.getInt("tamamlandi") == 1;
                
                for (Kullanici k : kullanicilar) {
                    if (k instanceof Sporcu && k.getKullaniciID() == sporcuId) {
                        Antrenman a = new Antrenman(id, tarih, tur);
                        a.setTamamlandi(tamamlandi);
                        ((Sporcu) k).antrenmanEkle(a);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Antrenman Yükleme Hatası: " + e.getMessage());
        }
    }

    // --- MESAJ İŞLEMLERİ ---

    public static void mesajlariKaydet(List<Mesaj> mesajListesi) {
        String sql = "INSERT OR REPLACE INTO mesajlar(id, gonderen_id, alici_id, gonderen_adi, alici_adi, icerik, tarih) VALUES(?,?,?,?,?,?,?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            for (Mesaj m : mesajListesi) {
                pstmt.setInt(1, m.getMesajId());
                pstmt.setInt(2, m.getGonderenId());
                pstmt.setInt(3, m.getAliciId());
                pstmt.setString(4, m.getGonderenAdi());
                pstmt.setString(5, m.getAliciAdi());
                pstmt.setString(6, m.getMesajIcerigi());
                pstmt.setString(7, m.getTarih().toString());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Mesaj Kaydetme Hatası: " + e.getMessage());
        }
    }

    public static List<Mesaj> mesajlariYukle() {
        List<Mesaj> liste = new ArrayList<>();
        String sql = "SELECT * FROM mesajlar";
        
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                int id = rs.getInt("id");
                int gonderenId = rs.getInt("gonderen_id");
                int aliciId = rs.getInt("alici_id");
                String gonderenAdi = rs.getString("gonderen_adi");
                String aliciAdi = rs.getString("alici_adi");
                String icerik = rs.getString("icerik");
                LocalDateTime tarih = LocalDateTime.parse(rs.getString("tarih"));
                
                Mesaj m = new Mesaj(id, gonderenId, aliciId, gonderenAdi, aliciAdi, icerik);
                m.setTarih(tarih);
                liste.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Mesaj Yükleme Hatası: " + e.getMessage());
        }
        return liste;
    }

    // --- KULLANICI ID URETIMI ---
    public static int yeniKullaniciIdUret() {
        String sql = "SELECT MAX(id) FROM kullanicilar";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            System.err.println("ID Uretme Hatasi: " + e.getMessage());
        }
        return 1;
    }

    // --- OGUN ISLEMLERI ---
    public static void ogunleriKaydet(List<Kullanici> kullanicilar) {
        String sqlOgun = "INSERT OR REPLACE INTO ogunler(id, sporcu_id, tarih, ogun_turu) VALUES(?,?,?,?)";
        String sqlBesinSil = "DELETE FROM ogun_besinler WHERE ogun_id = ?";
        String sqlBesin = "INSERT INTO ogun_besinler(ogun_id, besin_id, gram) VALUES(?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement psOgun = conn.prepareStatement(sqlOgun);
             PreparedStatement psSil = conn.prepareStatement(sqlBesinSil);
             PreparedStatement psBesin = conn.prepareStatement(sqlBesin)) {
            for (Kullanici k : kullanicilar) {
                if (k instanceof Sporcu) {
                    Sporcu s = (Sporcu) k;
                    for (Ogun o : s.getOgunListesi()) {
                        psOgun.setInt(1, o.getOgunID());
                        psOgun.setInt(2, s.getKullaniciID());
                        psOgun.setString(3, o.getTarih().toString());
                        psOgun.setString(4, o.getOgunTuru().name());
                        psOgun.executeUpdate();
                        psSil.setInt(1, o.getOgunID());
                        psSil.executeUpdate();
                        for (java.util.Map.Entry<Besin, Double> e : o.getBesinGramHaritasi().entrySet()) {
                            psBesin.setInt(1, o.getOgunID());
                            psBesin.setInt(2, e.getKey().getBesinId());
                            psBesin.setDouble(3, e.getValue());
                            psBesin.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Ogun Kaydetme Hatasi: " + e.getMessage());
        }
    }

    public static void ogunleriYukle(List<Kullanici> kullanicilar) {
        String sqlOgun = "SELECT * FROM ogunler";
        String sqlBesin = "SELECT * FROM ogun_besinler WHERE ogun_id = ?";
        List<Besin> besinler = VeriDeposu.getBesinKutuphanesi();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlOgun);
             PreparedStatement psBesin = conn.prepareStatement(sqlBesin)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int sporcuId = rs.getInt("sporcu_id");
                LocalDate tarih = LocalDate.parse(rs.getString("tarih"));
                OgunTuru tur = OgunTuru.valueOf(rs.getString("ogun_turu"));
                Ogun ogun = new Ogun(id, tur);
                ogun.setTarih(tarih);
                psBesin.setInt(1, id);
                try (ResultSet rsB = psBesin.executeQuery()) {
                    while (rsB.next()) {
                        int besinId = rsB.getInt("besin_id");
                        double gram = rsB.getDouble("gram");
                        for (Besin b : besinler) {
                            if (b.getBesinId() == besinId) {
                                ogun.besinEkle(b, gram);
                                break;
                            }
                        }
                    }
                }
                for (Kullanici k : kullanicilar) {
                    if (k instanceof Sporcu && k.getKullaniciID() == sporcuId) {
                        ((Sporcu) k).ogunEkle(ogun);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Ogun Yukleme Hatasi: " + e.getMessage());
        }
    }

    // --- ANTRENMAN EGZERSIZ ISLEMLERI ---
    public static void antrenmanEgzersizleriniKaydet(List<Kullanici> kullanicilar) {
        String sqlSil = "DELETE FROM antrenman_egzersizler WHERE antrenman_id = ?";
        String sqlEkle = "INSERT INTO antrenman_egzersizler(antrenman_id, egzersiz_id) VALUES(?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement psSil = conn.prepareStatement(sqlSil);
             PreparedStatement psEkle = conn.prepareStatement(sqlEkle)) {
            for (Kullanici k : kullanicilar) {
                if (k instanceof Sporcu) {
                    for (Antrenman a : ((Sporcu) k).getAntrenmanListesi()) {
                        psSil.setInt(1, a.getAntrenmanId());
                        psSil.executeUpdate();
                        for (Egzersiz eg : a.getEgzersizListesi()) {
                            psEkle.setInt(1, a.getAntrenmanId());
                            psEkle.setInt(2, eg.getEgzersizId());
                            psEkle.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Antrenman Egzersiz Kaydetme Hatasi: " + e.getMessage());
        }
    }

    public static void antrenmanEgzersizleriniYukle(List<Kullanici> kullanicilar) {
        String sql = "SELECT * FROM antrenman_egzersizler";
        List<Egzersiz> katalog = VeriDeposu.getEgzersizKutuphanesi();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int antrenmanId = rs.getInt("antrenman_id");
                int egzersizId = rs.getInt("egzersiz_id");
                Egzersiz bulunan = null;
                for (Egzersiz eg : katalog) {
                    if (eg.getEgzersizId() == egzersizId) { bulunan = eg; break; }
                }
                if (bulunan == null) continue;
                for (Kullanici k : kullanicilar) {
                    if (k instanceof Sporcu) {
                        for (Antrenman a : ((Sporcu) k).getAntrenmanListesi()) {
                            if (a.getAntrenmanId() == antrenmanId) {
                                a.egzersizEkle(bulunan);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Antrenman Egzersiz Yukleme Hatasi: " + e.getMessage());
        }
    }

    // --- GUNLUK TAKIP ISLEMLERI ---
    public static void gunlukTakipleriKaydet(List<Kullanici> kullanicilar) {
        String sqlTakip = "INSERT OR REPLACE INTO gunluk_takip(id, sporcu_id, tarih, su_miktari, gunluk_not) VALUES(?,?,?,?,?)";
        String sqlSupSil = "DELETE FROM gunluk_takip_supplementler WHERE takip_id = ?";
        String sqlSup = "INSERT INTO gunluk_takip_supplementler(takip_id, supplement_id) VALUES(?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement psTakip = conn.prepareStatement(sqlTakip);
             PreparedStatement psSupSil = conn.prepareStatement(sqlSupSil);
             PreparedStatement psSup = conn.prepareStatement(sqlSup)) {
            for (Kullanici k : kullanicilar) {
                if (k instanceof Sporcu) {
                    Sporcu s = (Sporcu) k;
                    for (GunlukTakip gt : s.getGunlukTakipListesi()) {
                        psTakip.setInt(1, gt.getTakipId());
                        psTakip.setInt(2, s.getKullaniciID());
                        psTakip.setString(3, gt.getTarih().toString());
                        psTakip.setDouble(4, gt.getSuMiktari());
                        psTakip.setString(5, gt.getGunlukNot());
                        psTakip.executeUpdate();
                        psSupSil.setInt(1, gt.getTakipId());
                        psSupSil.executeUpdate();
                        for (Supplement sup : gt.getSupplementListesi()) {
                            psSup.setInt(1, gt.getTakipId());
                            psSup.setInt(2, sup.getSupplementId());
                            psSup.executeUpdate();
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("GunlukTakip Kaydetme Hatasi: " + e.getMessage());
        }
    }

    public static void gunlukTakipleriYukle(List<Kullanici> kullanicilar) {
        String sqlTakip = "SELECT * FROM gunluk_takip";
        String sqlSup = "SELECT * FROM gunluk_takip_supplementler WHERE takip_id = ?";
        List<Supplement> katalog = VeriDeposu.getSupplementKutuphanesi();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlTakip);
             PreparedStatement psSup = conn.prepareStatement(sqlSup)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int sporcuId = rs.getInt("sporcu_id");
                LocalDate tarih = LocalDate.parse(rs.getString("tarih"));
                double su = rs.getDouble("su_miktari");
                String not_ = rs.getString("gunluk_not");
                GunlukTakip gt = new GunlukTakip(id);
                gt.setTarih(tarih);
                gt.setSuMiktari(su);
                gt.setGunlukNot(not_);
                psSup.setInt(1, id);
                try (ResultSet rsS = psSup.executeQuery()) {
                    while (rsS.next()) {
                        int supId = rsS.getInt("supplement_id");
                        for (Supplement s : katalog) {
                            if (s.getSupplementId() == supId) { gt.supplementEkle(s); break; }
                        }
                    }
                }
                for (Kullanici k : kullanicilar) {
                    if (k instanceof Sporcu && k.getKullaniciID() == sporcuId) {
                        ((Sporcu) k).gunlukTakipEkle(gt);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("GunlukTakip Yukleme Hatasi: " + e.getMessage());
        }
    }

    // --- HEDEF ISLEMLERI ---
    public static void hedefleriKaydet(List<Kullanici> kullanicilar) {
        String sql = "INSERT OR REPLACE INTO hedefler(sporcu_id, hedef_kilo, hedef_kalori, hedef_protein, hedef_su) VALUES(?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Kullanici k : kullanicilar) {
                if (k instanceof Sporcu) {
                    Sporcu s = (Sporcu) k;
                    if (s.getHedef() != null) {
                        ps.setInt(1, s.getKullaniciID());
                        ps.setDouble(2, s.getHedef().getHedefKilo());
                        ps.setDouble(3, s.getHedef().getHedefKalori());
                        ps.setDouble(4, s.getHedef().getHedefProtein());
                        ps.setDouble(5, s.getHedef().getHedefSu());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Hedef Kaydetme Hatasi: " + e.getMessage());
        }
    }

    public static void hedefleriYukle(List<Kullanici> kullanicilar) {
        String sql = "SELECT * FROM hedefler";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int sporcuId = rs.getInt("sporcu_id");
                double kilo = rs.getDouble("hedef_kilo");
                double kalori = rs.getDouble("hedef_kalori");
                double protein = rs.getDouble("hedef_protein");
                double su = rs.getDouble("hedef_su");
                Hedef hedef = new Hedef(kilo, kalori, protein, su);
                for (Kullanici k : kullanicilar) {
                    if (k instanceof Sporcu && k.getKullaniciID() == sporcuId) {
                        ((Sporcu) k).setHedef(hedef);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Hedef Yukleme Hatasi: " + e.getMessage());
        }
    }
}
