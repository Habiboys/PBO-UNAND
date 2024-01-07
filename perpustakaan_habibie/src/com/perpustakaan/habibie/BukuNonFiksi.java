package com.perpustakaan.habibie;

public class BukuNonFiksi extends Buku implements Pemesanan {
    private String kategori;

    public BukuNonFiksi(String judul, String penulis, String penerbit, double harga,String kategori, int stok) {
        super(judul, penulis, penerbit, harga, stok);
        this.kategori = kategori;
    }
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public String toString() {
        return super.toString() + ", Kategori: " + kategori;
    }

    @Override
    public double hitungHarga(int jumlah) {
        return harga * jumlah; // Tidak ada diskon untuk buku non-fiksi
    }
}
