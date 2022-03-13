-- ${flyway:timestamp}
CREATE OR ALTER PROCEDURE dbo.currency_insert_update
(
	@id int OUTPUT,
	@title varchar(256),
	@symbol varchar(256),
	@active bit
)
AS
BEGIN

	IF NOT EXISTS (SELECT 1 FROM dbo.currency WHERE id= @id)
	BEGIN
		INSERT INTO dbo.currency
		(
			title,
			symbol,
			active
		)
		VALUES
		(
			@title,
			@symbol,
			@active
		);

		SET @id = SCOPE_IDENTITY();
	END
	ELSE
	BEGIN
		UPDATE dbo.currency SET
		title = @title,
		symbol = @symbol,
		active = @active
		WHERE id = @id;
	END;

END;

GO