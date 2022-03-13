package pe.bcp.exchangerate.dto.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateQuery extends PaginationQuery{
	private int currencyOriginId;
	private int currencyExchangeId;
	private String registerDateUntil;
	private String registerDateTo;
}
