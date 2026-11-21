-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2026 at 05:22 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `blood_donor_radar`
--

-- --------------------------------------------------------

--
-- Table structure for table `app_user`
--

CREATE TABLE `app_user` (
  `user_id` int(11) NOT NULL,
  `full_name` varchar(100) DEFAULT NULL,
  `password_hash` varchar(100) NOT NULL,
  `role` varchar(20) DEFAULT NULL,
  `username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `app_user`
--

INSERT INTO `app_user` (`user_id`, `full_name`, `password_hash`, `role`, `username`) VALUES
(1, 'System Administrator', '$2a$10$vnLeFB9zMX56gm2AcbYqLe4MdFnga2Jn9YwNPvVsg9SwGtKL4N8n.', 'ADMIN', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `blood_bank`
--

CREATE TABLE `blood_bank` (
  `bank_id` int(11) NOT NULL,
  `bank_name` varchar(100) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `contact_number` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `blood_bank`
--

INSERT INTO `blood_bank` (`bank_id`, `bank_name`, `address`, `city`, `contact_number`) VALUES
(1, 'City Red Cross Blood Bank', 'MG Road', 'Bhubaneswar', '9876500001'),
(2, 'Capital Hospital Blood Bank', 'Unit 6', 'Bhubaneswar', '9876500002');

-- --------------------------------------------------------

--
-- Table structure for table `donation_camp`
--

CREATE TABLE `donation_camp` (
  `camp_id` int(11) NOT NULL,
  `camp_name` varchar(100) NOT NULL,
  `location` varchar(150) NOT NULL,
  `camp_date` date NOT NULL,
  `bank_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donation_camp`
--

INSERT INTO `donation_camp` (`camp_id`, `camp_name`, `location`, `camp_date`, `bank_id`) VALUES
(1, 'World Blood Donor Day Camp', 'Kalinga Stadium', '2026-06-14', 1),
(2, 'College Donation Drive', 'KIIT Campus', '2026-09-20', 2);

-- --------------------------------------------------------

--
-- Table structure for table `donation_record`
--

CREATE TABLE `donation_record` (
  `donation_id` int(11) NOT NULL,
  `donor_id` int(11) NOT NULL,
  `camp_id` int(11) DEFAULT NULL,
  `donation_date` date NOT NULL,
  `units_donated` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donation_record`
--

INSERT INTO `donation_record` (`donation_id`, `donor_id`, `camp_id`, `donation_date`, `units_donated`) VALUES
(1, 1, 1, '2026-06-14', 1),
(2, 2, 1, '2026-06-14', 1);

-- --------------------------------------------------------

--
-- Table structure for table `donor`
--

CREATE TABLE `donor` (
  `donor_id` int(11) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `blood_group` varchar(5) NOT NULL,
  `contact_number` varchar(15) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `last_donation_date` date DEFAULT NULL,
  `availability_status` varchar(20) DEFAULT 'Available',
  `bank_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `donor`
--

INSERT INTO `donor` (`donor_id`, `full_name`, `blood_group`, `contact_number`, `email`, `city`, `last_donation_date`, `availability_status`, `bank_id`) VALUES
(1, 'Amit Sahoo', 'O+', '9998887771', 'amit@example.com', 'Bhubaneswar', '2026-05-10', 'Available', 1),
(2, 'Priya Nayak', 'A+', '9998887772', 'priya@example.com', 'Bhubaneswar', '2026-03-22', 'Available', 1),
(3, 'Rahul Das', 'B-', '9998887773', 'rahul@example.com', 'Cuttack', NULL, 'Available', 2),
(5, 'KAI ', 'AB-', '7890654321', 'kaikun98@gmail.com', 'Kyoto', '2026-11-09', 'Available', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `app_user`
--
ALTER TABLE `app_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UK_3k4cplvh82srueuttfkwnylq0` (`username`);

--
-- Indexes for table `blood_bank`
--
ALTER TABLE `blood_bank`
  ADD PRIMARY KEY (`bank_id`);

--
-- Indexes for table `donation_camp`
--
ALTER TABLE `donation_camp`
  ADD PRIMARY KEY (`camp_id`),
  ADD KEY `fk_camp_bank` (`bank_id`);

--
-- Indexes for table `donation_record`
--
ALTER TABLE `donation_record`
  ADD PRIMARY KEY (`donation_id`),
  ADD KEY `fk_donation_donor` (`donor_id`),
  ADD KEY `fk_donation_camp` (`camp_id`);

--
-- Indexes for table `donor`
--
ALTER TABLE `donor`
  ADD PRIMARY KEY (`donor_id`),
  ADD KEY `fk_donor_bank` (`bank_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `app_user`
--
ALTER TABLE `app_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `blood_bank`
--
ALTER TABLE `blood_bank`
  MODIFY `bank_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `donation_camp`
--
ALTER TABLE `donation_camp`
  MODIFY `camp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `donation_record`
--
ALTER TABLE `donation_record`
  MODIFY `donation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `donor`
--
ALTER TABLE `donor`
  MODIFY `donor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `donation_camp`
--
ALTER TABLE `donation_camp`
  ADD CONSTRAINT `fk_camp_bank` FOREIGN KEY (`bank_id`) REFERENCES `blood_bank` (`bank_id`) ON DELETE CASCADE;

--
-- Constraints for table `donation_record`
--
ALTER TABLE `donation_record`
  ADD CONSTRAINT `fk_donation_camp` FOREIGN KEY (`camp_id`) REFERENCES `donation_camp` (`camp_id`) ON DELETE SET NULL,
  ADD CONSTRAINT `fk_donation_donor` FOREIGN KEY (`donor_id`) REFERENCES `donor` (`donor_id`) ON DELETE CASCADE;

--
-- Constraints for table `donor`
--
ALTER TABLE `donor`
  ADD CONSTRAINT `fk_donor_bank` FOREIGN KEY (`bank_id`) REFERENCES `blood_bank` (`bank_id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
