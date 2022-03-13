package pe.bcp.exchangerate.repository.interfaces;

import java.util.List;
import java.util.Map;

import pe.bcp.exchangerate.application.domain.CurrencyExchange;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface CurrencyExchangeRepository {

	CurrencyExchange getSearch(Map<String,Object> parametersJson);

	List<CurrencyExchange> getSearch(Map<String,Object> parametersJson,Pagination pagination);

	List<CurrencyExchange> getFindAll(Map<String,Object> parametersJson,Pagination pagination);

	void setInsertUpdate(CurrencyExchange o);

}