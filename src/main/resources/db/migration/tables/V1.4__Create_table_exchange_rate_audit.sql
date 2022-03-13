CREATE TABLE dbo.exchange_rate_audit (
  id int IDENTITY ( 1,1 ) ,
  exchange_rate_id int not null,
  amount_origin decimal(20,4) not null ,
  amount_exchange decimal(20,4) not null ,
  rate_exchange decimal(20,4) not null ,  
  currency_origin_id int not null,
  currency_exchange_id int not null,
  register_user_id int NOT NULL,
  register_user_fullname varchar(256) NOT NULL,
  register_datetime datetime NOT NULL,
  active bit NOT NULL
);

ALTER TABLE dbo.exchange_rate_audit ADD CONSTRAINT PK_exchange_rate_audit_id PRIMARY KEY (id ASC);
ALTER TABLE dbo.exchange_rate_audit ADD CONSTRAINT FK_exchange_rate_audit_exchange_rate_id FOREIGN KEY(exchange_rate_id) REFERENCES exchange_rate(id);
ALTER TABLE dbo.exchange_rate_audit ADD CONSTRAINT FK_exchange_rate_audit_currency_origin_id FOREIGN KEY(currency_origin_id) REFERENCES currency(id);
ALTER TABLE dbo.exchange_rate_audit ADD CONSTRAINT FK_exchange_rate_audit_currency_exchange_id FOREIGN KEY(currency_exchange_id) REFERENCES currency(id);
