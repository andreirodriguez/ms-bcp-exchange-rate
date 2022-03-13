package pe.bcp.exchangerate.service.interfaces;

import java.util.List;

import io.reactivex.Single;
import pe.bcp.exchangerate.application.domain.CurrencyExchange;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface CurrencyExchangeService {

	Single<CurrencyExchange> getSelect(int id);
	
	CurrencyExchange getSearchById(int id);

	Single<List<CurrencyExchange>> getSelectByCurrencyOrigin(int currencyOriginId, Pagination pagination);
	
	CurrencyExchange getSelectByCurrencyOriginExchange(int currencyOriginId, int currencyExchangeId);

}