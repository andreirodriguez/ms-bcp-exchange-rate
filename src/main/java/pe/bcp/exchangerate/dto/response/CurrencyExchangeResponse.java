package pe.bcp.exchangerate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchangeResponse {
	private int id;
	private int currencyOriginId;
	private String currencyOriginTitle;
	private String currencyOriginSymbol;
	private int currencyExchangeId;
	private String currencyExchangeTitle;
	private String currencyExchangeSymbol;
	private String mathematicalOperator;
	private Double rateExchange;
	private Boolean active;

}
