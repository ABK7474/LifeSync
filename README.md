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
- Bilgisayarınızda **Java Development Kit (JDK) 25** yüklü olmalıdır.
- **Maven** yüklü olmalıdır.

### Maven Kurulumu

Maven'in kurulu olup olmadığını kontrol etmek için:
```bash
mvn -v
```

Eğer Maven kurulu değilse işletim sisteminize göre aşağıdaki yöntemlerden birini kullanabilirsiniz:

**Windows:**
```bash
winget install Apache.Maven
```

Manuel kurulum yapmak isterseniz aşağıdaki adımları izleyebilirsiniz.

#### Windows için Görsel Maven PATH Kurulumu

Aşağıdaki görsel rehber, Windows üzerinde Maven'i indirip PATH'e eklemeyi ve ardından LifeSync projesini çalıştırmayı adım adım gösterir.

> Not: Maven klasör yolu örnek olarak `C:\Program Files\Apache\maven` kullanılmıştır. Maven'i farklı bir klasöre çıkardıysanız `MAVEN_HOME` değerini kendi klasör yolunuza göre düzenleyin.

<table>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/eef36606-d625-45e7-951c-c936002cd0f2" width="100%" alt="Windows Maven kurulumu baslik ekranı"><br>
      <b>1.</b> Kurulum rehberi başlatılır.
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/339b2752-466e-4737-84ce-0179c949e7eb" width="100%" alt="Apache Maven ZIP dosyasını indirme"><br>
      <b>2.</b> Apache Maven ZIP dosyası indirilir.
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/1dbebce5-4c79-4684-abfa-5d559d876e00" width="100%" alt="Maven ZIP dosyasını klasöre çıkarma"><br>
      <b>3.</b> ZIP dosyası klasöre çıkarılır.
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/8b303eae-01e4-4328-aa47-f819134d9b7f" width="100%" alt="Maven klasörünü Program Files içine yerleştirme"><br>
      <b>4.</b> Maven klasörü uygun konuma yerleştirilir.
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/42f36e96-fae6-4c88-a2a1-c16c5da18ff4" width="100%" alt="Maven bin klasörünü gösterme"><br>
      <b>5.</b> Maven komutlarının `bin` klasöründen çalıştığı kontrol edilir.
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/f69686f3-4ee6-470a-8d4d-f017813af876" width="100%" alt="Windows ortam değişkenleri ekranını açma"><br>
      <b>6.</b> Windows ortam değişkenleri ekranı açılır.
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/4b21f491-378e-4334-af53-fb431f2e1e58" width="100%" alt="MAVEN_HOME değişkeni ekleme"><br>
      <b>7.</b> `MAVEN_HOME` değişkeni eklenir.
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/af8732a7-fe36-4071-adf4-4e5d86f1d3aa" width="100%" alt="Path içine Maven bin yolunu ekleme"><br>
      <b>8.</b> `Path` içine Maven `bin` yolu eklenir.
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/7079031f-6dab-43e7-8d8c-086d3e6e0096" width="100%" alt="Maven kurulumunu terminalde kontrol etme"><br>
      <b>9.</b> Terminalde `mvn -v` komutu ile kurulum kontrol edilir.
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/1a664323-d53a-4d38-8a2e-c97ff5f87899" width="100%" alt="LifeSync projesini GitHub üzerinden indirme"><br>
      <b>10.</b> LifeSync projesi indirilir ve proje klasörüne girilir.
    </td>
  </tr>
  <tr>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/e3f168c2-d6ea-42bd-9f7c-8444050b911a" width="100%" alt="LifeSync projesini Maven ile çalıştırma"><br>
      <b>11.</b> Proje Maven ile derlenip çalıştırılır.
    </td>
    <td align="center" width="50%">
      <img src="https://github.com/user-attachments/assets/0a013b0b-a525-4eee-9ce0-e5ea1a424230" width="100%" alt="LifeSync uygulamasının açılması"><br>
      <b>12.</b> LifeSync masaüstü uygulaması açılır.
    </td>
  </tr>
</table>

Windows'ta manuel kurulum için temel komut ve değerler:

1. Apache Maven'i resmi sitesinden indirin.
2. ZIP dosyasını örneğin şu konuma çıkarın:
   ```text
   C:\Program Files\Apache\maven
   ```
3. Windows arama kısmına **Environment Variables** yazın ve **Edit the system environment variables** seçeneğini açın.
4. **Environment Variables** butonuna tıklayın.
5. **System variables** bölümünde **New** diyerek şu değişkeni ekleyin:
   ```text
   MAVEN_HOME = C:\Program Files\Apache\maven
   ```
6. **Path** değişkenini seçip **Edit** deyin ve şunu ekleyin:
   ```text
   %MAVEN_HOME%\bin
   ```
7. Terminali kapatıp yeniden açın ve kontrol edin:
   ```bash
   mvn -v
   ```

**macOS:**
```bash
brew install maven
```

Manuel kurulum yapıldıysa Maven'in `bin` klasörü `PATH` değişkenine eklenmelidir. Örneğin:
```bash
export MAVEN_HOME=/opt/apache-maven
export PATH=$MAVEN_HOME/bin:$PATH
```

Bu satırları kalıcı yapmak için kullandığınız terminale göre `~/.zshrc` veya `~/.bashrc` dosyasına ekleyebilirsiniz.

**Ubuntu / Debian:**
```bash
sudo apt update
sudo apt install maven
```

Manuel kurulum yapıldıysa Maven'in `bin` klasörü `PATH` değişkenine eklenmelidir. Örneğin:
```bash
export MAVEN_HOME=/opt/apache-maven
export PATH=$MAVEN_HOME/bin:$PATH
```

Bu satırları kalıcı yapmak için `~/.bashrc` dosyasına ekleyip terminali yeniden başlatabilirsiniz.

Kurulumdan sonra terminali kapatıp yeniden açın ve tekrar kontrol edin:
```bash
mvn -v
```

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
