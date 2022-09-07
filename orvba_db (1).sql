-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2021 at 07:09 PM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `orvba_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `complaint_db`
--

CREATE TABLE `complaint_db` (
  `complaint_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `complaint` varchar(500) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaint_db`
--

INSERT INTO `complaint_db` (`complaint_id`, `name`, `subject`, `complaint`, `user_id`) VALUES
(1, 'Dfg', 'Rfg', 'Ffggh', 11);

-- --------------------------------------------------------

--
-- Table structure for table `customer_registration_db`
--

CREATE TABLE `customer_registration_db` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(25) NOT NULL,
  `mobile` int(15) NOT NULL,
  `address` varchar(50) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_registration_db`
--

INSERT INTO `customer_registration_db` (`customer_id`, `customer_name`, `mobile`, `address`, `role_id`) VALUES
(10, '', 100, '', 10),
(11, 'Fg', 58985, '', 11),
(12, 'Dgg', 2147483647, '', 12),
(13, 'Anil', 77775588, '', 14);

-- --------------------------------------------------------

--
-- Table structure for table `order_db`
--

CREATE TABLE `order_db` (
  `order_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `work_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_db`
--

INSERT INTO `order_db` (`order_id`, `customer_id`, `work_id`) VALUES
(1, 11, 2),
(2, 11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `role_db`
--

CREATE TABLE `role_db` (
  `role_id` int(11) NOT NULL,
  `mail` varchar(25) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role_db`
--

INSERT INTO `role_db` (`role_id`, `mail`, `password`, `role`) VALUES
(10, '', '', 'customer'),
(11, 'ani', 'ani', 'customer'),
(12, 'arj', 'ani', 'customer'),
(13, 'arj', 'ani', 'worker'),
(14, 'anil', 'aaaa', 'customer'),
(15, 'joi', 'aaa', 'worker'),
(16, 'jo', 'aaa', 'worker');

-- --------------------------------------------------------

--
-- Table structure for table `worker_registration_db`
--

CREATE TABLE `worker_registration_db` (
  `worker_id` int(11) NOT NULL,
  `worker_name` varchar(30) NOT NULL,
  `w_mobile` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `worker_registration_db`
--

INSERT INTO `worker_registration_db` (`worker_id`, `worker_name`, `w_mobile`, `address`, `role_id`) VALUES
(1, 'Dgg', '89755855858', '', 13),
(2, 'Joi', '1545', '', 15),
(3, 'joi', '122', '', 16);

-- --------------------------------------------------------

--
-- Table structure for table `work_db`
--

CREATE TABLE `work_db` (
  `work_id` int(11) NOT NULL,
  `company` varchar(50) NOT NULL,
  `work_type_id` int(11) NOT NULL,
  `place` varchar(100) NOT NULL,
  `number` int(16) NOT NULL,
  `worker_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work_db`
--

INSERT INTO `work_db` (`work_id`, `company`, `work_type_id`, `place`, `number`, `worker_id`) VALUES
(1, 'HP', 7, 'Calicut', 985854428, 13),
(2, 'AJA Workshop', 6, 'Civil Station', 785426444, 15);

-- --------------------------------------------------------

--
-- Table structure for table `work_type_db`
--

CREATE TABLE `work_type_db` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work_type_db`
--

INSERT INTO `work_type_db` (`type_id`, `type_name`) VALUES
(1, 'Punchure'),
(2, 'Engine work'),
(4, 'Body works'),
(5, 'Interior works'),
(6, 'Workshop'),
(7, 'Petrol pump');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complaint_db`
--
ALTER TABLE `complaint_db`
  ADD PRIMARY KEY (`complaint_id`);

--
-- Indexes for table `customer_registration_db`
--
ALTER TABLE `customer_registration_db`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `order_db`
--
ALTER TABLE `order_db`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `role_db`
--
ALTER TABLE `role_db`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `worker_registration_db`
--
ALTER TABLE `worker_registration_db`
  ADD PRIMARY KEY (`worker_id`);

--
-- Indexes for table `work_db`
--
ALTER TABLE `work_db`
  ADD PRIMARY KEY (`work_id`);

--
-- Indexes for table `work_type_db`
--
ALTER TABLE `work_type_db`
  ADD PRIMARY KEY (`type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complaint_db`
--
ALTER TABLE `complaint_db`
  MODIFY `complaint_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `customer_registration_db`
--
ALTER TABLE `customer_registration_db`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `order_db`
--
ALTER TABLE `order_db`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `role_db`
--
ALTER TABLE `role_db`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `worker_registration_db`
--
ALTER TABLE `worker_registration_db`
  MODIFY `worker_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `work_db`
--
ALTER TABLE `work_db`
  MODIFY `work_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `work_type_db`
--
ALTER TABLE `work_type_db`
  MODIFY `type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
