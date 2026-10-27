# Ordering System (Spring Boot 3)

Backend checkout & pembayaran berdasarkan modul "Sistem Checkout & Transaksi berbasis Spring Boot 3".

## Prasyarat
- JDK 17 atau 21
- Maven 3.6+ (atau buka di IntelliJ / VS Code / Eclipse)
- (Opsional) PostgreSQL, kalau tidak mau pakai H2

## Menjalankan
### Opsi A - Tanpa install database (H2 in-memory, paling cepat)
    mvn spring-boot:run -Dspring-boot.run.profiles=h2
Data contoh (CUST-001, MENU-01, MENU-02) otomatis dimasukkan.

### Opsi B - PostgreSQL
1. Buat database: `CREATE DATABASE ordering_db;`
2. Sesuaikan username/password di `src/main/resources/application.yml`
3. Jalankan: `mvn spring-boot:run`
4. Jalankan `docs/seeder-postgres.sql` di database tersebut (setelah tabel terbentuk otomatis)

## Uji API
    bash docs/test-requests.sh
atau pakai Postman ke `POST http://localhost:8080/api/v1/checkout`.
