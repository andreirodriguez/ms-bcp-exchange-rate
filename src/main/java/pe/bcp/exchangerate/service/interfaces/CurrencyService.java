package pe.bcp.exchangerate.service.interfaces;

import java.util.List;

import io.reactivex.Single;
import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface CurrencyService {
	Single<Currency> getSelect(int id);

	Single<List<Currency>> getSelectByActive(Pagination pagination);
}
