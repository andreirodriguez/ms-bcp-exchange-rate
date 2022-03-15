-- ${flyway:timestamp}
CREATE OR ALTER PROCEDURE dbo.exchange_rate_find_all
(
	@parameters_json ntext,
	@page_active int,
	@show_quantity int,
	@order_by varchar(128),
	@total_registers int OUTPUT
)
AS

--EXECUTE dbo.exchange_rate_find_all '{"currency_origin_id":1,"currency_exchange_id":2,"register_datetime_until":"12/03/2022","register_datetime_to":"13/03/2022"}',1,10,'',NULL;

DECLARE @sql nvarchar(MAX),@sql_count nvarchar(MAX),@sql_body varchar(5500),@sql_join varchar(1000),@sql_where varchar(1500),@sql_parameter nvarchar(1024),@sql_parameter_count nvarchar(1024);

DECLARE @rownum_from varchar(10),@rownum_to varchar(10);

DECLARE @id int,@currency_origin_id int,@currency_exchange_id int,@register_datetime_until varchar(10),@register_datetime_to varchar(10);

--VARIABLES
BEGIN
	SET @rownum_from = Cast((((@page_active - 1) * @show_quantity) + 1) AS varchar);

	SET @rownum_to = Cast((@page_active * @show_quantity) AS varchar);
END;

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

	SET @register_datetime_until =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='register_datetime_until'
	);

	SET @register_datetime_to =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='register_datetime_to'
	);
END;

--CONSTRUCT SQL
BEGIN
	--SQL BODY
	BEGIN
		SET @sql_body = '
		SELECT
		EXC.id,
		EXC.amount_origin,
		EXC.amount_exchange,
		EXC.rate_exchange,
		EXC.currency_origin_id,
		COR.title [currency_origin_title],
		COR.symbol [currency_origin_symbol],
		EXC.currency_exchange_id,
		CEX.title [currency_exchange_title],
		CEX.symbol [currency_exchange_symbol],
		EXC.register_user_id,
		EXC.register_user_fullname,
		EXC.register_datetime,
		EXC.active';

		SET @sql_join = '
		FROM dbo.exchange_rate [EXC]
		Inner Join dbo.currency [COR] On COR.id=EXC.currency_origin_id
		Inner Join dbo.currency [CEX] On CEX.id=EXC.currency_exchange_id
		';
	END;

	--SQL WHERE
	BEGIN
		SET @sql_where = '
		WHERE (1=1)';

		--id
		IF @id IS NOT NULL
		BEGIN
			SET @sql_where += + '
			AND EXC.id = @id';
		END;

		--@currency_origin_id
		IF @currency_origin_id IS NOT NULL
		BEGIN
			SET @sql_where += + '
			AND EXC.currency_origin_id = @currency_origin_id';
		END;

		--@currency_exchange_id
		IF @currency_exchange_id IS NOT NULL
		BEGIN
			SET @sql_where += + '
			AND EXC.currency_exchange_id = @currency_exchange_id';
		END;

		--@register_datetime
		IF @register_datetime_until IS NOT NULL AND @register_datetime_to IS NOT NULL
		BEGIN
			SET @sql_where += + '
			AND 
			(
				EXC.register_datetime >= Convert(DATETIME,@register_datetime_until,102)
				AND
				EXC.register_datetime < Convert(DATETIME,@register_datetime_to,102) + 1
			)
			';
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
	SET @total_registers = 0;

	SET @sql_parameter = '@id int,@currency_origin_id int,@currency_exchange_id int,@register_datetime_until varchar(10),@register_datetime_to varchar(10)';

	SET @sql_parameter_count = @sql_parameter + ',@total_registers int output';

	SET @sql = @sql_body + @sql_join + @sql_where;

	IF @page_active > 0
	BEGIN
		SET @sql_count = N'SELECT @total_registers = COUNT(1) ' + @sql_join + @sql_where;

		EXECUTE SYS.SP_EXECUTESQL @sql_count,@sql_parameter_count,@id,@currency_origin_id,@currency_exchange_id,@register_datetime_until,@register_datetime_to,@total_registers=@total_registers OUTPUT;

		SET @sql = 'SELECT * FROM (SELECT ROW_NUMBER() OVER (' + @order_by + ') [ROWNUM],* FROM (' + @sql + ') [TMP] ) [PAG] WHERE ROWNUM >= ' + @rownum_from + ' And ROWNUM <= ' + @rownum_to; 
	END
	ELSE
	BEGIN
		SET @sql += @order_by;
	END;

	--PRINT @sql;

	EXECUTE SYS.SP_EXECUTESQL @sql,@sql_parameter,@id,@currency_origin_id,@currency_exchange_id,@register_datetime_until,@register_datetime_to;
END;

GO