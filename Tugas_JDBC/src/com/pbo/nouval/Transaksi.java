package com.pbo.nouval;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaksi extends Barang implements TotalBayar {

    private String namaPelanggan;
    private String nomorHP;
    private String alamat;
    private int jumlahBeli;

    public Transaksi(String namaPelanggan, String nomorHP, String alamat, String kodeBarang,
                     String namaBarang, double hargaBarang, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        this.namaPelanggan = namaPelanggan;
        this.nomorHP = nomorHP;
        this.alamat = alamat;
        this.jumlahBeli = jumlahBeli;
    }

    @Override
    public double hitungTotalBayar() {
        return hargaBarang * jumlahBeli;
    }

    public void simpanTransaksi() {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO transaksi (nama_pelanggan, nomor_hp, alamat, kode_barang, " +
                             "nama_barang, harga_barang, jumlah_beli, tanggal_transaksi, waktu_transaksi) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            statement.setString(1, namaPelanggan);
            statement.setString(2, nomorHP);
            statement.setString(3, alamat);
            statement.setString(4, kodeBarang);
            statement.setString(5, namaBarang);
            statement.setDouble(6, hargaBarang);
            statement.setInt(7, jumlahBeli);
            statement.setDate(8, java.sql.Date.valueOf(getCurrentDate()));
            statement.setTime(9, java.sql.Time.valueOf(getCurrentTime()));

            statement.executeUpdate();
            System.out.println("Transaksi berhasil disimpan ke database.");

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public void bacaTransaksiDariDatabase() {
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM transaksi")) {

            while (resultSet.next()) {
                String namaPelanggan = resultSet.getString("nama_pelanggan");

                System.out.println("Nama Pelanggan: " + namaPelanggan);
                // Tampilkan data lainnya ...
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public void perbaruiTransaksiDiDatabase(int transaksiId, double newHargaBarang) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE transaksi SET harga_barang = ? WHERE id = ?")) {

            statement.setDouble(1, newHargaBarang);
            statement.setInt(2, transaksiId);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Data transaksi berhasil diperbarui.");
            } else {
                System.out.println("Tidak ada data transaksi dengan ID " + transaksiId);
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public void hapusTransaksiDariDatabase(int transaksiId) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM transaksi WHERE id = ?")) {

            statement.setInt(1, transaksiId);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Data transaksi berhasil dihapus.");
            } else {
                System.out.println("Tidak ada data transaksi dengan ID " + transaksiId);
            }

        } catch (SQLException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return timeFormat.format(date);
    }
}
