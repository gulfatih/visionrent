<div align="center">
  <h1>🚗 VisionRent - Car Rental Backend API</h1>
  <p>
    <b>An enterprise-grade, modern RESTful API for Car Rental Systems built with Spring Boot 3.</b>
  </p>
  
  [![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://java.com/)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
  [![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue.svg)](https://www.postgresql.org/)
  [![Security](https://img.shields.io/badge/Security-Stateless%20JWT-green.svg)]()
  [![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-85EA2D.svg)]()
  
  <br>

  [🇬🇧 English Version](#-english) &nbsp; | &nbsp; [🇹🇷 Türkçe Versiyon](#-türkçe)
</div>

<br>

---

<h2 id="-english">🇬🇧 English</h2>

> VisionRent is a fully-featured, production-ready **Spring Boot REST API** designed to digitalize modern car rental operations. This project goes far beyond simple CRUD operations; it implements industry-standard best practices, performance optimizations, layered architecture, and a robust security infrastructure.

### 🚀 Enterprise Architecture & Best Practices

To ensure maximum performance, security, and maintainability, the following advanced architectural solutions have been implemented:

- **N+1 Problem Solution via `@EntityGraph`:** Instead of degrading performance with global `FetchType.EAGER`, Hibernate's infamous N+1 Select problem is solved by using highly optimized, method-specific `@EntityGraph` annotations (using optimal JOIN FETCH) for loading complex relationships (like user roles and car images) only when demanded.
- **DTO (Data Transfer Object) Pattern:** Database entities are strictly encapsulated. Communication between the client and the server is handled exclusively via DTOs. The heavy lifting of object mapping is completely automated using the high-performance **MapStruct** library, ensuring clean code and eliminating boilerplate.
- **Centralized Exception Handling:** No more spaghetti `try-catch` blocks! All runtime exceptions are caught globally using a robust `@ControllerAdvice` (*VisionRentExceptionHandler*). It maps system and security errors to a standardized, frontend-friendly JSON `ApiResponseError` format with appropriate HTTP Status Codes.
- **Stateless JWT Security:** Utilizing the modern Spring Security 3 (`SecurityFilterChain`), the application is completely stateless. Role-based access control (Admin / Customer) is securely managed at the method level using `@PreAuthorize`.
- **Custom JPQL Business Logic:** Resolving reservation conflicts requires more than basic JPA methods. Deeply customized JPQL queries handle complex logic, checking `BETWEEN` operations directly at the database layer to ensure strict car availability.
- **Cloud & DevOps Ready (Actuator):** Spring Boot **Actuator** is integrated to provide deep insights into application health (`/health`) and application info (`/info`), while sensitive metrics remain securely behind the JWT firewall.

### 📂 Project Structure (Layered Architecture)

```text
src/main/java/com/visionrent/
├── config/        # Swagger/OpenAPI configurations
├── controller/    # REST API Endpoints (Cars, Users, Auth, etc.)
├── domain/        # JPA Entities (Database Models)
├── dto/           # Data Transfer Objects representing payloads
├── exception/     # Global Error Controllers & Specific Exceptions
├── mapper/        # MapStruct mapping interfaces (Entity <-> DTO)
├── report/        # Excel reporting (Apache POI) generators
├── repository/    # Spring Data JPA Repositories & Custom JPQL
├── security/      # JWT Filters & SecurityFilterChain setups
└── service/       # Business Logic Layer implementations
```

### 🧩 Core Business Modules

- **Authentication & User Management:** Secure JWT-based Login/Register. Users can manage their profiles, while Admins have full authority over user life-cycles and role assignments.
- **Car & Image Management:** Advanced vehicle inventory. Supports detailed attributes (engine, transmission, hourly rate) and secure, UUID-based image file management protecting against orphaned data deletions.
- **Reservation Engine:** Intelligent reservation calendar that automatically calculates total pricing, prevents booking overlaps, and tracks status (`CREATED`, `CANCELED`, `DONE`).
- **Dynamic Excel Reporting:** Native extraction of real-time application data (Users, Cars, Reservations) directly into **Excel (.xlsx)** format using the `Apache POI` library.
- **Visitor Communication:** An integrated Contact Message module allowing unregistered visitors to reach out, fully managed by admins.

### 🧰 Tech Stack

- **Core:** Java 17, Spring Boot 3.3.4
- **Security:** Spring Security, JSON Web Tokens (JWT)
- **Database (ORM):** PostgreSQL, Spring Data JPA, Hibernate Core
- **Data Mapping & Boilerplate:** MapStruct, Lombok
- **Reporting:** Apache POI
- **Monitoring & API Docs:** Spring Boot Actuator, OpenAPI (Swagger)

### 🛠 Getting Started

1. Clone the repository.
2. Ensure **Java 17** is installed on your machine.
3. Access your PostgreSQL service and create an empty schema named `visionrent_db`.
4. Configure your database credentials in `src/main/resources/application.yml` (Default is port `5433`, password `1`). 
5. Build and run the project via Maven. Database tables will be generated automatically (`ddl-auto: update`).
6. **API Testing:** Once the application is running, navigate to `http://localhost:8080/swagger-ui/index.html` in your browser to access the complete Swagger documentation and easily test existing endpoints!

<br>

---

<h2 id="-türkçe">🇹🇷 Türkçe</h2>

> VisionRent, modern araç kiralama süreçlerini dijitalleştirmek üzere endüstri standartlarında kurgulanmış profesyonel bir **Spring Boot REST API** sistemidir. Bu proje sadece temel veri yazıp okuma (CRUD) işlemlerini değil; performans odaklı kodlama standartlarını, veritabanı optimizasyonlarını, katmanlı bir yapıyı ve yüksek güvenlikli mimariyi temel alır.

### 🚀 Kurumsal Mimari ve En İyi Pratikler (Best Practices)

Sistemin maksimum performans, güvenlik ve sürdürülebilirlik sağlaması için aşağıdaki ileri seviye mühendislik çözümleri uygulanmıştır:

- **@EntityGraph ile N+1 Select Çözümü:** Hibernate'in kronik performans düşmanı olan N+1 problemi; global sistemi yoran `EAGER` yapıları kullanmak yerine, yalnızca ihtiyaç duyulan Repository metotlarında özel `@EntityGraph` anotasyonları kullanılarak (optimal JOIN FETCH) çözülmüş ve milisaniyelere inen yanıt süreleri elde edilmiştir.
- **DTO Pattern ve MapStruct Otomasyonu:** Veritabanı tabloları (Entity) dış dünyaya kapatılmıştır (Encapsulation). İstemci ve sunucu arasındaki tüm trafik performanslı DTO yapılarıyla sağlanmış, bu objeler arası dönüşüm işlemi ise endüstri standardı **MapStruct** kütüphanesine devredilerek kod kalabalığı önlenmiştir.
- **Global Merkezi Hata Yönetimi:** Kodu kirleten `try-catch` blokları yerine, tüm sistem ve güvenlik hataları tek bir merkezde (`@ControllerAdvice` - *VisionRentExceptionHandler*) yakalanır. İstemciye (frontend) her zaman HTTP standartlarına uygun, temiz `ApiResponseError` JSON şemaları dönülür.
- **Stateless JWT Security:** En güncel Spring Security (`SecurityFilterChain`) standartlarıyla kurulan altyapı tamamen **Stateless (oturumsuz)** kurgulanmıştır. Mikroservis kurgularına hazır JWT mimarisi kullanılmış, Admin/Customer izinleri metod seviyesinde `@PreAuthorize` ile kilitlenmiştir.
- **Özel JPQL İş Lojikleri:** Aynı saat dilimindeki rezervasyon çakışmalarını önlemek için standart JPA yetmez. Bu sebeple veritabanı yorulmayan özel `BETWEEN` şartlı JPQL sorguları geliştirilmiştir.
- **DevOps ve İzleme (Actuator):** İleride Kubernetes/Docker mimarilerine uyum ve yük dengeleyicilerle (Load Balancer) çalışma amacıyla, sunucu sağlığını (`/health`) dökümleyen **Spring Boot Actuator** entegre edilmiştir.

### 📂 Katmanlı Mimari Hiyerarşisi (Project Structure)

Uygulamanın esnek tasarlanabilmesi (SOLID) için sağlam bir paket yapısı kullanılmıştır:
```text
src/main/java/com/visionrent/
├── config/        # Swagger/OpenAPI gibi genel konfigürasyonlar
├── controller/    # REST API Uç Noktaları (Araçlar, Üyeler, Auth vb.)
├── domain/        # Mimarideki veritabanı varlıkları (Entity tabloları)
├── dto/           # Request/Response ile taşınan objeler (Data Transfer Object)
├── exception/     # Proje geneli hata yakalayıcı (Handler) ve hata kalıpları
├── mapper/        # MapStruct veri eşleme arayüzleri
├── report/        # Excel rapor çıktısı üreten (Apache POI) modüller
├── repository/    # JpaRepository sınıfları ve JPQL ile özelleşen sorgular
├── security/      # Güvenlik (SecurityFilterChain) ve JWT doğrulama filtreleri
└── service/       # Asıl iş kurallarının (Business Logic) yazıldığı katman
```

### 🧩 Çekirdek İş Modülleri

- **Kimlik Doğrulama ve Kullanıcı Yönetimi:** JWT tabanlı güvenli giriş/kayıt sistemi. Müşteriler kendi profillerini yönetirken, Yöneticiler (Admin) tüm ekosisteme, hesaplara ve rollere hakimdir.
- **Araç ve Görsel Envanteri:** Gelişmiş araç teknik özellikleri ve dinamik fiyatlandırma. UUID mimarisiyle araç görsellerinin veri tabanında (Byte olarak) saklanması ve sistemde kullanılan görsellerin kazara web üzerinden silinmesini engelleyen katı güvenlik sorguları içerir.
- **Rezervasyon Motoru:** Fiyatı otomatik hesaplayan, tarihsel çakışmaları veritabanında denetleyen ve durumu sürekli (`CREATED`, `CANCELED`, `DONE`) loglanan hatasız sipariş sistemi.
- **Dinamik Excel Raporlama:** Sistemin mevcut Araç, Kullanıcı ve Sipariş verilerini, hiçbir üçüncü parti yazılıma ihtiyaç duymadan **Apache POI** kütüphanesi ile anlık olarak **Excel (.xlsx)** dokümanlarına dönüştürüp indirebilme yeteneği.
- **Ziyaretçi İletişim Yönetimi:** Sisteme kayıtlı olmayan kullanıcıların dahi güvenli iletişim kanalıyla mesaj gönderebildiği, Admin loglu ziyaretçi defteri modülü.

### 🧰 Kullanılan Teknolojiler

- **Çekirdek:** Java 17, Spring Boot 3.3.4
- **Güvenlik:** Spring Security, JSON Web Tokens (JWT)
- **Veritabanı:** PostgreSQL, Spring Data JPA, Hibernate Core
- **Veri Eşleme:** MapStruct, Lombok
- **Raporlama:** Apache POI
- **Sistem Takibi:** Spring Boot Actuator, OpenAPI (Swagger)

### 🛠 Kurulum ve Çalıştırma

1. Projeyi sisteminize indirin.
2. Bilgisayarınızda **Java 17**'nin kurulu olduğundan emin olun.
3. PostgreSQL servisinizi ayağa kaldırın ve `visionrent_db` adında boş bir şema yaratın.
4. `src/main/resources/application.yml` dosyasından size ait olan PostgreSQL şifresini ve portunu düzeltin (Mevcut ayar: Port `5433`, Şifre `1`).
5. Maven üzerinden projeyi derleyip çalıştırın. `ddl-auto: update` sayesinde tablolar sistem açılırken otomatik olarak kendiliğinden kurulacaktır. 
6. **API Testi:** Uygulamanız tamamen başladıktan sonra tarayıcınızda `http://localhost:8080/swagger-ui/index.html` adresine giderek OpenApi (Swagger) arayüzüne ulaşabilir ve tüm uygulamanızı doğrudan tarayıcıdan görsel bir şekilde test edebilirsiniz!