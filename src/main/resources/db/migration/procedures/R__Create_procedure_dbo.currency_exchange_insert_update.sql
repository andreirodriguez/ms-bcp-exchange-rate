-- ${flyway:timestamp}
CREATE OR ALTER PROCEDURE dbo.currency_exchange_insert_update
(
	@id int OUTPUT,
	@currency_origin_id int,
	@currency_exchange_id int,
	@mathematical_operator char(1),
	@rate_exchange decimal(20,4),
	@active bit
)
AS
BEGIN

	IF NOT EXISTS (SELECT 1 FROM dbo.currency_exchange WHERE id= @id)
	BEGIN
		INSERT INTO dbo.currency_exchange
		(
			currency_origin_id,
			currency_exchange_id,
			mathematical_operator,
			rate_exchange,
			active
		)
		VALUES
		(
			@currency_origin_id,
			@currency_exchange_id,
			@mathematical_operator,
			@rate_exchange,
			@active
		);

		SET @id = SCOPE_IDENTITY();
	END
	ELSE
	BEGIN
		UPDATE dbo.currency_exchange SET
		currency_origin_id = @currency_origin_id,
		currency_exchange_id = @currency_exchange_id,
		mathematical_operator = @mathematical_operator,
		rate_exchange = @rate_exchange,
		active = @active
		WHERE id = @id;
	END;

END;

GO