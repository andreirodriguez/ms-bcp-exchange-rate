package pe.bcp.exchangerate.application.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.dto.response.CurrencyResponse;

public class CurrencyMapper {
	public static CurrencyResponse ToCurrencyResponse(Currency i) {
		CurrencyResponse o = new CurrencyResponse();

		BeanUtils.copyProperties(i, o);

		return o;
	}
	
	public static List<CurrencyResponse> ToArrayCurrencyResponse(List<Currency> l) {
		List<CurrencyResponse> o = new ArrayList<CurrencyResponse>();

		for(Currency i:l)
			o.add(CurrencyMapper.ToCurrencyResponse(i));

		return o;
	}	
}
