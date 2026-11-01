package com.example.orderingsystem.perpustakaan.control;

import com.example.orderingsystem.perpustakaan.entity.Anggota;
import com.example.orderingsystem.perpustakaan.entity.AnggotaRepository;
import com.example.orderingsystem.perpustakaan.entity.Buku;
import com.example.orderingsystem.perpustakaan.entity.BukuRepository;
import com.example.orderingsystem.perpustakaan.entity.TransaksiPinjam;
import com.example.orderingsystem.perpustakaan.entity.TransaksiPinjamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PinjamService {

    private final AnggotaRepository anggotaRepository;
    private final BukuRepository bukuRepository;
    private final TransaksiPinjamRepository transaksiRepository;

    @Transactional(readOnly = true)
    public List<Buku> daftarBuku() {
        return bukuRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Anggota> daftarAnggota() {
        return anggotaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public void validasiAnggota(String idAnggota) {
        anggotaRepository.findById(idAnggota)
                .orElseThrow(() -> PinjamGagalException.tidakDitemukan("Anggota"));
    }

    @Transactional
    public Bukti pinjamBuku(String idAnggota, String idBuku) {
        Anggota anggota = anggotaRepository.findById(idAnggota)
                .orElseThrow(() -> PinjamGagalException.tidakDitemukan("Anggota"));
        Buku buku = bukuRepository.findById(idBuku)
                .orElseThrow(() -> PinjamGagalException.tidakDitemukan("Buku"));

        if (!anggota.cekBatasPinjam()) {
            throw PinjamGagalException.melewatiBatas();
        }
        if (!buku.cekStok()) {
            throw PinjamGagalException.stokHabis();
        }

        TransaksiPinjam transaksi = TransaksiPinjam.buat(anggota, buku);
        transaksiRepository.save(transaksi);
        buku.kurangiStok();
        bukuRepository.save(buku);
        return Bukti.dari(transaksi);
    }
}
