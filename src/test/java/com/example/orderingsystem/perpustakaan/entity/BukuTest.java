package com.example.orderingsystem.perpustakaan.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BukuTest {

    @Test
    void kurangiStok_mengurangiStokSampaiHabisDanMenolakStokNegatif() {
        Buku buku = new Buku("B001", "Pemrograman Java", 1);

        assertTrue(buku.cekStok());
        buku.kurangiStok();

        assertEquals(0, buku.getStok());
        assertFalse(buku.cekStok());
        assertThrows(IllegalStateException.class, buku::kurangiStok);
    }

    @Test
    void konstruktor_menolakStokNegatif() {
        assertThrows(IllegalArgumentException.class, () -> new Buku("B001", "Pemrograman Java", -1));
    }
}
