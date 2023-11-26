package com.pbo.nouval;

class Transaksi extends Barang implements TotalBayar {
    private int noFaktur;
    private String namaPelanggan;
    private int jumlahBeli;

    public Transaksi(int noFaktur, String namaPelanggan, String namaBarang, double hargaBarang, int jumlahBeli) {
        super(namaBarang, hargaBarang);
        this.noFaktur = noFaktur;
        this.namaPelanggan = namaPelanggan;
        this.jumlahBeli = jumlahBeli;
    }

    @Override
    public double hitungTotalBayar() {
        return hargaBarang * jumlahBeli;
    }

    public void tampilkanTransaksi() {
        System.out.println("No Faktur: " + noFaktur);
        System.out.println("Nama Pelanggan: " + namaPelanggan);
        tampilkanInfo(); // Memanggil metode dari kelas induk
        System.out.println("Jumlah Beli: " + jumlahBeli);
        System.out.println("Total Bayar: " + hitungTotalBayar());
    }
}