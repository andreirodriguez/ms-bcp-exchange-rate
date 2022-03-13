-- ${flyway:timestamp}
DELIMITER $$
DROP PROCEDURE IF EXISTS `currency_search`$$
CREATE PROCEDURE `currency_search`
(
	in parameters_json json,
	in order_by varchar(128)
)
-- CALL currency_search('{"active":1}','');
BEGIN
	DECLARE sql_body varchar(5500); DECLARE sql_join varchar(1000); DECLARE sql_where varchar(1500);

	-- FILTERS
	BEGIN
		SET @id =
		(
			SELECT JSON_EXTRACT(parameters_json, '$.id')
		);
        
		SET @active =
		(
			SELECT JSON_EXTRACT(parameters_json, '$.active')
		);        
	END;

	-- CONSTRUCT SQL
	BEGIN
		-- SQL BODY
		BEGIN
			SET sql_body = "
			SELECT
			CUS.id,
			CUS.title,
			CUS.symbol,
			CUS.active";

			SET sql_join = "
			FROM currency CUS";
		END;

		-- SQL WHERE
		BEGIN
			SET sql_where = "
			WHERE (1=1)";

			-- id
			IF @id IS NOT NULL THEN
				SET sql_where = CONCAT(sql_where,"
				AND CUS.id = @id");
			END IF;
            
			-- active
			IF @active IS NOT NULL THEN
				SET sql_where = CONCAT(sql_where,"
				AND CUS.active = @active");
			END IF;            
		END;

		-- ORDER BY
		BEGIN
			IF order_by = "" THEN
				Set order_by = "id ASC";
			END IF;

			SET order_by = CONCAT("
			Order By ",order_by);
		END;
	END;

	-- EXECUTE SQL
	BEGIN
		SET @sql = CONCAT(sql_body,sql_join,sql_where,order_by);

		-- SELECT @sql;

		PREPARE stmt FROM @sql;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
	END;
END;

