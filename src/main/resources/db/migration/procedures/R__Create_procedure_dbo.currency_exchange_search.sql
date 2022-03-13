-- ${flyway:timestamp}
CREATE OR ALTER PROCEDURE dbo.currency_exchange_search
(
	@parameters_json ntext,
	@order_by varchar(128)
)
AS

--EXECUTE dbo.currency_exchange_search '{"currency_origin_id":1,"currency_exchange_id":2,"active":1}','';

DECLARE @sql nvarchar(MAX),@sql_body varchar(5500),@sql_join varchar(1000),@sql_where varchar(1500),@sql_parameter nvarchar(1024);

DECLARE @id int,@currency_origin_id int,@currency_exchange_id int,@active bit;

--FILTERS
BEGIN
	SET @id =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='id'
	);

	SET @currency_origin_id =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='currency_origin_id'
	);

	SET @currency_exchange_id =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='currency_exchange_id'
	);

	SET @active =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='@active'
	);
END;

--CONSTRUCT SQL
BEGIN
	--SQL BODY
	BEGIN
		SET @sql_body = '
		SELECT
		CUR.id,
		CUR.currency_origin_id,
		COR.title [currency_origin_title],
		COR.symbol [currency_origin_symbol],
		CUR.currency_exchange_id,
		CEX.title [currency_exchange_title],
		CEX.symbol [currency_exchange_symbol],
		CUR.mathematical_operator,
		CUR.rate_exchange,
		CUR.active';

		SET @sql_join = '
		FROM dbo.currency_exchange [CUR]
		Inner Join dbo.currency [COR] On COR.id=CUR.currency_origin_id
		Inner Join dbo.currency [CEX] On CEX.id=CUR.currency_exchange_id		';
	END;

	--SQL WHERE
	BEGIN
		SET @sql_where = '
		WHERE (1=1)';

		--id
		IF @id IS NOT NULL
		BEGIN
			SET @sql_where += + '
			AND CUR.id = @id';
		END;

		--@currency_origin_id
		IF @currency_origin_id IS NOT NULL
		BEGIN
			SET @sql_where += + '
			AND CUR.currency_origin_id = @currency_origin_id';
		END;

		--@currency_exchange_id
		IF @currency_exchange_id IS NOT NULL
		BEGIN
			SET @sql_where += + '
			AND CUR.currency_exchange_id = @currency_exchange_id';
		END;

		--active
		IF @active IS NOT NULL
		BEGIN
			SET @sql_where += + '
			AND CUR.active = @active';
		END;
	END;

	--ORDER BY
	BEGIN
		IF @order_by = ''
		BEGIN
			Set @order_by = 'id ASC';
		END;

		SET @order_by = '
		Order By ' + @order_by;
	END;
END;

--EXECUTE SQL
BEGIN
	SET @sql_parameter = '@id int,@currency_origin_id int,@currency_exchange_id int,@active bit';

	SET @sql = @sql_body + @sql_join + @sql_where + @order_by;

	--PRINT @sql;

	EXECUTE SYS.SP_EXECUTESQL @sql,@sql_parameter,@id,@currency_origin_id,@currency_exchange_id,@active;
END;

GO