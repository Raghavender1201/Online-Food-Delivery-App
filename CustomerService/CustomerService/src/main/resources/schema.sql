CREATE TABLE IF NOT EXISTS `customer` (
  `id` int AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `address` varchar(20) NOT NULL
);