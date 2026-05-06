# LifeSync 🏋️‍♂️🥗

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A22?style=for-the-badge&logo=apachemaven&logoColor=white)

**LifeSync**, sporcuların ve antrenörlerin bir araya geldiği, beslenme, antrenman ve gelişim takibini kolaylaştıran kapsamlı bir masaüstü uygulamasıdır. Kullanıcıların hedeflerine ulaşmasını sağlamak için günlük kalori takibi, makro besin analizleri, egzersiz planlaması ve antrenör-sporcu iletişimini tek bir platformda birleştirir.

## 🌟 Öne Çıkan Özellikler

### 👥 Çoklu Kullanıcı Rolleri
- **Antrenör:** Sporcularını yönetebilir, onlara hedefler atayabilir, beslenme ve antrenman istatistiklerini detaylıca inceleyebilir ve toplu mesaj gönderebilir.
- **Profesyonel Sporcu:** Kapsamlı makro ve mikro besin takibi, yoğun antrenman programları ve antrenör geri bildirimleriyle performansını zirveye taşır.
- **Amatör Sporcu:** Temel kalori takibi ve günlük egzersiz kayıtlarıyla sağlıklı yaşama adım atar.

### 🍏 Beslenme ve Öğün Takibi
- Kapsamlı besin veritabanı (Protein, Karbonhidrat, Yağ oranları dahil).
- Sabah, Öğle, Akşam, Ara Öğün ve Antrenman Öncesi/Sonrası gibi farklı öğün türleri.
- Supplement (Takviye) kullanımı kayıtları.
- Günlük alınan ve harcanan kalorinin net özeti.

### 💪 Egzersiz ve Antrenman Modülü
- Kardiyo, Güç (Ağırlık), Esneklik ve Spor Dalı spesifik antrenman türleri.
- Süre, set, tekrar ve yakılan kalori bazlı egzersiz kayıtları.

### 📈 Gelişim ve Hedef Takibi
- Kilo alma, kilo verme, kas kazanımı gibi özel hedefler belirleme.
- Hedefe ne kadar yaklaşıldığını gösteren görsel ilerleme çubukları.

### ✉️ İletişim (Mesajlaşma)
- Antrenör ve sporcular arasında uygulama içi direkt mesajlaşma altyapısı.

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
   git clone https://github.com/kullaniciadi/LifeSync.git
   ```

2. Proje dizinine gidin:
   ```bash
   cd LifeSync
   ```

3. Maven ile bağımlılıkları yükleyin ve projeyi derleyin:
   ```bash
   mvn clean install
   ```

4. Uygulamayı başlatın:
   ```bash
   mvn exec:java -Dexec.mainClass="com.lifesync.gui.MainFrame"
   ```

## 📂 Proje Mimarisi

- `com.lifesync` : Temel veri modelleri (Kullanici, Sporcu, Besin, Ogun, vs.) ve iş mantığı (servisler).
- `com.lifesync.gui` : Kullanıcı arayüzü bileşenleri (`MainFrame.java`).
- `DatabaseManager.java` : SQLite veritabanı işlemleri, tablo oluşturma ve CRUD operasyonları.
- `AuthService.java` : Kullanıcı kayıt, giriş işlemleri ve şifreleme mekanizmaları.
- `GunlukTakipService.java` : Günlük bazda besin tüketimi ve egzersiz harcaması hesaplamaları.

---
*LifeSync ile hedeflerine daha hızlı ve planlı ulaş!* 🏆
