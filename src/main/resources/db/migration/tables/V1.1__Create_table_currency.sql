CREATE TABLE dbo.currency (
  id int IDENTITY ( 1,1 ) ,
  title varchar(256) NOT NULL,
  symbol varchar(256) NOT NULL,
  active bit NOT NULL
);

ALTER TABLE dbo.currency ADD CONSTRAINT PK_currency_id PRIMARY KEY (id ASC);