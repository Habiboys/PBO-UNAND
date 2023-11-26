package com.pbo.nouval;
class Barang {
    protected String namaBarang;
    protected double hargaBarang;

    public Barang(String namaBarang, double hargaBarang) {
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }

    public void tampilkanInfo() {
        System.out.println("Nama Barang: " + namaBarang);
        System.out.println("Harga Barang: " + hargaBarang);
    }
}