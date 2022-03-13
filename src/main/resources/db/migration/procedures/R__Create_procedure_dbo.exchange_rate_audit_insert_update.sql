-- ${flyway:timestamp}
CREATE OR ALTER PROCEDURE dbo.exchange_rate_audit_insert_update
(
	@id int OUTPUT,
	@exchange_rate_id int,
	@amount_origin decimal(20,4),
	@amount_exchange decimal(20,4),
	@rate_exchange decimal(20,4),
	@currency_origin_id int,
	@currency_exchange_id int,
	@register_user_id int,
	@register_user_fullname varchar(256),
	@register_datetime datetime,
	@active bit
)
AS
BEGIN

	INSERT INTO dbo.exchange_rate_audit
	(
		exchange_rate_id,
		amount_origin,
		amount_exchange,
		rate_exchange,
		currency_origin_id,
		currency_exchange_id,
		register_user_id,
		register_user_fullname,
		register_datetime,
		active
	)
	VALUES
	(
		@exchange_rate_id,
		@amount_origin,
		@amount_exchange,
		@rate_exchange,
		@currency_origin_id,
		@currency_exchange_id,
		@register_user_id,
		@register_user_fullname,
		@register_datetime,
		@active
	);

	SET @id = SCOPE_IDENTITY();

END;

GO