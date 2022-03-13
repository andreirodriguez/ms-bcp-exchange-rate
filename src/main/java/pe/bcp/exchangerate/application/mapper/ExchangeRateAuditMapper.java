package pe.bcp.exchangerate.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import pe.bcp.exchangerate.application.domain.ExchangeRateAudit;
import pe.bcp.exchangerate.dto.response.ExchangeRateAuditResponse;

public class ExchangeRateAuditMapper {
	public static ExchangeRateAuditResponse ToExchangeRateAuditResponse(ExchangeRateAudit i) {
		ExchangeRateAuditResponse o = new ExchangeRateAuditResponse();

		BeanUtils.copyProperties(i, o);

		return o;
	}
	
	public static List<ExchangeRateAuditResponse> ToArrayExchangeRateAuditResponse(List<ExchangeRateAudit> l) {
		List<ExchangeRateAuditResponse> o = new ArrayList<ExchangeRateAuditResponse>();

		for(ExchangeRateAudit i:l)
			o.add(ExchangeRateAuditMapper.ToExchangeRateAuditResponse(i));

		return o;
	}	
}
