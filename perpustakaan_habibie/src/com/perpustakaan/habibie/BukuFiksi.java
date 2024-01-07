package com.perpustakaan.habibie;

public class BukuFiksi extends Buku implements Pemesanan {
    private String genre;

    public BukuFiksi(String judul, String penulis, String penerbit, double harga, String genre, int stok) {
        super(judul, penulis, penerbit, harga, stok);
        this.genre = genre;
        
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    

    public String toString() {
        return super.toString() + ", Genre: " + genre;
    }

    @Override
    public double hitungHarga(int jumlah) {
        return harga * jumlah * 0.9; // Diskon 10% untuk buku fiksi
    }
}
