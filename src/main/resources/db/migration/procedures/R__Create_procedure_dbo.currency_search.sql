-- ${flyway:timestamp}
CREATE OR ALTER PROCEDURE dbo.currency_search
(
	@parameters_json ntext,
	@order_by varchar(128)
)
AS

--EXECUTE dbo.currency_search '{"active":1}','';

DECLARE @sql nvarchar(MAX),@sql_body varchar(5500),@sql_join varchar(1000),@sql_where varchar(1500),@sql_parameter nvarchar(1024);

DECLARE @id int,@active bit;

--FILTERS
BEGIN
	SET @id =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='id'
	);
	
	SET @active =
	(
		SELECT [value]
		FROM OPENJSON(@parameters_json)
		WHERE [key]='active'
	);	
END;

--CONSTRUCT SQL
BEGIN
	--SQL BODY
	BEGIN
		SET @sql_body = '
		SELECT
		CUR.id,
		CUR.title,
		CUR.symbol,
		CUR.active';

		SET @sql_join = '
		FROM dbo.currency [CUR]';
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
	SET @sql_parameter = '@id int,@active bit';

	SET @sql = @sql_body + @sql_join + @sql_where + @order_by;

	--PRINT @sql;

	EXECUTE SYS.SP_EXECUTESQL @sql,@sql_parameter,@id,@active;
END;

GO