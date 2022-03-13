package pe.bcp.exchangerate.repository.interfaces;

import java.util.List;
import java.util.Map;

import pe.bcp.exchangerate.application.domain.ExchangeRate;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface ExchangeRateRepository {

	ExchangeRate getSearch(Map<String,Object> parametersJson);

	List<ExchangeRate> getSearch(Map<String,Object> parametersJson,Pagination pagination);

	List<ExchangeRate> getFindAll(Map<String,Object> parametersJson,Pagination pagination);

	void setInsertUpdate(ExchangeRate o);

}