CREATE TABLE `person` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) 