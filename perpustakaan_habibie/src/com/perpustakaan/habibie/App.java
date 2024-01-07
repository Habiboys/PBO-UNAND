package com.perpustakaan.habibie;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class App {
    
    private static Connection connection;
    // private static Statement statement;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Buku> daftarBuku = new ArrayList<>();

        String jdbcUrl = "jdbc:mysql://localhost:3306/tbpbo";
        String username = "root";
        String password = "";

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Koneksi ke database berhasil.");

            while (true) {
                System.out.println("\n=== Toko Buku ===");
                System.out.println("1. Tambah Buku");
                System.out.println("2. Lihat Data Buku");
                System.out.println("3. Update Data Buku");
                System.out.println("4. Hapus Buku");
                System.out.println("5. Pesan Buku");
                System.out.println("6. Lihat Detail Transaksi");
                System.out.println("7. Keluar");

                try {
                    System.out.print("Pilih menu: ");
                    int pilihan = scanner.nextInt();
                    scanner.nextLine();


                    switch (pilihan) {
                        case 1:
                            tambahBuku(scanner, daftarBuku);
                            break;

                        case 2:
                            lihatDataBuku();
                            break;

                        case 3:
                            updateBuku(scanner, daftarBuku);
                            break;

                        case 4:
                            hapusBuku(scanner, daftarBuku);
                            break;

                        case 5:
                            pesanBuku(scanner, daftarBuku);
                            break;

                        case 6:
                            lihatDetailTransaksi(scanner, daftarBuku);
                            break;

                        case 7:
                            System.out.println("Terima kasih! Keluar dari program.");
                            System.exit(0);
                            break;

                        default:
                            System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Masukkan tidak valid. Silakan coba lagi.");
                    scanner.nextLine(); // Membersihkan buffer
                } catch (Exception e) {
                    System.out.println("Terjadi kesalahan: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Koneksi ke database ditutup.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

            if (scanner != null) {
                scanner.close();
            }
        }
    }


    private static void updateBuku(Scanner scanner, List<Buku> daftarBuku) {
        System.out.print("Masukkan judul buku yang akan diupdate: ");
        String judulUpdate = scanner.nextLine();
        try {
            Statement statementFiksi = connection.createStatement();
            String sqlFiksi = "SELECT * FROM buku_fiksi WHERE judul = '" + judulUpdate + "'";
            ResultSet resultSetFiksi = statementFiksi.executeQuery(sqlFiksi);
    
            Statement statementNonFiksi = connection.createStatement();
            String sqlNonFiksi = "SELECT * FROM buku_nonfiksi WHERE judul = '" + judulUpdate + "'";
            ResultSet resultSetNonFiksi = statementNonFiksi.executeQuery(sqlNonFiksi);
    
            if (resultSetFiksi.next()) {
                System.out.println("Data buku fiksi sebelum diupdate:");
                System.out.println("Judul: " + resultSetFiksi.getString("judul") + ", Penulis: " + resultSetFiksi.getString("penulis") +
                        ", Penerbit: " + resultSetFiksi.getString("penerbit") + ", Harga: " + resultSetFiksi.getDouble("harga") + ", Genre: " + resultSetFiksi.getString("genre") + ", Stok: " + resultSetFiksi.getInt("stok"));
    
                updateDataBuku(scanner, "buku_fiksi", judulUpdate);
            } else if (resultSetNonFiksi.next()) {
                System.out.println("Data buku nonfiksi sebelum diupdate:");
                System.out.println("Judul: " + resultSetNonFiksi.getString("judul") + ", Penulis: " + resultSetNonFiksi.getString("penulis") +
                        ", Penerbit: " + resultSetNonFiksi.getString("penerbit") + ", Harga: " + resultSetNonFiksi.getDouble("harga") + ", Kategori: " + resultSetNonFiksi.getString("kategori") + ", Stok: " + resultSetNonFiksi.getInt("stok"));
    
                updateDataBuku(scanner, "buku_nonfiksi", judulUpdate);
            } else {
                System.out.println("Buku dengan judul " + judulUpdate + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void updateDataBuku(Scanner scanner, String tableName, String judulUpdate) {
        try {
            Statement statement = connection.createStatement();
            String jenis = tableName.equals("buku_fiksi") ? "Fiksi" : "NonFiksi";
    
            System.out.print("Judul Buku Baru: ");
            String judulBaru = scanner.nextLine();
            System.out.print("Penulis Baru: ");
            String penulisBaru = scanner.nextLine();
            System.out.print("Penerbit Baru: ");
            String penerbitBaru = scanner.nextLine();
            System.out.print("Harga Baru: ");
            double hargaBaru = scanner.nextDouble();
            System.out.print("Stok Buku Baru: ");
            int stokBaru = scanner.nextInt();
            scanner.nextLine();
    
            String sql;
    
            if (jenis.equals("Fiksi")) {
                System.out.print("Genre Baru: ");
                String genreBaru = scanner.nextLine();
                sql = "UPDATE buku_fiksi SET judul = '" + judulBaru + "', penulis = '" + penulisBaru + "', penerbit = '" +
                        penerbitBaru + "', harga = " + hargaBaru + ", genre = '" + genreBaru + "', stok = " + stokBaru + " WHERE judul = '" + judulUpdate + "'";
            } else {
                System.out.print("Kategori Baru: ");
                String kategoriBaru = scanner.nextLine();
                sql = "UPDATE buku_nonfiksi SET judul = '" + judulBaru + "', penulis = '" + penulisBaru + "', penerbit = '" +
                        penerbitBaru + "', harga = " + hargaBaru + ", kategori = '" + kategoriBaru + "', stok = " + stokBaru + " WHERE judul = '" + judulUpdate + "'";
            }
    
            statement.executeUpdate(sql);
            System.out.println("Data buku berhasil diupdate.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    

    private static void tambahBuku(Scanner scanner, List<Buku> daftarBuku) {
    
        System.out.print("Judul Buku: ");
        String judul = scanner.nextLine(); 
       
        System.out.print("Penulis: ");
        String penulis = scanner.nextLine();
        System.out.print("Penerbit: ");
        String penerbit = scanner.nextLine();
        System.out.print("Harga: ");
        double harga = scanner.nextDouble();
        System.out.print("Jenis Buku (Fiksi/NonFiksi): ");
        String jenisBuku = scanner.next();
        String genre = null;
        String kategori = null;
        int stok = 0;
    
        if (jenisBuku.equalsIgnoreCase("Fiksi")) {
            System.out.print("Genre: ");
            genre = scanner.next();
        } else if (jenisBuku.equalsIgnoreCase("NonFiksi")) {
            System.out.print("Kategori: ");
            kategori = scanner.next();
        } else {
            System.out.println("Jenis buku tidak valid.");
            return;
        }
    
        System.out.print("Stok Buku: ");
        stok = scanner.nextInt();
    
        // Sebelum menambah buku, periksa apakah buku dengan judul yang sama sudah ada di database
        if (bukuSudahAda(judul)) {
            System.out.println("Buku dengan judul " + judul + " sudah ada di database.");
            return;
        }
    
        if (jenisBuku.equalsIgnoreCase("Fiksi")) {
            daftarBuku.add(new BukuFiksi(judul, penulis, penerbit, harga, genre, stok));
        } else if (jenisBuku.equalsIgnoreCase("NonFiksi")) {
            daftarBuku.add(new BukuNonFiksi(judul, penulis, penerbit, harga, kategori, stok));
        } else {
            System.out.println("Jenis buku tidak valid.");
            return;
        }
    
        try {
            Statement statement = connection.createStatement();
            String sql;
            if (jenisBuku.equalsIgnoreCase("Fiksi")) {
                sql = "INSERT INTO buku_fiksi (judul, penulis, penerbit, harga, genre, stok) VALUES ('" + judul + "', '" + penulis + "', '" + penerbit + "', " + harga + ", '" + genre + "', " + stok + ")";
            } else if (jenisBuku.equalsIgnoreCase("NonFiksi")) {
                sql = "INSERT INTO buku_nonfiksi (judul, penulis, penerbit, harga, kategori, stok) VALUES ('" + judul + "', '" + penulis + "', '" + penerbit + "', " + harga + ", '" + kategori + "', " + stok + ")";
            } else {
                System.out.println("Jenis buku tidak valid.");
                return;
            }
            statement.executeUpdate(sql);
            System.out.println("Buku berhasil ditambahkan ke database.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
  


    private static void lihatDataBuku() {
        try {
            Statement statement = connection.createStatement();
            
            // Query untuk buku fiksi
            String sqlFiksi = "SELECT * FROM buku_fiksi";
            ResultSet resultSetFiksi = statement.executeQuery(sqlFiksi);
    
            System.out.println("=================================================================================================================================");
            System.out.println("                                   Tabel Buku Fiksi");
            System.out.println("=================================================================================================================================");
            System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-10s | %-6s | %-30s | %-5s |\n", "ID", "Judul", "Penulis", "Penerbit", "Harga", "Jenis", "Detail", "Stok");
            System.out.println("=================================================================================================================================");
    
            while (resultSetFiksi.next()) {
                int id = resultSetFiksi.getInt("id");
                String judul = resultSetFiksi.getString("judul");
                String penulis = resultSetFiksi.getString("penulis");
                String penerbit = resultSetFiksi.getString("penerbit");
                double harga = resultSetFiksi.getDouble("harga");
                String jenis = "Fiksi";
                String genre = resultSetFiksi.getString("genre");
                int stok = resultSetFiksi.getInt("stok");
    
                System.out.printf("| %-3d | %-20s | %-20s | %-20s | %-10.2f | %-6s | %-30s | %-5d |\n", id, judul, penulis, penerbit, harga, jenis, "Genre: " + genre, stok);
            }
            System.out.println(" ");
            System.out.println(" ");
            // Query untuk buku nonfiksi
            String sqlNonFiksi = "SELECT * FROM buku_nonfiksi";
            ResultSet resultSetNonFiksi = statement.executeQuery(sqlNonFiksi);
    
            System.out.println("=================================================================================================================================");
            System.out.println("                                  Tabel Buku Non-Fiksi");
            System.out.println("=================================================================================================================================");
            System.out.printf("| %-3s | %-20s | %-20s | %-20s | %-10s | %-10s | %-25s | %-5s |\n", "ID", "Judul", "Penulis", "Penerbit", "Harga", "Jenis", "Detail", "Stok");
            System.out.println("=================================================================================================================================");
    
            while (resultSetNonFiksi.next()) {
                int id = resultSetNonFiksi.getInt("id");
                String judul = resultSetNonFiksi.getString("judul");
                String penulis = resultSetNonFiksi.getString("penulis");
                String penerbit = resultSetNonFiksi.getString("penerbit");
                double harga = resultSetNonFiksi.getDouble("harga");
                String jenis = "NonFiksi";
                String kategori = resultSetNonFiksi.getString("kategori");
                int stok = resultSetNonFiksi.getInt("stok");
    
                System.out.printf("| %-3d | %-20s | %-20s | %-20s | %-10.2f | %-10s | %-25s | %-5d |\n", id, judul, penulis, penerbit, harga, jenis, "Kategori: " + kategori, stok);
            }
    
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void hapusBuku(Scanner scanner, List<Buku> daftarBuku) {
        System.out.print("Masukkan judul buku yang akan dihapus: ");
        String judulHapus = scanner.nextLine();
        try {
            Statement statement = connection.createStatement();
            
            // Hapus dari buku_fiksi
            String sqlFiksi = "DELETE FROM buku_fiksi WHERE judul = '" + judulHapus + "'";
            int rowsAffectedFiksi = statement.executeUpdate(sqlFiksi);
    
            // Hapus dari buku_nonfiksi
            String sqlNonFiksi = "DELETE FROM buku_nonfiksi WHERE judul = '" + judulHapus + "'";
            int rowsAffectedNonFiksi = statement.executeUpdate(sqlNonFiksi);
    
            // Jika salah satu dari tabel berhasil menghapus, anggap buku berhasil dihapus
            if (rowsAffectedFiksi > 0 || rowsAffectedNonFiksi > 0) {
                daftarBuku.removeIf(buku -> buku.judul.equals(judulHapus));
                System.out.println("Buku dengan judul " + judulHapus + " berhasil dihapus.");
            } else {
                System.out.println("Buku dengan judul " + judulHapus + " tidak ditemukan.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    

private static void pesanBuku(Scanner scanner, List<Buku> daftarBuku) {
    System.out.print("Masukkan nama pembeli: ");
    String namaPembeli = scanner.next();
    System.out.print("Masukkan judul buku yang akan dipesan: ");
    String judulPesan = scanner.next();
    System.out.print("Masukkan jumlah buku yang akan dipesan: ");
    int jumlahPesan = scanner.nextInt();

    try {
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM buku_fiksi WHERE judul = '" + judulPesan + "' UNION SELECT * FROM buku_nonfiksi WHERE judul = '" + judulPesan + "'";
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            String jenis = resultSet.getString("genre") != null ? "Fiksi" : "NonFiksi";
            int stokBuku = resultSet.getInt("stok");

            // Membuat objek buku berdasarkan jenisnya
            Buku buku;
            if (jenis.equalsIgnoreCase("Fiksi")) {
                buku = new BukuFiksi(resultSet.getString("judul"), resultSet.getString("penulis"), resultSet.getString("penerbit"), resultSet.getDouble("harga"), resultSet.getString("genre"), stokBuku);
            } else {
                buku = new BukuNonFiksi(resultSet.getString("judul"), resultSet.getString("penulis"), resultSet.getString("penerbit"), resultSet.getDouble("harga"), resultSet.getString("kategori"), stokBuku);
            }

            if (buku instanceof Pemesanan) {
                if (stokBuku >= jumlahPesan) {
                    double totalHarga = ((Pemesanan) buku).hitungHarga(jumlahPesan);
                    System.out.println("Pesanan berhasil. Total harga: " + totalHarga);
                    Date tanggalBeli = new Date();

                    // Menyimpan data transaksi ke database
                    try {
                        Statement statementTransaksi = connection.createStatement();
                        String sqlTransaksi = "INSERT INTO transaksi (nama_pembeli, judul_buku, jumlah_pesan, tanggal_beli, total_harga) VALUES ('" + namaPembeli + "', '" + judulPesan + "','" + jumlahPesan + "', '" + new SimpleDateFormat("dd-MM-yyyy").format(tanggalBeli) + "', " + totalHarga + ")";
                        statementTransaksi.executeUpdate(sqlTransaksi);
                        System.out.println("Data transaksi berhasil disimpan ke database.");
                        
                        // Mengurangi stok buku setelah pesanan
                        int sisaStok = stokBuku - jumlahPesan;
                        String updateStokSql;
                        if (jenis.equalsIgnoreCase("Fiksi")) {
                            updateStokSql = "UPDATE buku_fiksi SET stok = " + sisaStok + " WHERE judul = '" + judulPesan + "'";
                        } else {
                            updateStokSql = "UPDATE buku_nonfiksi SET stok = " + sisaStok + " WHERE judul = '" + judulPesan + "'";
                        }
                        statementTransaksi.executeUpdate(updateStokSql);
                    } catch (SQLException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("Maaf, stok buku tidak mencukupi untuk pesanan ini. Stok saat ini: " + stokBuku);
                }
            } else {
                System.out.println("Buku dengan judul " + judulPesan + " tidak dapat dipesan.");
            }
        } else {
            System.out.println("Buku dengan judul " + judulPesan + " tidak ditemukan.");
        }
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

private static void lihatDetailTransaksi(Scanner scanner, List<Buku> daftarBuku) {
    try {
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM transaksi";
        ResultSet resultSet = statement.executeQuery(sql);

        System.out.println("=========================================================================================================");
        System.out.printf("| %-20s | %-40s | %-10s | %-20s | %-15s |\n", "Nama Pembeli", "Judul Buku", "Jumlah", "Tanggal Beli", "Total Harga");
        System.out.println("=========================================================================================================");

        while (resultSet.next()) {
            String namaPembeli = resultSet.getString("nama_pembeli");
            String judulBuku = resultSet.getString("judul_buku");
            String jumlahPesan = resultSet.getString("jumlah_pesan");
            String tanggalBeli = resultSet.getString("tanggal_beli");
            double totalHarga = resultSet.getDouble("total_harga");

            System.out.printf("| %-20s | %-40s | %-10s | %-20s | %-15.2f |\n", namaPembeli, judulBuku, jumlahPesan, tanggalBeli, totalHarga);
        }

        System.out.println("=========================================================================================================");
    } catch (SQLException e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    private static boolean bukuSudahAda(String judul) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT COUNT(*) as count FROM buku_fiksi WHERE judul = '" + judul + "' UNION SELECT COUNT(*) as count FROM buku_nonfiksi WHERE judul = '" + judul + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            int count = resultSet.getInt("count");
            return count > 0;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
}
