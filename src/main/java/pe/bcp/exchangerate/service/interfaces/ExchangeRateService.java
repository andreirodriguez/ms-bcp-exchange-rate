package pe.bcp.exchangerate.service.interfaces;

import java.util.List;

import io.reactivex.Single;
import pe.bcp.exchangerate.application.domain.ExchangeRate;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface ExchangeRateService {

	Single<ExchangeRate> getSelect(int id);

	List<ExchangeRate> getSelect(int id, Pagination pagination);

	Single<List<ExchangeRate>> getListInbox(int currencyOriginId,int currencyExchangeId,String registerDateUntil,String registerDateTo, Pagination pagination);

	void setRegister(ExchangeRate o);

	void setUpdate(int id,ExchangeRate o);

}