# LifeSync 🏋️‍♂️🥗

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A22?style=for-the-badge&logo=apachemaven&logoColor=white)

**LifeSync**, sporcuların ve antrenörlerin bir araya geldiği, beslenme, antrenman ve gelişim takibini kolaylaştıran kapsamlı bir masaüstü uygulamasıdır. Kullanıcıların hedeflerine ulaşmasını sağlamak için günlük kalori takibi, makro besin analizleri, egzersiz planlaması ve antrenör-sporcu iletişimini modern ve sadeleştirilmiş tek bir platformda birleştirir.

## 🌟 Öne Çıkan Özellikler

### 👥 Çoklu Kullanıcı Rolleri
- **Antrenör:** Merkezi bir sporcu yönetim paneli üzerinden sporcularına hedefler ve detaylı programlar atayabilir, kapsamlı **İstatistikler** sekmesi üzerinden beslenme ve antrenman ilerlemelerini tek ekranda analiz edebilir. Ayrıca, **Mesajlar** sekmesinden tüm sporcularına toplu duyurular veya özel mesajlar gönderebilir.
- **Profesyonel & Amatör Sporcu:** Günlük öğün ve antrenman kayıtlarını basit bir arayüzle girer, antrenöründen gelen hedefleri, mesajları ve antrenmanları takip eder. Tüm kişisel bilgileri, şifre ve hesap ayarlarını birleşik **Profilim** sekmesinden kolayca yönetebilir.

### 🍏 Beslenme ve Öğün Takibi
- Kapsamlı besin veritabanı ile sabah, öğle, akşam ve ara öğün kayıtları.
- Gramaj bazlı otomatik kalori ve makro besin hesaplamaları.
- Günlük alınan kalori, harcanan kalori ve net enerji dengesinin (kilo alma/verme eğilimi) otomatik takibi.

### 💪 Egzersiz ve Antrenman Modülü
- Egzersizlerin set, tekrar ve ağırlık bazlı kayıt altına alınması.
- Antrenör tarafından atanan detaylı antrenman programlarının görüntülenip onaylanması.
- Tamamlanan antrenmanların oranları ve detaylarının istatistiklere yansıması.

### 📈 Gelişim ve Hedef Takibi
- Kilo alma, kilo verme gibi sporcuya özel kalori, protein ve su hedeflerinin belirlenmesi.
- Hedeflenen kaloriden ne kadar kaldığının hesaplanması ve antrenöre detaylı rapor olarak sunulması.

### ✉️ İletişim & Duyuru Sistemi
- Antrenörler ve sporcular arasında kesintisiz, uygulama içi mesajlaşma.
- Antrenörlerin tüm sporcularına tek tıkla **Toplu Duyuru** gönderebilme altyapısı.

## 🛠️ Kullanılan Teknolojiler

- **Programlama Dili:** Java
- **Kullanıcı Arayüzü:** Java Swing
- **Veritabanı:** SQLite (JDBC Driver ile)
- **Proje Yönetimi:** Maven

## 🚀 Kurulum ve Çalıştırma

Projeyi yerel bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyin:

### Ön Koşullar
- Bilgisayarınızda **Java Development Kit (JDK) 8 veya üzeri** yüklü olmalıdır.
- **Maven** yüklü olmalıdır.

### Adımlar

1. Projeyi klonlayın:
   ```bash
   git clone https://github.com/ABK7474/LifeSync.git
   ```

2. Proje dizinine gidin:
   ```bash
   cd LifeSync
   ```

3. Bağımlılıkları yükleyin, projeyi derleyin ve uygulamayı başlatın:
   ```bash
   mvn clean compile exec:java
   ```
   *(Not: `pom.xml` içerisine `<sourceDirectory>` yapılandırması ve `exec.mainClass` eklendiği için bu komut projeyi direkt başlatacaktır.)*

## 📂 Proje Mimarisi

- `com.lifesync` : Temel veri modelleri (Kullanici, Sporcu, Besin, Ogun, vs.) ve iş mantığı servisleri.
- `com.lifesync.gui` : Kullanıcı arayüzü bileşenleri. Uygulamanın merkezi giriş noktası `MainFrame.java` dosyasıdır.
- `DatabaseManager.java` : SQLite veritabanı işlemleri, profil güncellemeleri ve tablo oluşturma.
- `AuthService.java` : Kullanıcı kayıt, giriş işlemleri, oturum yönetimi ve hesap doğrulama.
- `GunlukTakipService.java` : Günlük bazda besin tüketimi ve egzersiz harcaması hesaplamaları.
- `VeriDeposu.java` : Bellek-içi (In-memory) veri yönetimi ve SQLite veritabanı ile RAM arasındaki senkronizasyonun kontrolü.

---
*LifeSync ile hedeflerine daha hızlı ve planlı ulaş!* 🏆
