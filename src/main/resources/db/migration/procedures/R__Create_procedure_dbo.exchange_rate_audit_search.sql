-- ${flyway:timestamp}
CREATE OR ALTER PROCEDURE dbo.exchange_rate_audit_search
(
	@parameters_json ntext,
	@order_by varchar(128)
)
AS

--EXECUTE dbo.exchange_rate_audit_search '{"id":1}','';

DECLARE @sql nvarchar(MAX),@sql_body varchar(5500),@sql_join varchar(1000),@sql_where varchar(1500),@sql_parameter nvarchar(1024);

DECLARE @id int;

--FILTERS
BEGIN
	SET @id =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='id'
	);
END;

--CONSTRUCT SQL
BEGIN
	--SQL BODY
	BEGIN
		SET @sql_body = '
		SELECT
		EXC.id,
		EXC.exchange_rate_id,
		EXC.amount_origin,
		EXC.amount_exchange,
		EXC.rate_exchange,
		EXC.currency_origin_id,
		EXC.currency_exchange_id,
		EXC.register_user_id,
		EXC.register_user_fullname,
		EXC.register_datetime,
		EXC.active';

		SET @sql_join = '
		FROM dbo.exchange_rate_audit [EXC]';
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
	SET @sql_parameter = '@id int';

	SET @sql = @sql_body + @sql_join + @sql_where + @order_by;

	--PRINT @sql;

	EXECUTE SYS.SP_EXECUTESQL @sql,@sql_parameter,@id;
END;

GO