-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2026 at 09:28 PM
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
  `professor` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `created_by` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `event_name`, `description`, `event_date`, `venue`, `filled_slots`, `max_slots`, `status`, `accessibility`, `event_code`, `professor`, `created_at`, `created_by`) VALUES
(1, 'Sample Event', NULL, '2026-05-13', 'GC', 0, 4, 'Open', 'Private', '1234', '', '2026-05-06 14:12:58', 1),
(2, 'Sample2', NULL, '2026-05-15', 'School', 0, 5, 'Open', 'Public', NULL, '', '2026-05-06 14:21:20', 1),
(3, 'Sample3', NULL, '2026-05-12', 'GC', 0, 5, 'Open', 'Public', NULL, '', '2026-05-06 14:31:18', 1),
(4, 'sample', NULL, '2026-05-11', 'sample', 0, 3, 'Lock', 'Public', NULL, '', '2026-05-08 14:59:43', 1),
(5, 'Grad', NULL, '2026-05-20', 'GC', 0, 6, 'Open', 'Public', NULL, '', '2026-05-09 12:43:56', 1),
(6, 'Party', NULL, '2026-05-20', 'GC', 0, 10, 'Open', 'Public', NULL, '', '2026-05-09 13:01:48', 1),
(7, 'Quiz Bee', NULL, '2026-05-23', 'GC', 0, 7, 'Open', 'Private', '888', '', '2026-05-09 13:13:02', 12),
(8, 'sss', NULL, '2026-05-12', 'GC', 0, 2, 'Open', 'Public', NULL, '', '2026-05-09 13:26:41', 1),
(9, 'eventname12', NULL, '2026-12-12', 'venuevenue', 0, 25, 'Open', 'Private', 'GC-2946', 'Pau Sab', '2026-05-10 01:13:11', 13),
(10, 'hello', NULL, '2026-10-10', 'idk', 0, 55, 'Open', 'Public', 'GC-3860', 'Pau Sab', '2026-05-10 02:53:31', 13),
(11, 'its me', NULL, '2026-11-11', 'bahay', 0, 22, 'Open', 'Public', 'GC-8137', 'Pau Sab', '2026-05-10 02:54:06', 13),
(12, 'i forgor', NULL, '2026-12-12', 'utak na walang laman na', 2, 32, 'Lock', 'Public', '', 'Pau Sab', '2026-05-10 03:29:58', 13),
(29, 'test2', NULL, '2026-10-10', 'kahit saan', 0, 21, 'Open', 'Private', 'code123', 'Pau Sab', '2026-05-12 17:00:05', 13),
(30, 'test3', NULL, '2024-10-10', 'asd', 2, 21, 'Open', 'Public', 'codez', 'Pau Sab', '2026-05-12 17:07:14', 13);

-- --------------------------------------------------------

--
-- Table structure for table `registrations`
--

CREATE TABLE `registrations` (
  `registration_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `attendance_status` enum('Pending','Present','Attended','Absent') NOT NULL DEFAULT 'Pending',
  `time_in` timestamp NULL DEFAULT NULL,
  `time_out` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `registrations`
--

INSERT INTO `registrations` (`registration_id`, `user_id`, `event_id`, `attendance_status`, `time_in`, `time_out`) VALUES
(2, 13, 11, 'Present', '2026-05-12 11:45:28', '2026-05-12 01:31:12'),
(3, 13, 10, 'Pending', '2026-05-10 05:52:22', '2026-05-12 01:31:12'),
(13, 13, 12, 'Attended', '2026-05-12 11:59:07', '2026-05-12 17:49:28'),
(20, 14, 12, 'Attended', '2026-05-12 12:53:55', '2026-05-12 12:54:05'),
(37, 13, 29, 'Attended', '2026-05-12 17:06:22', '2026-05-12 17:06:23'),
(42, 13, 30, 'Attended', '2026-05-12 17:58:41', '2026-05-12 17:58:42'),
(43, 14, 30, 'Attended', '2026-05-12 17:59:16', '2026-05-12 17:59:16');

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
(1, 'Jaijai', NULL, 'Mnglndn', 'admin12026@gordoncollege.edu.ph', '09123456789', '0c863bdff61b51b20c005ae25d4a6eab8f8cb00536fb53d4132edcdc3cbd8c9e', '2026-04-15 18:08:44', 'Admin'),
(2, 'Pau', NULL, 'Sabando', '202611@gordoncollege.edu.ph', '09111111111', '334d016f755cd6dc58c53a86e183882f8ec14f52fb05345887c8a5edd42c87b7', '2026-05-01 23:34:47', 'Registrant'),
(9, 'Dummy', NULL, 'Dummy', 'sample@gordoncollege.edu.ph', '09333333333', '17533be2a3b9545ddfc2d6adbe7b38c2b7b790d1df10d8056b10b8752e456a68', '2026-05-02 17:13:46', 'Registrant'),
(10, 'Sample', NULL, 'Sample', 'example@gordoncollege.edu.ph', '09123456789', '74446438f96dd72002057f6c3a3cc7d32ec9f9c6def95ae4a4ded33485b8f2cc', '2026-05-02 17:24:10', 'Registrant'),
(11, 'Samplee', NULL, 'Samplee', 'sample', '09321321321', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '2026-05-08 06:42:16', 'Registrant'),
(12, 'Pau', NULL, 'Sabando', 'admin2@gordoncollege.edu.ph', '09999888777', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '2026-05-09 13:11:05', 'Admin'),
(13, 'Pau', NULL, 'Sab', 'admin@gmail.com', '1234', 'admin123', '2026-05-09 23:05:12', 'Admin'),
(14, 'Richelle', NULL, 'Calba', 'sample@gmail.com', '99009090', 'password123', '2026-05-10 11:25:57', 'Registrant'),
(16, 'Neu Neym', NULL, 'Project', 'admin2468@gordoncollege.edu.ph', '0911', '2468', '2026-05-11 01:24:20', 'Registrant'),
(17, 'Paulene Richelle', NULL, 'Sabando', 'staff@gmail.com', '09478024091', 'staff1234', '2026-05-12 18:50:31', 'Staff');

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
  ADD UNIQUE KEY `unique_user_event` (`user_id`,`event_id`),
  ADD KEY `fk_user_id` (`user_id`),
  ADD KEY `registrations_ibfk_1` (`event_id`);

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
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `registrations`
--
ALTER TABLE `registrations`
  MODIFY `registration_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

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
