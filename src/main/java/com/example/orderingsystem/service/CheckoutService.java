package com.example.orderingsystem.service;

import com.example.orderingsystem.dto.request.CheckoutRequest;
import com.example.orderingsystem.dto.response.CheckoutResponse;

public interface CheckoutService {
    CheckoutResponse prosesCheckout(CheckoutRequest request);

    void batalkanTransaksi(String idPesanan, String alasan);
}
