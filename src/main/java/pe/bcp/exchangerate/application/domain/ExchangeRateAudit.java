package pe.bcp.exchangerate.application.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateAudit {
	private int id;
	private int exchangeRateId;
	private Double amountOrigin;
	private Double amountExchange;
	private Double rateExchange;
	private int currencyOriginId;
	private String currencyOriginTitle;
	private String currencyOriginSymbol;
	private int currencyExchangeId;
	private String currencyExchangeTitle;
	private String currencyExchangeSymbol;
	private int registerUserId;
	private String registerUserFullname;
	private Date registerDatetime;
	private Boolean active;
}
