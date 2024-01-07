package com.perpustakaan.habibie;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaksi {
    private String namaPembeli;
    private Date tanggalBeli;
    private List<Buku> daftarBuku;

    public Transaksi(String namaPembeli, List<Buku> daftarBuku) {
        this.namaPembeli = namaPembeli;
        this.tanggalBeli = new Date();
        this.daftarBuku = daftarBuku;
    }

    public void lihatDetailTransaksi() {
        System.out.println("===== Detail Transaksi =====");
        System.out.println("Nama Pembeli: " + namaPembeli);
        System.out.println("Tanggal Beli: " + new SimpleDateFormat("dd-MM-yyyy").format(tanggalBeli));
        System.out.println("===== Daftar Buku Dibeli =====");
        for (Buku buku : daftarBuku) {
            System.out.println(buku.toString());
        }
        double totalHarga = hitungTotalHarga();
        System.out.println("Total Harga: " + totalHarga);
        System.out.println("=============================");
    }

    public double hitungTotalHarga() {
        double totalHarga = 0;
        for (Buku buku : daftarBuku) {
            totalHarga += buku.harga;
        }
        return totalHarga;
    }
}
