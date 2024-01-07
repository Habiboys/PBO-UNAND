-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Jan 2024 pada 11.55
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tbpbo`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku_fiksi`
--

CREATE TABLE `buku_fiksi` (
  `id` int(11) NOT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `penulis` varchar(255) DEFAULT NULL,
  `penerbit` varchar(255) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `buku_fiksi`
--

INSERT INTO `buku_fiksi` (`id`, `judul`, `penulis`, `penerbit`, `harga`, `genre`, `stok`) VALUES
(1, 'haha', 'irfan', 'padang', 10000, 'action', 20);

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku_nonfiksi`
--

CREATE TABLE `buku_nonfiksi` (
  `id` int(11) NOT NULL,
  `judul` varchar(255) DEFAULT NULL,
  `penulis` varchar(255) DEFAULT NULL,
  `penerbit` varchar(255) DEFAULT NULL,
  `harga` double DEFAULT NULL,
  `kategori` varchar(255) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id` int(11) NOT NULL,
  `nama_pembeli` varchar(255) DEFAULT NULL,
  `judul_buku` varchar(255) DEFAULT NULL,
  `jumlah_pesan` int(11) DEFAULT NULL,
  `tanggal_beli` varchar(255) DEFAULT NULL,
  `total_harga` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id`, `nama_pembeli`, `judul_buku`, `jumlah_pesan`, `tanggal_beli`, `total_harga`) VALUES
(1, 'ucok', 'haha', 80, '04-01-2024', 720000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `buku_fiksi`
--
ALTER TABLE `buku_fiksi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `buku_nonfiksi`
--
ALTER TABLE `buku_nonfiksi`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `buku_fiksi`
--
ALTER TABLE `buku_fiksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `buku_nonfiksi`
--
ALTER TABLE `buku_nonfiksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
