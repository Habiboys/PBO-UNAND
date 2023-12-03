package com.pbo.nouval;


import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // TODO: Write your code here
        Scanner scanner = new Scanner(System.in);

        try {
            // Input data dari pengguna
            System.out.println("Input Pelanggan");
            System.out.println("------------------------");
            System.out.print("Masukkan Nama Pelanggan: ");
            String namaPelanggan = scanner.nextLine();
            System.out.print("Masukkan Nomor HP: ");
            String nomorHP = scanner.nextLine();
            System.out.print("Masukkan Alamat: ");
            String alamat = scanner.nextLine();
            System.out.println("");
            // Input data pembelian barang
            System.out.println("Input Data Barang");
            System.out.println("------------------------");
            System.out.print("Kode Barang   : ");
            String kodeBarang = scanner.nextLine();
            System.out.print("Nama Barang   : ");
            String namaBarang = scanner.nextLine();
            System.out.print("Harga Barang  : ");
            double hargaBarang = scanner.nextDouble();
            System.out.print("Jumlah Beli   : ");
            int jumlahBeli = scanner.nextInt();

            System.out.println("");
            System.out.println("");

         
           Barang transaksi= new Transaksi(namaPelanggan, nomorHP, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
          
            ((Transaksi) transaksi).tampilkanTransaksi(); 

        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

  

}
