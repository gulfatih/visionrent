# ENGLISH SECTION

# VisionRent

<div align="center">

![VisionRent Logo](https://img.shields.io/badge/VisionRent-v1.0.0-blue?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

A comprehensive **vehicle rental management system** built with Spring Boot, designed to streamline booking, inventory management, and customer operations.

[Features](#features) • [Installation](#installation) • [Usage](#usage) • [API Documentation](#api-documentation) • [Contributing](#contributing)

</div>

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Contributing](#contributing)
- [License](#license)
- [Support](#support)

---

## 🎯 Overview

**VisionRent** is a modern, scalable vehicle rental management system built with Spring Boot and Java. It provides comprehensive features for managing vehicle rentals, customer bookings, payments, and inventory tracking. The system is designed to handle multiple user roles and provide a seamless experience for both administrators and customers.

### Key Highlights
- ✅ Complete vehicle inventory management
- ✅ Advanced booking and reservation system
- ✅ Secure user authentication and authorization
- ✅ Real-time availability tracking
- ✅ Payment processing integration
- ✅ Comprehensive reporting and analytics
- ✅ RESTful API architecture
- ✅ Database-driven persistence

---

## ✨ Features

### Core Features

#### 🚗 Vehicle Management
- Add, update, and delete vehicle records
- Track vehicle availability and status
- Manage vehicle categories and specifications
- Vehicle condition and maintenance tracking
- Fleet overview and analytics

#### 📅 Booking & Reservation System
- Create and manage reservations
- Real-time availability checking
- Flexible booking date management
- Reservation status tracking
- Automated booking confirmations

#### 👥 User Management
- Customer registration and profiles
- Role-based access control (Admin, Customer, Staff)
- User authentication and authorization
- Profile management and preferences
- Account security features

#### 💳 Payment Processing
- Multiple payment methods support
- Secure payment transactions
- Invoice generation
- Payment history tracking
- Refund management

#### 📊 Reporting & Analytics
- Booking statistics and trends
- Revenue reports
- Vehicle utilization metrics
- Customer behavior analysis
- Customizable reporting

#### 🔐 Security
- JWT-based authentication
- Role-based authorization
- Encrypted password storage
- Secure API endpoints
- Input validation and sanitization

---

## 🛠️ Technology Stack

| Layer | Technology |
|-------|-----------|
| **Backend** | Java 17+, Spring Boot 3.x |
| **Framework** | Spring Framework, Spring MVC |
| **Database** | MySQL / PostgreSQL |
| **ORM** | JPA / Hibernate |
| **Build Tool** | Maven |
| **Security** | Spring Security, JWT |
| **API** | RESTful Web Services |
| **Logging** | SLF4J, Logback |
| **Testing** | JUnit, Mockito |

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Java Development Kit (JDK)**: Version 17 or higher
  - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)
  
- **Maven**: Version 3.8.0 or higher
  - Download from [Apache Maven](https://maven.apache.org/download.cgi)
  
- **Database**: MySQL 8.0+ or PostgreSQL 12+
  - Download from [MySQL](https://www.mysql.com/) or [PostgreSQL](https://www.postgresql.org/)

- **IDE** (Optional):
  - IntelliJ IDEA, Eclipse, or VS Code with Java extension

---

## 📥 Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/Fatih-gul65/visionrent.git
cd visionrent
```

### Step 2: Configure Database

Create a new database:

```sql
CREATE DATABASE visionrent;
USE visionrent;
```

### Step 3: Update Application Properties

Edit `src/main/resources/application.properties` or `application.yml`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/visionrent
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Application Configuration
spring.application.name=visionrent
server.port=8080
```

### Step 4: Build the Project

Using Maven wrapper:

```bash
./mvnw clean install
```

Or using Maven directly:

```bash
mvn clean install
```

### Step 5: Run the Application

```bash
./mvnw spring-boot:run
```

Or:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

---

## ⚙️ Configuration

### Application Properties

Key configuration options in `application.properties`:

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/api

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/visionrent
spring.datasource.username=root
spring.datasource.password=

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Logging
logging.level.root=INFO
logging.level.com.visionrent=DEBUG

# JWT Configuration
app.jwt.secret=your_secret_key_here
app.jwt.expiration=86400000
```

### Environment Variables

Set these environment variables as needed:

```bash
DATABASE_URL=jdbc:mysql://localhost:3306/visionrent
DATABASE_USER=root
DATABASE_PASSWORD=your_password
JWT_SECRET=your_secret_key
JWT_EXPIRATION=86400000
```

---

## 🚀 Usage

### Starting the Application

```bash
./mvnw spring-boot:run
```

### Sample API Endpoints

#### Authentication
- **POST** `/api/auth/login` - User login
- **POST** `/api/auth/register` - New user registration
- **POST** `/api/auth/refresh` - Refresh JWT token

#### Vehicles
- **GET** `/api/vehicles` - Get all vehicles
- **GET** `/api/vehicles/{id}` - Get vehicle details
- **POST** `/api/vehicles` - Add new vehicle (Admin only)
- **PUT** `/api/vehicles/{id}` - Update vehicle
- **DELETE** `/api/vehicles/{id}` - Delete vehicle

#### Bookings
- **GET** `/api/bookings` - Get user bookings
- **POST** `/api/bookings` - Create new booking
- **GET** `/api/bookings/{id}` - Get booking details
- **PUT** `/api/bookings/{id}` - Update booking
- **DELETE** `/api/bookings/{id}` - Cancel booking

#### Users
- **GET** `/api/users/profile` - Get user profile
- **PUT** `/api/users/profile` - Update user profile
- **GET** `/api/users` - List all users (Admin only)

---

## 📁 Project Structure

```
visionrent/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/visionrent/
│   │   │       ├── controller/        # REST Controllers
│   │   │       ├── service/           # Business Logic
│   │   │       ├── repository/        # Data Access Layer
│   │   │       ├── entity/            # JPA Entities
│   │   │       ├── dto/               # Data Transfer Objects
│   │   │       ├── config/            # Configuration Classes
│   │   │       ├── security/          # Security Configuration
│   │   │       ├── exception/         # Custom Exceptions
│   │   │       └── util/              # Utility Classes
│   │   └── resources/
│   │       ├── application.properties # Configuration
│   │       └── db/
│   │           └── migration/         # Database migrations
│   └── test/
│       └── java/                      # Unit & Integration Tests
├── pom.xml                            # Maven Configuration
├── init.sql                           # Database Initialization Script
├── mvnw & mvnw.cmd                    # Maven Wrapper Scripts
└── README.md                          # This file
```

---

## 📚 API Documentation

### Authentication

#### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"user@example.com","password":"password123"}'
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "...",
  "expiresIn": 86400000
}
```

### Vehicle Management

#### Get All Vehicles
```bash
curl -X GET http://localhost:8080/api/vehicles \
  -H "Authorization: Bearer {token}"
```

**Response:**
```json
[
  {
    "id": 1,
    "brand": "Toyota",
    "model": "Camry",
    "year": 2023,
    "licensePlate": "ABC123",
    "dailyRate": 50.00,
    "status": "AVAILABLE",
    "category": "SEDAN"
  }
]
```

#### Create New Vehicle
```bash
curl -X POST http://localhost:8080/api/vehicles \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "Honda",
    "model": "Civic",
    "year": 2023,
    "licensePlate": "XYZ789",
    "dailyRate": 45.00,
    "category": "SEDAN"
  }'
```

### Booking Management

#### Create Booking
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "vehicleId": 1,
    "startDate": "2024-02-20",
    "endDate": "2024-02-25",
    "rentalLocation": "Downtown Branch"
  }'
```

**Response:**
```json
{
  "id": 1,
  "vehicleId": 1,
  "userId": 1,
  "startDate": "2024-02-20",
  "endDate": "2024-02-25",
  "totalCost": 250.00,
  "status": "CONFIRMED",
  "createdAt": "2024-02-15T10:30:00Z"
}
```

---

## 🗄️ Database Schema

### Key Tables

#### users
```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  phone VARCHAR(20),
  role ENUM('ADMIN', 'CUSTOMER', 'STAFF'),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### vehicles
```sql
CREATE TABLE vehicles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  brand VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL,
  year INT,
  license_plate VARCHAR(20) UNIQUE NOT NULL,
  daily_rate DECIMAL(10, 2),
  status ENUM('AVAILABLE', 'RENTED', 'MAINTENANCE'),
  category VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### bookings
```sql
CREATE TABLE bookings (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT,
  vehicle_id BIGINT,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  total_cost DECIMAL(10, 2),
  status ENUM('PENDING', 'CONFIRMED', 'COMPLETED', 'CANCELLED'),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);
```

---

## 🤝 Contributing

We welcome contributions from the community! Here's how you can contribute:

### Steps to Contribute

1. **Fork the Repository**
   ```bash
   git clone https://github.com/your-username/visionrent.git
   ```

2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```

3. **Commit Your Changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```

4. **Push to the Branch**
   ```bash
   git push origin feature/AmazingFeature
   ```

5. **Open a Pull Request**

### Coding Standards

- Follow Java naming conventions
- Write meaningful commit messages
- Add unit tests for new features
- Update documentation as needed
- Keep code clean and readable

### Reporting Issues

Found a bug? Please create an issue with:
- Clear title and description
- Steps to reproduce
- Expected and actual behavior
- Screenshots if applicable
- Environment details

---

## 📄 License

This project is licensed under the **MIT License** - see the LICENSE file for details.

### MIT License Summary
- ✅ Free to use, modify, and distribute
- ✅ Include original license and copyright notice
- ✅ No warranty provided
- ✅ No liability

---

## 💬 Support

### Getting Help

- 📖 Check the [Documentation](https://github.com/Fatih-gul65/visionrent/wiki)
- 💭 Open a [Discussion](https://github.com/Fatih-gul65/visionrent/discussions)
- 🐛 Report an [Issue](https://github.com/Fatih-gul65/visionrent/issues)
- 📧 Contact: [Fatih Gül](mailto:your-email@example.com)

### Community

- Star ⭐ the repository if you find it useful
- Share with others 🔗
- Contribute improvements 🚀

---

## 🙏 Acknowledgments

- Spring Boot and Spring Framework communities
- All contributors and supporters
- Open source community

---

## 📞 Contact

**Fatih Gül**
- GitHub: [@Fatih-gul65](https://github.com/Fatih-gul65)
- Email: your-email@example.com

---

<div align="center">

Made with ❤️ by Fatih Gül

[⬆ Back to Top](#visionrent)

</div>

---

# TURKISH SECTION / TÜRKÇE BÖLÜM

---

# VisionRent

<div align="center">

![VisionRent Logo](https://img.shields.io/badge/VisionRent-v1.0.0-blue?style=for-the-badge)
![Java](https://img.shields.io/badge/Java-17+-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge)
![Lisans](https://img.shields.io/badge/Lisans-MIT-green?style=for-the-badge)

**Araç kiralama yönetim sistemi** - Spring Boot ile oluşturulmuş, rezervasyon, envanter yönetimi ve müşteri işlemlerini kolaylaştıran kapsamlı çözüm.

[Özellikler](#özellikler) • [Kurulum](#kurulum) • [Kullanım](#kullanım) • [API Belgelendirmesi](#api-belgelendirmesi) • [Katkı Sağlama](#katkı-sağlama)

</div>

---

## 📋 İçindekiler

- [Genel Bakış](#genel-bakış)
- [Özellikler](#özellikler)
- [Teknoloji Yığını](#teknoloji-yığını)
- [Ön Koşullar](#ön-koşullar)
- [Kurulum](#kurulum)
- [Yapılandırma](#yapılandırma)
- [Kullanım](#kullanım)
- [Proje Yapısı](#proje-yapısı)
- [API Belgelendirmesi](#api-belgelendirmesi)
- [Veritabanı Şeması](#veritabanı-şeması)
- [Katkı Sağlama](#katkı-sağlama)
- [Lisans](#lisans)
- [Destek](#destek)

---

## 🎯 Genel Bakış

**VisionRent**, Spring Boot ve Java ile oluşturulmuş modern, ölçeklenebilir bir araç kiralama yönetim sistemidir. Araç kiralama işlemlerini, müşteri rezervasyonlarını, ödemeleri ve envanter takibini yönetmek için kapsamlı özellikler sunmaktadır. Sistem, çoklu kullanıcı rollerini işlemek ve hem yöneticiler hem de müşteriler için sorunsuz bir deneyim sağlamak üzere tasarlanmıştır.

### Ana Özellikler
- ✅ Tam araç envanteri yönetimi
- ✅ Gelişmiş rezervasyon ve ayırma sistemi
- ✅ Güvenli kullanıcı kimlik doğrulaması ve yetkilendirmesi
- ✅ Gerçek zamanlı müsaitlik takibi
- ✅ Ödeme işleme entegrasyonu
- ✅ Kapsamlı raporlama ve analitik
- ✅ RESTful API mimarisi
- ✅ Veritabanı tabanlı veri saklama

---

## ✨ Özellikler

### Temel Özellikler

#### 🚗 Araç Yönetimi
- Araç kayıtlarını ekleme, güncelleme ve silme
- Araç müsaitliği ve durumunu takip etme
- Araç kategorileri ve özelliklerini yönetme
- Araç durumu ve bakım takibi
- Filoya genel bakış ve analitik

#### 📅 Rezervasyon ve Ayırma Sistemi
- Rezervasyonları oluşturma ve yönetme
- Gerçek zamanlı müsaitlik kontrolü
- Esnek rezervasyon tarihi yönetimi
- Rezervasyon durumu takibi
- Otomatik rezervasyon onayları

#### 👥 Kullanıcı Yönetimi
- Müşteri kaydı ve profilleri
- Rol tabanlı erişim denetimi (Yönetici, Müşteri, Personel)
- Kullanıcı kimlik doğrulaması ve yetkilendirmesi
- Profil yönetimi ve tercihler
- Hesap güvenliği özellikleri

#### 💳 Ödeme İşleme
- Birden fazla ödeme yöntemi desteği
- Güvenli ödeme işlemleri
- Fatura oluşturma
- Ödeme geçmişi takibi
- Geri ödeme yönetimi

#### 📊 Raporlama ve Analitik
- Rezervasyon istatistikleri ve trendleri
- Gelir raporları
- Araç kullanım metrikleri
- Müşteri davranış analizi
- Özelleştirilebilir raporlama

#### 🔐 Güvenlik
- JWT tabanlı kimlik doğrulaması
- Rol tabanlı yetkilendirme
- Şifrelenmiş şifre depolanması
- Güvenli API uç noktaları
- Giriş doğrulaması ve sanitasyonu

---

## 🛠️ Teknoloji Yığını

| Katman | Teknoloji |
|-------|-----------|
| **Backend** | Java 17+, Spring Boot 3.x |
| **Framework** | Spring Framework, Spring MVC |
| **Veritabanı** | MySQL / PostgreSQL |
| **ORM** | JPA / Hibernate |
| **Yapı Aracı** | Maven |
| **Güvenlik** | Spring Security, JWT |
| **API** | RESTful Web Services |
| **Logging** | SLF4J, Logback |
| **Test** | JUnit, Mockito |

---

## 📦 Ön Koşullar

Başlamadan önce sisteminizde aşağıdakiler yüklü olduğundan emin olun:

- **Java Geliştirme Kiti (JDK)**: Sürüm 17 veya üzeri
  - [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) adresinden indirin
  
- **Maven**: Sürüm 3.8.0 veya üzeri
  - [Apache Maven](https://maven.apache.org/download.cgi) adresinden indirin
  
- **Veritabanı**: MySQL 8.0+ veya PostgreSQL 12+
  - [MySQL](https://www.mysql.com/) veya [PostgreSQL](https://www.postgresql.org/) adresinden indirin

- **IDE** (İsteğe bağlı):
  - IntelliJ IDEA, Eclipse veya VS Code Java uzantısı ile

---

## 📥 Kurulum

### Adım 1: Depoyu Klonlayın

```bash
git clone https://github.com/Fatih-gul65/visionrent.git
cd visionrent
```

### Adım 2: Veritabanını Yapılandırın

Yeni bir veritabanı oluşturun:

```sql
CREATE DATABASE visionrent;
USE visionrent;
```

### Adım 3: Uygulama Özelliklerini Güncelleyin

`src/main/resources/application.properties` veya `application.yml` dosyasını düzenleyin:

```properties
# Veritabanı Yapılandırması
spring.datasource.url=jdbc:mysql://localhost:3306/visionrent
spring.datasource.username=root
spring.datasource.password=şifreniz
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate Yapılandırması
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Uygulama Yapılandırması
spring.application.name=visionrent
server.port=8080
```

### Adım 4: Projeyi Oluşturun

Maven wrapper kullanarak:

```bash
./mvnw clean install
```

Veya Maven'i doğrudan kullanarak:

```bash
mvn clean install
```

### Adım 5: Uygulamayı Çalıştırın

```bash
./mvnw spring-boot:run
```

Veya:

```bash
mvn spring-boot:run
```

Uygulama `http://localhost:8080` adresinde başlayacaktır

---

## ⚙️ Yapılandırma

### Uygulama Özellikleri

`application.properties` içinde anahtar yapılandırma seçenekleri:

```properties
# Sunucu Yapılandırması
server.port=8080
server.servlet.context-path=/api

# Veritabanı
spring.datasource.url=jdbc:mysql://localhost:3306/visionrent
spring.datasource.username=root
spring.datasource.password=

# JPA Yapılandırması
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Logging
logging.level.root=INFO
logging.level.com.visionrent=DEBUG

# JWT Yapılandırması
app.jwt.secret=gizli_anahtarınız_buraya
app.jwt.expiration=86400000
```

### Ortam Değişkenleri

Gerektiğinde bu ortam değişkenlerini ayarlayın:

```bash
DATABASE_URL=jdbc:mysql://localhost:3306/visionrent
DATABASE_USER=root
DATABASE_PASSWORD=şifreniz
JWT_SECRET=gizli_anahtarınız
JWT_EXPIRATION=86400000
```

---

## 🚀 Kullanım

### Uygulamayı Başlatma

```bash
./mvnw spring-boot:run
```

### Örnek API Uç Noktaları

#### Kimlik Doğrulama
- **POST** `/api/auth/login` - Kullanıcı girişi
- **POST** `/api/auth/register` - Yeni kullanıcı kaydı
- **POST** `/api/auth/refresh` - JWT token yenileme

#### Araçlar
- **GET** `/api/vehicles` - Tüm araçları al
- **GET** `/api/vehicles/{id}` - Araç detaylarını al
- **POST** `/api/vehicles` - Yeni araç ekle (Yalnızca Yönetici)
- **PUT** `/api/vehicles/{id}` - Araç güncelle
- **DELETE** `/api/vehicles/{id}` - Araç sil

#### Rezervasyonlar
- **GET** `/api/bookings` - Kullanıcı rezervasyonlarını al
- **POST** `/api/bookings` - Yeni rezervasyon oluştur
- **GET** `/api/bookings/{id}` - Rezervasyon detaylarını al
- **PUT** `/api/bookings/{id}` - Rezervasyon güncelle
- **DELETE** `/api/bookings/{id}` - Rezervasyonu iptal et

#### Kullanıcılar
- **GET** `/api/users/profile` - Kullanıcı profilini al
- **PUT** `/api/users/profile` - Kullanıcı profilini güncelle
- **GET** `/api/users` - Tüm kullanıcıları listele (Yalnızca Yönetici)

---

## 📁 Proje Yapısı

```
visionrent/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/visionrent/
│   │   │       ├── controller/        # REST Kontrolörleri
│   │   │       ├── service/           # İş Mantığı
│   │   │       ├── repository/        # Veri Erişim Katmanı
│   │   │       ├── entity/            # JPA Varlıkları
│   │   │       ├── dto/               # Veri Transfer Nesneleri
│   │   │       ├── config/            # Yapılandırma Sınıfları
│   │   │       ├── security/          # Güvenlik Yapılandırması
│   │   │       ├── exception/         # Özel İstisnalar
│   │   │       └── util/              # Yardımcı Sınıflar
│   │   └── resources/
│   │       ├── application.properties # Yapılandırma
│   │       └── db/
│   │           └── migration/         # Veritabanı Göçleri
│   └── test/
│       └── java/                      # Birim & Entegrasyon Testleri
├── pom.xml                            # Maven Yapılandırması
├── init.sql                           # Veritabanı Başlatma Betiği
├── mvnw & mvnw.cmd                    # Maven Wrapper Betikleri
└── README.md                          # Bu dosya
```

---

## 📚 API Belgelendirmesi

### Kimlik Doğrulama

#### Giriş
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"kullanıcı@ornek.com","password":"sifre123"}'
```

**Yanıt:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "refreshToken": "...",
  "expiresIn": 86400000
}
```

### Araç Yönetimi

#### Tüm Araçları Al
```bash
curl -X GET http://localhost:8080/api/vehicles \
  -H "Authorization: Bearer {token}"
```

**Yanıt:**
```json
[
  {
    "id": 1,
    "brand": "Toyota",
    "model": "Camry",
    "year": 2023,
    "licensePlate": "ABC123",
    "dailyRate": 50.00,
    "status": "AVAILABLE",
    "category": "SEDAN"
  }
]
```

#### Yeni Araç Oluştur
```bash
curl -X POST http://localhost:8080/api/vehicles \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "brand": "Honda",
    "model": "Civic",
    "year": 2023,
    "licensePlate": "XYZ789",
    "dailyRate": 45.00,
    "category": "SEDAN"
  }'
```

### Rezervasyon Yönetimi

#### Rezervasyon Oluştur
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "vehicleId": 1,
    "startDate": "2024-02-20",
    "endDate": "2024-02-25",
    "rentalLocation": "Şehir Merkezi Şubesi"
  }'
```

**Yanıt:**
```json
{
  "id": 1,
  "vehicleId": 1,
  "userId": 1,
  "startDate": "2024-02-20",
  "endDate": "2024-02-25",
  "totalCost": 250.00,
  "status": "CONFIRMED",
  "createdAt": "2024-02-15T10:30:00Z"
}
```

---

## 🗄️ Veritabanı Şeması

### Anahtar Tablolar

#### users (Kullanıcılar)
```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  phone VARCHAR(20),
  role ENUM('ADMIN', 'CUSTOMER', 'STAFF'),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

#### vehicles (Araçlar)
```sql
CREATE TABLE vehicles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  brand VARCHAR(100) NOT NULL,
  model VARCHAR(100) NOT NULL,
  year INT,
  license_plate VARCHAR(20) UNIQUE NOT NULL,
  daily_rate DECIMAL(10, 2),
  status ENUM('AVAILABLE', 'RENTED', 'MAINTENANCE'),
  category VARCHAR(50),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### bookings (Rezervasyonlar)
```sql
CREATE TABLE bookings (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  user_id BIGINT,
  vehicle_id BIGINT,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  total_cost DECIMAL(10, 2),
  status ENUM('PENDING', 'CONFIRMED', 'COMPLETED', 'CANCELLED'),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);
```

---

## 🤝 Katkı Sağlama

Topluluğun katkılarını memnuniyetle karşılıyoruz! İşte nasıl katkıda bulunabileceğiniz:

### Katkı Adımları

1. **Depoyu Forklayın**
   ```bash
   git clone https://github.com/kullanıcı-adınız/visionrent.git
   ```

2. **Özellik Dalı Oluşturun**
   ```bash
   git checkout -b feature/HarikaÖzellik
   ```

3. **Değişikliklerinizi Commit Edin**
   ```bash
   git commit -m 'HarikaÖzellik ekleme'
   ```

4. **Dala Gönder**
   ```bash
   git push origin feature/HarikaÖzellik
   ```

5. **Pull Request Açın**

### Kodlama Standartları

- Java adlandırma kurallarına uyun
- Anlamlı commit mesajları yazın
- Yeni özellikler için birim testleri yazın
- Gerektiğinde belgelendirmeyi güncelleyin
- Kodu temiz ve okunabilir tutun

### Sorun Bildirme

Bir hata buldu mu? Lütfen şunları içeren bir issue oluşturun:
- Açık başlık ve açıklama
- Yeniden üretme adımları
- Beklenen ve gerçek davranış
- Varsa ekran görüntüleri
- Ortam detayları

---

## 📄 Lisans

Bu proje **MIT Lisansı** altında lisanslanmıştır - ayrıntılar için LICENSE dosyasına bakınız.

### MIT Lisansı Özeti
- ✅ Kullanım, değiştirme ve dağıtım için özgür
- ✅ Orijinal lisans ve telif hakkı uyarısını ekleyin
- ✅ Garanti sağlanmaz
- ✅ Sorumluluk yok

---

## 💬 Destek

### Yardım Almak

- 📖 [Belgelendirmeyi](https://github.com/Fatih-gul65/visionrent/wiki) kontrol edin
- 💭 [Tartışma](https://github.com/Fatih-gul65/visionrent/discussions) açın
- 🐛 [Sorun](https://github.com/Fatih-gul65/visionrent/issues) bildirin
- 📧 İletişim: [Fatih Gül](mailto:email@example.com)

### Topluluk

- Depoyu beğendiyseniz yıldız ⭐ verin
- Başkalarıyla paylaşın 🔗
- Iyileştirmelere katkıda bulunun 🚀

---

## 🙏 Teşekkürler

- Spring Boot ve Spring Framework topluluklarına
- Tüm katkıda bulunanlara ve destekleyenlere
- Açık kaynak topluluğuna

---

## 📞 İletişim

**Fatih Gül**
- GitHub: [@Fatih-gul65](https://github.com/Fatih-gul65)
- Email: email@example.com

---

<div align="center">

❤️ ile Fatih Gül tarafından yapılmıştır

[⬆ Başa Dön](#visionrent)

</div>