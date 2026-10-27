package com.example.orderingsystem.repository;

import com.example.orderingsystem.entity.Pembayaran;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PembayaranRepository extends JpaRepository<Pembayaran, String> {
}
