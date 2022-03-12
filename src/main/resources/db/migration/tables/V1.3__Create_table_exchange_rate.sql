CREATE TABLE `exchange_rate` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `amount_origin` decimal(20,4) not null ,
  `amount_exchange` decimal(20,4) not null ,
  `rate_exchange` decimal(20,4) not null ,
  `currency_origin_id` int unsigned not null ,
  `currency_exchange_id` int unsigned not null ,
  `register_user_id` int unsigned NOT NULL,
  `register_user_fullname` varchar(256) NOT NULL,
  `register_datetime` datetime NOT NULL,
  `active` bit NOT NULL,
  PRIMARY KEY `pk_exchange_rate_id` (`id`),
  CONSTRAINT `fk_exchange_rate_currency_origin_id` FOREIGN KEY(currency_origin_id) REFERENCES currency(id),
  CONSTRAINT `fk_exchange_rate_currency_exchange_id` FOREIGN KEY(currency_exchange_id) REFERENCES currency(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;