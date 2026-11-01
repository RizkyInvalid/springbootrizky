package com.example.orderingsystem.perpustakaan.control;

public class PinjamGagalException extends RuntimeException {

    private final boolean bisaReservasi;

    private PinjamGagalException(String message, boolean bisaReservasi) {
        super(message);
        this.bisaReservasi = bisaReservasi;
    }

    public static PinjamGagalException tidakDitemukan(String jenis) {
        return new PinjamGagalException(jenis + " tidak ditemukan", false);
    }

    public static PinjamGagalException melewatiBatas() {
        return new PinjamGagalException("Melewati batas pinjam", false);
    }

    public static PinjamGagalException stokHabis() {
        return new PinjamGagalException("Stok buku habis", true);
    }

    public boolean isBisaReservasi() {
        return bisaReservasi;
    }
}
