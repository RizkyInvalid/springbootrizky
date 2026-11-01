-- Data seeder (hanya dieksekusi pada profile "h2")
INSERT INTO pelanggan (id_pelanggan, nama, email, nomor_telepon, alamat)
VALUES ('CUST-001', 'Budi Santoso', 'budi@example.com', '08123456789', 'Jl. Sudirman No. 45, Jakarta');

INSERT INTO menu (id_menu, nama_menu, harga, stok) VALUES ('MENU-01', 'Kopi Susu Gula Aren', 18000.0, 20);
INSERT INTO menu (id_menu, nama_menu, harga, stok) VALUES ('MENU-02', 'Croissant Butter', 25000.0, 10);

INSERT INTO anggota (id_anggota, nama, batas_pinjam) VALUES ('A001', 'Budi Santoso', 2);
INSERT INTO anggota (id_anggota, nama, batas_pinjam) VALUES ('A002', 'Sari Dewi', 1);

INSERT INTO buku (id_buku, judul, stok) VALUES ('B001', 'Pemrograman Java', 3);
INSERT INTO buku (id_buku, judul, stok) VALUES ('B002', 'Rekayasa Perangkat Lunak', 0);
INSERT INTO buku (id_buku, judul, stok) VALUES ('B003', 'Basis Data', 2);
