package com.example.orderingsystem.perpustakaan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buku")
public class Buku {

    @Id
    @Column(name = "id_buku", length = 10)
    private String idBuku;

    @Column(name = "judul", length = 200, nullable = false)
    private String judul;

    @Column(nullable = false)
    private int stok;

    protected Buku() {
    }

    public Buku(String idBuku, String judul, int stok) {
        if (idBuku == null || idBuku.isBlank() || idBuku.length() > 10) {
            throw new IllegalArgumentException("ID buku wajib diisi dan maksimal 10 karakter");
        }
        if (judul == null || judul.isBlank() || judul.length() > 200) {
            throw new IllegalArgumentException("Judul wajib diisi dan maksimal 200 karakter");
        }
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif");
        }
        this.idBuku = idBuku;
        this.judul = judul;
        this.stok = stok;
    }

    public boolean cekStok() {
        return stok > 0;
    }

    public void kurangiStok() {
        if (!cekStok()) {
            throw new IllegalStateException("Stok buku sudah habis");
        }
        stok--;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public int getStok() {
        return stok;
    }
}
