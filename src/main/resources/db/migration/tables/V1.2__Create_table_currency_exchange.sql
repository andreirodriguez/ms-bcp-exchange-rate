CREATE TABLE `currency_exchange` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `currency_origin_id` int unsigned NOT NULL,
  `currency_exchange_id` int unsigned NOT NULL,
  `mathematical_operator` varchar(1) NOT NULL,
  `rate_exchange` decimal(20,4) not null ,
  `active` bit NOT NULL,
  PRIMARY KEY `pk_currency_exchange_id` (`id`),
  CONSTRAINT `fk_currency_exchange_currency_origin_id` FOREIGN KEY(currency_origin_id) REFERENCES currency(id),
  CONSTRAINT `fk_currency_exchange_currency_exchange_id` FOREIGN KEY(currency_exchange_id) REFERENCES currency(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;