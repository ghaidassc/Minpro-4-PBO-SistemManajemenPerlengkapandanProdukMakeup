-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 12:14 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_makeup`
--

-- --------------------------------------------------------

--
-- Table structure for table `makeup`
--

CREATE TABLE `makeup` (
  `id` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `merk` varchar(100) NOT NULL,
  `kategori` varchar(31) NOT NULL,
  `harga` double NOT NULL,
  `stok` int NOT NULL,
  `shade` varchar(50) DEFAULT NULL,
  `material` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `makeup`
--

INSERT INTO `makeup` (`id`, `nama`, `merk`, `kategori`, `harga`, `stok`, `shade`, `material`) VALUES
('AL01', 'Beauty Blender Original', 'Beautyblender', 'PERLENGKAPAN', 185000, 2, NULL, 'Hydrophilic Polyurethane'),
('MK01', 'Superstay Matte Ink', 'Maybelline', 'PRODUK', 125000, 1, '115 Founder', NULL),
('MK02', 'Cushion Flawless Matte SPF 35', 'Wardah', 'PRODUK', 85000, 1, '02 Natural Beige', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `makeup`
--
ALTER TABLE `makeup`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
