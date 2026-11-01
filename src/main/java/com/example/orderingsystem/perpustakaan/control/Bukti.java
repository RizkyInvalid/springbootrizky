package com.example.orderingsystem.perpustakaan.control;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Bukti {

    private final String idTransaksi;
    private final String namaAnggota;
    private final String judulBuku;
    private final LocalDate tanggalPinjam;
    private final String status;

    public static Bukti dari(com.example.orderingsystem.perpustakaan.entity.TransaksiPinjam transaksi) {
        return new Bukti(
                transaksi.getIdTransaksi(),
                transaksi.getAnggota().getNama(),
                transaksi.getBuku().getJudul(),
                transaksi.getTanggalPinjam(),
                transaksi.getStatus());
    }
}
