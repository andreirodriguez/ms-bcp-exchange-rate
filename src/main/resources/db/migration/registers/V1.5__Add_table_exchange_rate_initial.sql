INSERT INTO dbo.currency(title,symbol,active)
VALUES('Nuevos Soles','S/.',1);
INSERT INTO dbo.currency(title,symbol,active)
VALUES('Dolares','US$',1);
INSERT INTO dbo.currency(title,symbol,active)
VALUES('Euros','EUR',1);

INSERT INTO dbo.currency_exchange(currency_origin_id,currency_exchange_id,mathematical_operator,rate_exchange,active)
VALUES(1,2,'D',3.82,1);
INSERT INTO dbo.currency_exchange(currency_origin_id,currency_exchange_id,mathematical_operator,rate_exchange,active)
VALUES(2,1,'M',3.84,1);
INSERT INTO dbo.currency_exchange(currency_origin_id,currency_exchange_id,mathematical_operator,rate_exchange,active)
VALUES(1,3,'D',4.0,1);
INSERT INTO dbo.currency_exchange(currency_origin_id,currency_exchange_id,mathematical_operator,rate_exchange,active)
VALUES(3,1,'M',4.10,1);


INSERT INTO dbo.exchange_rate(amount_origin,amount_exchange,rate_exchange,currency_origin_id,currency_exchange_id,register_user_id,register_user_fullname,register_datetime,active)
VALUES(4000,1000,4,1,2,1,'Andrei Sergio Rodriguez Barriga',GETDATE(),1);
INSERT INTO dbo.exchange_rate_audit(exchange_rate_id,amount_origin,amount_exchange,rate_exchange,currency_origin_id,currency_exchange_id,register_user_id,register_user_fullname,register_datetime,active)
VALUES(1,4000,1142,3.5,1,2,1,'Andrei Sergio Rodriguez Barriga',GETDATE(),1);
INSERT INTO dbo.exchange_rate_audit(exchange_rate_id,amount_origin,amount_exchange,rate_exchange,currency_origin_id,currency_exchange_id,register_user_id,register_user_fullname,register_datetime,active)
VALUES(1,4000,1000,4,1,2,1,'Andrei Sergio Rodriguez Barriga',GETDATE(),1);