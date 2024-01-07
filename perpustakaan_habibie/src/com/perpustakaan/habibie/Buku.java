package com.perpustakaan.habibie;

public class Buku {
    protected String judul;
    protected String penulis;
    protected String penerbit;
    protected double harga;
    protected int stok;

    public Buku(String judul, String penulis, String penerbit, double harga, int stok ) {
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.harga = harga;
        this.stok = stok;
    }

    public String toString() {
        return "Judul: " + judul + ", Penulis: " + penulis + ", Penerbit: " + penerbit + ", Harga: " + harga;
    }
}

