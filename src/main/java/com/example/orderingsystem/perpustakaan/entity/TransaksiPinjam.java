package com.example.orderingsystem.perpustakaan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transaksi_pinjam")
public class TransaksiPinjam {

    public static final String DIPINJAM = "DIPINJAM";

    @Id
    @Column(name = "id_transaksi", length = 10)
    private String idTransaksi;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_anggota", nullable = false)
    private Anggota anggota;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_buku", nullable = false)
    private Buku buku;

    @Column(name = "tanggal_pinjam", nullable = false)
    private LocalDate tanggalPinjam;

    @Column(nullable = false, length = 20)
    private String status;

    protected TransaksiPinjam() {
    }

    public static TransaksiPinjam buat(Anggota anggota, Buku buku) {
        if (anggota == null || buku == null) {
            throw new IllegalArgumentException("Anggota dan buku wajib diisi");
        }

        TransaksiPinjam transaksi = new TransaksiPinjam();
        transaksi.idTransaksi = "T" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        transaksi.anggota = anggota;
        transaksi.buku = buku;
        transaksi.tanggalPinjam = LocalDate.now();
        transaksi.status = DIPINJAM;
        anggota.catatPinjaman(transaksi);
        return transaksi;
    }

    public boolean isDipinjam() {
        return DIPINJAM.equals(status);
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public Buku getBuku() {
        return buku;
    }

    public LocalDate getTanggalPinjam() {
        return tanggalPinjam;
    }

    public String getStatus() {
        return status;
    }
}
