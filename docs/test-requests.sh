#!/usr/bin/env bash
# Skenario uji API. Jalankan aplikasi dulu, lalu: bash docs/test-requests.sh
URL=http://localhost:8080/api/v1/checkout
H="Content-Type: application/json"

echo -e "\n--- Skenario 1: Checkout sukses ---"
curl -s -X POST $URL -H "$H" -d '{
  "idPelanggan":"CUST-001","metodePembayaran":"CREDIT_CARD","tokenKartu":"tok_visa_valid_999",
  "keranjangData":[{"idMenu":"MENU-01","jumlah":2},{"idMenu":"MENU-02","jumlah":1}]}'

echo -e "\n\n--- Skenario 2: Stok tidak cukup ---"
curl -s -X POST $URL -H "$H" -d '{
  "idPelanggan":"CUST-001","metodePembayaran":"CREDIT_CARD","tokenKartu":"tok_visa_valid_999",
  "keranjangData":[{"idMenu":"MENU-02","jumlah":100}]}'

echo -e "\n\n--- Skenario 3: Ditolak gateway ---"
curl -s -X POST $URL -H "$H" -d '{
  "idPelanggan":"CUST-001","metodePembayaran":"CREDIT_CARD","tokenKartu":"invalid_card",
  "keranjangData":[{"idMenu":"MENU-01","jumlah":1}]}'

echo -e "\n\n--- Skenario 4: Batalkan (ganti ORD-XXXX dengan idPesanan dari skenario 1) ---"
echo "curl -X POST $URL/ORD-XXXX/batal -H \"$H\" -d '{\"alasan\":\"Pelanggan ingin mengganti menu pesanan\"}'"
echo
