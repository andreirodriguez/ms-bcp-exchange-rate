package pe.bcp.exchangerate.repository.interfaces;

import java.util.List;
import java.util.Map;

import pe.bcp.exchangerate.application.domain.Pagination;
import pe.bcp.exchangerate.application.domain.Currency;

public interface CurrencyRepository {
	Currency getSearch(Map<String,Object> parametersJson);

	List<Currency> getSearch(Map<String,Object> parametersJson,Pagination pagination);
}
