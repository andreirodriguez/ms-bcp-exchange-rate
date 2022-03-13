package pe.bcp.exchangerate.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import pe.bcp.exchangerate.application.domain.CurrencyExchange;
import pe.bcp.exchangerate.dto.response.CurrencyExchangeResponse;

public class CurrencyExchangeMapper {
	public static CurrencyExchangeResponse ToCurrencyExchangeResponse(CurrencyExchange i) {
		CurrencyExchangeResponse o = new CurrencyExchangeResponse();

		BeanUtils.copyProperties(i, o);

		return o;
	}
	
	public static List<CurrencyExchangeResponse> ToArrayCurrencyExchangeResponse(List<CurrencyExchange> l) {
		List<CurrencyExchangeResponse> o = new ArrayList<CurrencyExchangeResponse>();

		for(CurrencyExchange i:l)
			o.add(CurrencyExchangeMapper.ToCurrencyExchangeResponse(i));

		return o;
	}	
}
