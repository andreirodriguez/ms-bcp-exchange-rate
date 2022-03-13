package pe.bcp.exchangerate.service.interfaces;

import java.util.List;

import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface CurrencyService {
	Currency getSelect(int id);

	List<Currency> getSelectByActive(Boolean active, Pagination pagination);
}
