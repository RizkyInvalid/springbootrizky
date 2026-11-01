# Ordering System & Perpustakaan BCE (Spring Boot 3)

Aplikasi ini mempertahankan backend checkout/pembayaran dan menambahkan studi kasus peminjaman buku dengan arsitektur Boundary-Control-Entity (BCE).

## Prasyarat
- JDK 17 atau 21
- Maven 3.6+ (atau buka di IntelliJ / VS Code / Eclipse)
- (Opsional) PostgreSQL, kalau tidak mau pakai H2

## Menjalankan
### Opsi A - Tanpa install database (H2 in-memory, paling cepat)
    mvn spring-boot:run -Dspring-boot.run.profiles=h2
Data contoh ordering dan perpustakaan otomatis dimasukkan. Buka `http://localhost:8080/pinjam` untuk mencoba peminjaman.

### Opsi B - PostgreSQL
1. Buat database: `CREATE DATABASE ordering_db;`
2. Sesuaikan username/password di `src/main/resources/application.yml`
3. Jalankan: `mvn spring-boot:run`
4. Jalankan `docs/seeder-postgres.sql` di database tersebut (setelah tabel terbentuk otomatis)

## Modul perpustakaan (BCE)

- `perpustakaan/boundary`: `HalamanPinjamController` menerima request dan merender template.
- `perpustakaan/control`: `PinjamService` mengoordinasikan use case dalam transaksi; `Bukti` adalah data hasil dan `PinjamGagalException` menangani alur alternatif.
- `perpustakaan/entity`: `Anggota`, `Buku`, `TransaksiPinjam`, dan repository JPA masing-masing.
- `src/main/resources/templates`: halaman daftar buku/pilih anggota dan bukti peminjaman.

Alur `POST /pinjam` memeriksa anggota, batas pinjaman, dan stok, menyimpan transaksi, lalu mengurangi stok. Buku tidak tersedia menampilkan pesan dengan opsi reservasi; anggota yang melewati batas dan data yang tidak ditemukan ditangani sebagai alur gagal. Pilih anggota dari daftar pada halaman sebelum meminjam. Pemilihan anggota ini hanya untuk demo pembelajaran dan bukan autentikasi produksi.

Contoh data H2: anggota `A001` (batas 2) dan `A002` (batas 1); buku `B001` (stok 3), `B002` (stok 0), dan `B003` (stok 2).

## Uji API ordering
    bash docs/test-requests.sh
atau pakai Postman ke `POST http://localhost:8080/api/v1/checkout`.

## Pengujian
    mvn test
