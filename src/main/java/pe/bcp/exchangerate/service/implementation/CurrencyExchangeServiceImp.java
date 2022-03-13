package pe.bcp.exchangerate.service.implementation;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.Single;
import pe.bcp.exchangerate.repository.interfaces.CurrencyExchangeRepository;
import pe.bcp.exchangerate.service.interfaces.CurrencyExchangeService;
import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.application.domain.CurrencyExchange;
import pe.bcp.exchangerate.application.domain.Pagination;

@Service
public class CurrencyExchangeServiceImp implements CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	@Override
	public Single<CurrencyExchange> getSelect(int id) {
        return Single.create(singleSubscriber -> {
        	CurrencyExchange o = this.getSearchById(id);
            
            if (o==null)
                singleSubscriber.onError(new EntityNotFoundException());
            else 
                singleSubscriber.onSuccess(o);
        });	
	}
	
	@Override
	public CurrencyExchange getSearchById(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id",id);

		return currencyExchangeRepository.getSearch(parameters);
	}

	@Override
	public Single<List<CurrencyExchange>> getSelectByCurrencyOrigin(int currencyOriginId, Pagination pagination) {
        return Single.create(singleSubscriber -> {
            List<CurrencyExchange> l = this.getSearchByCurrencyOrigin(currencyOriginId,pagination);
            singleSubscriber.onSuccess(l);
        });
	}	
	
	private List<CurrencyExchange> getSearchByCurrencyOrigin(int currencyOriginId, Pagination pagination) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("currency_origin_id",currencyOriginId);
		parameters.put("active",1);

		return currencyExchangeRepository.getSearch(parameters, pagination);
	}

	@Override
	public CurrencyExchange getSelectByCurrencyOriginExchange(int currencyOriginId, int currencyExchangeId) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("currency_origin_id",currencyOriginId);
		parameters.put("currency_exchange_id",currencyExchangeId);
		parameters.put("active",1);

		return currencyExchangeRepository.getSearch(parameters);
	}




}