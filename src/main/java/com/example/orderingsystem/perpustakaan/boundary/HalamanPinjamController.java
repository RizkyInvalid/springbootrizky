package com.example.orderingsystem.perpustakaan.boundary;

import com.example.orderingsystem.perpustakaan.control.Bukti;
import com.example.orderingsystem.perpustakaan.control.PinjamGagalException;
import com.example.orderingsystem.perpustakaan.control.PinjamService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pinjam")
@RequiredArgsConstructor
public class HalamanPinjamController {

    private static final String SESSION_ID_ANGGOTA = "idAnggota";

    private final PinjamService pinjamService;

    @GetMapping
    public String tampilHalaman(HttpSession session, Model model) {
        model.addAttribute("daftarAnggota", pinjamService.daftarAnggota());
        model.addAttribute("daftarBuku", pinjamService.daftarBuku());
        model.addAttribute("idAnggota", session.getAttribute(SESSION_ID_ANGGOTA));
        return "pinjam";
    }

    @PostMapping("/anggota")
    public String pilihAnggota(
            @RequestParam String idAnggota,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        try {
            pinjamService.validasiAnggota(idAnggota);
            session.setAttribute(SESSION_ID_ANGGOTA, idAnggota);
        } catch (PinjamGagalException ex) {
            redirectAttributes.addFlashAttribute("pesan", ex.getMessage());
        }
        return "redirect:/pinjam";
    }

    @PostMapping
    public String pilihBuku(
            @RequestParam String idBuku,
            HttpSession session,
            Model model) {
        Object anggotaTersimpan = session.getAttribute(SESSION_ID_ANGGOTA);
        if (!(anggotaTersimpan instanceof String idAnggota) || idAnggota.isBlank()) {
            model.addAttribute("pesan", "Pilih anggota sebelum meminjam buku");
            return tampilHalaman(session, model);
        }

        try {
            Bukti bukti = pinjamService.pinjamBuku(idAnggota, idBuku);
            model.addAttribute("bukti", bukti);
            return "bukti";
        } catch (PinjamGagalException ex) {
            model.addAttribute("pesan", ex.getMessage());
            model.addAttribute("bisaReservasi", ex.isBisaReservasi());
            return tampilHalaman(session, model);
        }
    }
}
