package pe.bcp.exchangerate.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import pe.bcp.exchangerate.application.domain.ExchangeRate;
import pe.bcp.exchangerate.dto.request.ExchangeRateRequest;
import pe.bcp.exchangerate.dto.response.ExchangeRateResponse;

public class ExchangeRateMapper {
	public static ExchangeRateResponse ToExchangeRateResponse(ExchangeRate i) {
		ExchangeRateResponse o = new ExchangeRateResponse();

		BeanUtils.copyProperties(i, o);

		return o;
	}
	
	public static List<ExchangeRateResponse> ToArrayExchangeRateResponse(List<ExchangeRate> l) {
		List<ExchangeRateResponse> o = new ArrayList<ExchangeRateResponse>();

		for(ExchangeRate i:l)
			o.add(ExchangeRateMapper.ToExchangeRateResponse(i));

		return o;
	}
	
	public static ExchangeRate ToExchangeRate(ExchangeRateRequest i) {
		ExchangeRate o = new ExchangeRate();

		BeanUtils.copyProperties(i, o);

		return o;
	}
}
