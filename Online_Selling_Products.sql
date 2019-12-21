-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 21, 2019 at 03:21 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `techparts`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `ID` int(11) NOT NULL,
  `ProductName` varchar(200) NOT NULL,
  `Type` varchar(20) NOT NULL,
  `Price` int(11) NOT NULL,
  `INFO` varchar(250) DEFAULT NULL,
  `Image` varchar(150) DEFAULT NULL,
  `AvailableQuantity` int(11) NOT NULL,
  `SoldQuantity` int(11) NOT NULL,
  UNIQUE KEY `ID` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ID`, `ProductName`, `Type`, `Price`, `INFO`, `Image`, `AvailableQuantity`, `SoldQuantity`) VALUES
(2, 'AMD R9270', 'GPU', 14000, '2GB Memory', NULL, 60, 50),
(1, 'MSI X570-A Pro DDR4', 'Motherboard', 17000, 'AM4 Socket', NULL, 50, 70),
(3, 'AMD RX480', 'GPU', 20000, '4GB memory', NULL, 40, 25),
(4, 'Nvdia RTX2060', 'GPU', 40000, '8GB memroy', NULL, 90, 80),
(5, 'Team Elite 4GB', 'RAM', 3000, '1600 MHz', NULL, 60, 46),
(6, 'CROSSAIR 8GB', 'RAM', 5000, '3200 MHz', NULL, 90, 34);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
