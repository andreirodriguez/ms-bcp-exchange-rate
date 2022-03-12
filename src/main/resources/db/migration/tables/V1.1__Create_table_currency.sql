CREATE TABLE `currency` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(256) NOT NULL,
  `symbol` varchar(16) NOT NULL,
  `active` bit NOT NULL,
  PRIMARY KEY `pk_currency_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;