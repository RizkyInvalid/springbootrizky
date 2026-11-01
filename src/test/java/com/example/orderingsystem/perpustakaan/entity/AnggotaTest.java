package com.example.orderingsystem.perpustakaan.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnggotaTest {

    @Test
    void cekBatasPinjam_menghitungTransaksiYangMasihDipinjam() {
        Anggota anggota = new Anggota("A001", "Budi Santoso", 1);
        Buku buku = new Buku("B001", "Pemrograman Java", 2);

        assertTrue(anggota.cekBatasPinjam());
        TransaksiPinjam.buat(anggota, buku);
        assertFalse(anggota.cekBatasPinjam());
    }
}
