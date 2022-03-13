CREATE TABLE dbo.currency_exchange (
  id int IDENTITY ( 1,1 ) ,
  currency_origin_id int not null,
  currency_exchange_id int not null,
  mathematical_operator char(1) not null,
  rate_exchange decimal(20,4) not null ,
  active bit NOT NULL
);

ALTER TABLE dbo.currency_exchange ADD CONSTRAINT PK_currency_exchange_id PRIMARY KEY (id ASC);
ALTER TABLE dbo.currency_exchange ADD CONSTRAINT FK_currency_exchange_currency_origin_id FOREIGN KEY(currency_origin_id) REFERENCES currency(id);
ALTER TABLE dbo.currency_exchange ADD CONSTRAINT FK_currency_exchange_currency_exchange_id FOREIGN KEY(currency_exchange_id) REFERENCES currency(id);