package pe.bcp.exchangerate.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateRequest {
	private int id;
	private Double amountOrigin;
	private Double rateExchange;
	private int currencyOriginId;
	private int currencyExchangeId;
	private int registerUserId;
	private String registerUserFullname;
}
