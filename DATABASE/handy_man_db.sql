-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 24, 2021 at 10:20 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `handy_man_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `chat_db`
--

CREATE TABLE `chat_db` (
  `chat_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  `worker2_id` int(11) NOT NULL,
  `job_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `chat_db`
--

INSERT INTO `chat_db` (`chat_id`, `customer_id`, `worker_id`, `worker2_id`, `job_id`) VALUES
(1, 1, 6, 7, 1),
(2, 8, 6, 7, 2),
(4, 5, 7, 0, 4),
(5, 1, 7, 6, 5),
(6, 1, 0, 0, 6),
(7, 1, 7, 0, 7);

-- --------------------------------------------------------

--
-- Table structure for table `complaint_db`
--

CREATE TABLE `complaint_db` (
  `complaint_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `subject` varchar(50) NOT NULL,
  `complaint` varchar(100) NOT NULL,
  `login_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaint_db`
--

INSERT INTO `complaint_db` (`complaint_id`, `name`, `subject`, `complaint`, `login_id`, `worker_id`) VALUES
(1, 'Babu', '', 'this is a testing complaint', 1, 6),
(2, 'ani', 'service issue', 'workers are not ready', 1, 5),
(3, 'hai', 'bhh', 'hello', 1, 7),
(4, 'hello', 'world', 'ang 23', 8, 7),
(5, 'anj', 'nithin', 'aaaa', 5, 7),
(6, 'Aravind', 'About coding', 'Have to complete fast', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `customer_registration_db`
--

CREATE TABLE `customer_registration_db` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `place` varchar(50) NOT NULL,
  `location` varchar(100) NOT NULL,
  `login_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer_registration_db`
--

INSERT INTO `customer_registration_db` (`customer_id`, `customer_name`, `phone`, `place`, `location`, `login_id`) VALUES
(1, 'Aravind', '1234567890', 'Mndy', '', 1),
(2, 'Arjyou', '1020', 'kottayam', '', 2),
(5, 'Jj', '66', 'Rt', '', 5),
(6, 'Antony', '008855', 'Kochi', '', 8);

-- --------------------------------------------------------

--
-- Table structure for table `job_db`
--

CREATE TABLE `job_db` (
  `job_id` int(11) NOT NULL,
  `job_name` varchar(50) NOT NULL,
  `job_desc` varchar(200) NOT NULL,
  `job_location` varchar(50) NOT NULL,
  `job_date` varchar(50) NOT NULL,
  `job_due_date` varchar(50) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `worker_id` int(11) NOT NULL,
  `worker2_id` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  `urgency` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `job_db`
--

INSERT INTO `job_db` (`job_id`, `job_name`, `job_desc`, `job_location`, `job_date`, `job_due_date`, `customer_id`, `worker_id`, `worker2_id`, `status`, `urgency`) VALUES
(1, 'Coding project', 'have to do project for degree students. It will be 20 projects', 'wayanad', '25 Jan 2021', '22 March 2021', 1, 6, 7, 'Completed', 2),
(2, 'name', 'desc', 'place', '2021-04-30', '11mar 2021', 8, 6, 7, 'doing', 1),
(4, 'ertffgT', 'fth crunching higher udf height', 'fgtyytrtgV', '2021-04-30', '12 mar 2021', 5, 7, 7, 'Completed', 2),
(5, 'Github', 'GItHub profile', 'Ktym', '2021-05-22', '12-01-2020', 1, 7, 6, 'doing', 1),
(7, 'test 1', 'aa', 'test', '2021-05-24', 'test', 1, 7, 0, 'requested', 0);

-- --------------------------------------------------------

--
-- Table structure for table `message_db`
--

CREATE TABLE `message_db` (
  `message_id` int(11) NOT NULL,
  `chat_id` int(11) NOT NULL,
  `message` varchar(100) NOT NULL,
  `login_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role_db`
--

CREATE TABLE `role_db` (
  `role_id` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role_db`
--

INSERT INTO `role_db` (`role_id`, `email`, `password`, `role`) VALUES
(1, 'Ani', 'ani', 'user'),
(5, 'yy', 'gk', 'user'),
(6, 'joi', 'ani', 'worker'),
(7, 'harry', 'ani', 'worker'),
(8, 'ant@', '123', 'user');

-- --------------------------------------------------------

--
-- Table structure for table `worker_registration_db`
--

CREATE TABLE `worker_registration_db` (
  `worker_id` int(11) NOT NULL,
  `worker_name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `age` int(11) NOT NULL,
  `location` varchar(50) NOT NULL,
  `qualification` varchar(100) NOT NULL,
  `specialization` varchar(200) NOT NULL,
  `link` varchar(200) NOT NULL,
  `price` varchar(50) NOT NULL,
  `login_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `worker_registration_db`
--

INSERT INTO `worker_registration_db` (`worker_id`, `worker_name`, `phone`, `age`, `location`, `qualification`, `specialization`, `link`, `price`, `login_id`) VALUES
(2, 'Joice', '9630852741', 0, 'Karti', 'poly', 'Android app Developer', 'http://github.com/abc', '100', 6),
(3, 'Harry', '9653542885', 0, 'Mndy', 'bca', 'Android app Developer', 'http://github.com/qwerty', '300', 7);

-- --------------------------------------------------------

--
-- Table structure for table `work_calender_db`
--

CREATE TABLE `work_calender_db` (
  `calender_id` int(11) NOT NULL,
  `date` varchar(20) NOT NULL,
  `job_id` int(11) NOT NULL,
  `due_date` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `work_calender_db`
--

INSERT INTO `work_calender_db` (`calender_id`, `date`, `job_id`, `due_date`) VALUES
(1, '2021-04-30', 2, '11mar 2021'),
(2, '2021-04-30', 1, '22 March 2021'),
(3, '2021-04-30', 4, '12 mar 2021'),
(4, '2021-05-22', 5, '12-01-2020'),
(5, '2021-05-22', 2, '11mar 2021'),
(6, '2021-05-22', 2, '11mar 2021'),
(7, '2021-05-24', 7, 'test'),
(8, '2021-05-24', 5, '12-01-2020');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chat_db`
--
ALTER TABLE `chat_db`
  ADD PRIMARY KEY (`chat_id`);

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
-- Indexes for table `job_db`
--
ALTER TABLE `job_db`
  ADD PRIMARY KEY (`job_id`);

--
-- Indexes for table `message_db`
--
ALTER TABLE `message_db`
  ADD PRIMARY KEY (`message_id`);

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
-- Indexes for table `work_calender_db`
--
ALTER TABLE `work_calender_db`
  ADD PRIMARY KEY (`calender_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chat_db`
--
ALTER TABLE `chat_db`
  MODIFY `chat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `complaint_db`
--
ALTER TABLE `complaint_db`
  MODIFY `complaint_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `customer_registration_db`
--
ALTER TABLE `customer_registration_db`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `job_db`
--
ALTER TABLE `job_db`
  MODIFY `job_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `message_db`
--
ALTER TABLE `message_db`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role_db`
--
ALTER TABLE `role_db`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `worker_registration_db`
--
ALTER TABLE `worker_registration_db`
  MODIFY `worker_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `work_calender_db`
--
ALTER TABLE `work_calender_db`
  MODIFY `calender_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
