-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 10, 2026 at 08:01 AM
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
-- Database: `db_event_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `event_id` int(11) NOT NULL,
  `event_name` varchar(100) NOT NULL,
  `description` mediumtext DEFAULT NULL,
  `event_date` date NOT NULL,
  `venue` varchar(150) NOT NULL,
  `filled_slots` int(6) NOT NULL DEFAULT 0,
  `max_slots` int(6) NOT NULL,
  `status` enum('Open','Lock') NOT NULL DEFAULT 'Open',
  `accessibility` enum('Public','Private') NOT NULL DEFAULT 'Public',
  `event_code` varchar(50) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `created_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `event_name`, `description`, `event_date`, `venue`, `filled_slots`, `max_slots`, `status`, `accessibility`, `event_code`, `created_at`, `created_by`) VALUES
(1, 'Sample Event', NULL, '2026-05-13', 'GC', 0, 4, 'Open', 'Private', '1234', '2026-05-07 06:12:58', 1),
(2, 'Sample2', NULL, '2026-05-15', 'School', 0, 5, 'Open', 'Public', NULL, '2026-05-07 06:21:20', 1),
(3, 'Sample3', NULL, '2026-05-12', 'GC', 0, 5, 'Open', 'Public', NULL, '2026-05-07 06:31:18', 1),
(4, 'sample', NULL, '2026-05-11', 'sample', 0, 3, 'Lock', 'Public', NULL, '2026-05-09 06:59:43', 1),
(5, 'Grad', NULL, '2026-05-20', 'GC', 0, 6, 'Open', 'Public', NULL, '2026-05-10 04:43:56', 1),
(6, 'Party', NULL, '2026-05-20', 'GC', 0, 10, 'Open', 'Public', NULL, '2026-05-10 05:01:48', 1),
(7, 'Quiz Bee', NULL, '2026-05-23', 'GC', 0, 7, 'Open', 'Private', '888', '2026-05-10 05:13:02', 12),
(8, 'sss', NULL, '2026-05-12', 'GC', 0, 2, 'Open', 'Public', NULL, '2026-05-10 05:26:41', 1);

-- --------------------------------------------------------

--
-- Table structure for table `registrations`
--

CREATE TABLE `registrations` (
  `registration_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `attendance_status` enum('Pending','Present','Absent') NOT NULL DEFAULT 'Pending',
  `registered_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contact_number` varchar(15) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `role` enum('Admin','Staff','Registrant') NOT NULL DEFAULT 'Registrant'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `first_name`, `middle_name`, `last_name`, `email`, `contact_number`, `password`, `created_at`, `role`) VALUES
(1, 'Jaijai', NULL, 'Mnglndn', 'admin12026@gordoncollege.edu.ph', '09123456789', '0c863bdff61b51b20c005ae25d4a6eab8f8cb00536fb53d4132edcdc3cbd8c9e', '2026-04-16 10:08:44', 'Admin'),
(2, 'Pau', NULL, 'Sabando', '202611@gordoncollege.edu.ph', '09111111111', '334d016f755cd6dc58c53a86e183882f8ec14f52fb05345887c8a5edd42c87b7', '2026-05-02 15:34:47', 'Registrant'),
(9, 'Dummy', NULL, 'Dummy', 'sample@gordoncollege.edu.ph', '09333333333', '17533be2a3b9545ddfc2d6adbe7b38c2b7b790d1df10d8056b10b8752e456a68', '2026-05-03 09:13:46', 'Registrant'),
(10, 'Sample', NULL, 'Sample', 'example@gordoncollege.edu.ph', '09123456789', '74446438f96dd72002057f6c3a3cc7d32ec9f9c6def95ae4a4ded33485b8f2cc', '2026-05-03 09:24:10', 'Registrant'),
(11, 'Samplee', NULL, 'Samplee', 'sample', '09321321321', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '2026-05-08 22:42:16', 'Registrant'),
(12, 'Pau', NULL, 'Sabando', 'admin2@gordoncollege.edu.ph', '09999888777', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '2026-05-10 05:11:05', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `fk_event_creator` (`created_by`);

--
-- Indexes for table `registrations`
--
ALTER TABLE `registrations`
  ADD PRIMARY KEY (`registration_id`),
  ADD UNIQUE KEY `unique_event_user` (`event_id`,`user_id`),
  ADD KEY `fk_user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `registrations`
--
ALTER TABLE `registrations`
  MODIFY `registration_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `fk_event_creator` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `registrations`
--
ALTER TABLE `registrations`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `registrations_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
