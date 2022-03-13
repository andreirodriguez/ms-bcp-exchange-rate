package pe.bcp.exchangerate.repository.interfaces;

import java.util.List;
import java.util.Map;

import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface CurrencyRepository {

	Currency getSearch(Map<String,Object> parametersJson);

	List<Currency> getSearch(Map<String,Object> parametersJson,Pagination pagination);

	List<Currency> getFindAll(Map<String,Object> parametersJson,Pagination pagination);

	void setInsertUpdate(Currency o);

}