package com.example.orderingsystem.perpustakaan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "anggota")
public class Anggota {

    @Id
    @Column(name = "id_anggota", length = 10)
    private String idAnggota;

    @Column(nullable = false, length = 100)
    private String nama;

    @Column(name = "batas_pinjam", nullable = false)
    private int batasPinjam;

    @OneToMany(mappedBy = "anggota")
    private List<TransaksiPinjam> daftarPinjam = new ArrayList<>();

    protected Anggota() {
    }

    public Anggota(String idAnggota, String nama, int batasPinjam) {
        if (idAnggota == null || idAnggota.isBlank() || idAnggota.length() > 10) {
            throw new IllegalArgumentException("ID anggota wajib diisi dan maksimal 10 karakter");
        }
        if (nama == null || nama.isBlank() || nama.length() > 100) {
            throw new IllegalArgumentException("Nama wajib diisi dan maksimal 100 karakter");
        }
        if (batasPinjam < 0) {
            throw new IllegalArgumentException("Batas pinjam tidak boleh negatif");
        }
        this.idAnggota = idAnggota;
        this.nama = nama;
        this.batasPinjam = batasPinjam;
    }

    public boolean cekBatasPinjam() {
        long sedangDipinjam = daftarPinjam.stream()
                .filter(TransaksiPinjam::isDipinjam)
                .count();
        return sedangDipinjam < batasPinjam;
    }

    void catatPinjaman(TransaksiPinjam transaksi) {
        daftarPinjam.add(transaksi);
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public String getNama() {
        return nama;
    }

    public int getBatasPinjam() {
        return batasPinjam;
    }
}
