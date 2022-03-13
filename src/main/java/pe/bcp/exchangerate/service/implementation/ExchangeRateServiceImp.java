package pe.bcp.exchangerate.service.implementation;

import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.Single;
import pe.bcp.exchangerate.repository.interfaces.ExchangeRateRepository;
import pe.bcp.exchangerate.service.interfaces.CurrencyExchangeService;
import pe.bcp.exchangerate.service.interfaces.ExchangeRateAuditService;
import pe.bcp.exchangerate.service.interfaces.ExchangeRateService;
import pe.bcp.exchangerate.application.domain.CurrencyExchange;
import pe.bcp.exchangerate.application.domain.ExchangeRate;
import pe.bcp.exchangerate.application.domain.Pagination;
import pe.bcp.exchangerate.cross.utils.ConvertFormat;

@Service
public class ExchangeRateServiceImp implements ExchangeRateService {

	@Autowired
	private ExchangeRateRepository exchangeRateRepository;
	
	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	private ExchangeRateAuditService exchangeRateAuditService;	

	@Override
	public Single<ExchangeRate> getSelect(int id) {
        return Single.create(singleSubscriber -> {
        	ExchangeRate o = this.getSearchById(id);
            
            if (o==null)
                singleSubscriber.onError(new EntityNotFoundException());
            else 
                singleSubscriber.onSuccess(o);
        });	
	}
	
	private ExchangeRate getSearchById(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id",id);

		return exchangeRateRepository.getSearch(parameters);		
	}	

	public Single<List<ExchangeRate>> getListInbox(int currencyOriginId,int currencyExchangeId,String registerDateUntil,String registerDateTo, Pagination pagination) {
        return Single.create(singleSubscriber -> {
            List<ExchangeRate> l = this.getFindAllInbox(currencyOriginId,currencyExchangeId,registerDateUntil,registerDateTo,pagination);
            
            singleSubscriber.onSuccess(l);
        });		
		
	}
	
	private List<ExchangeRate> getFindAllInbox(int currencyOriginId,int currencyExchangeId,String registerDateUntil,String registerDateTo, Pagination pagination) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		if(currencyOriginId > 0) parameters.put("currency_origin_id",currencyOriginId);
		if(currencyExchangeId > 0) parameters.put("currency_exchange_id",currencyExchangeId);
		if(!ConvertFormat.IsNullOrEmpty(registerDateUntil) && !ConvertFormat.IsNullOrEmpty(registerDateTo))
		{
			parameters.put("register_datetime_until",registerDateUntil);
			parameters.put("register_datetime_to",registerDateTo);
		}
		
		return exchangeRateRepository.getFindAll(parameters, pagination);
	}			
	
	public List<ExchangeRate> getSelect(int id, Pagination pagination) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		if(id > 0) parameters.put("id",id);

		return exchangeRateRepository.getSearch(parameters, pagination);
	}	

	public Single<Integer> setRegister(ExchangeRate o) {
        return Single.create(singleSubscriber -> {
            CurrencyExchange currency = this.setCalculateExchange(o);
            if (currency==null)
                singleSubscriber.onError(new EntityNotFoundException());
            else 
            {
        		o.setRegisterDatetime(new Date());
        		o.setActive(true);
        		
        		exchangeRateRepository.setInsertUpdate(o);
        		
        		exchangeRateAuditService.setRegister(o);
        		
        		singleSubscriber.onSuccess(o.getId());
            }
        });
	}

	public Single<Integer> setUpdate(int id,ExchangeRate o) {
        return Single.create(singleSubscriber -> {
        	ExchangeRate exchange = this.getSearchById(id);
        	
        	if(exchange==null)
        		singleSubscriber.onError(new EntityNotFoundException());
        	else
        	{
        		exchange.setAmountOrigin(o.getAmountOrigin());
        		exchange.setRateExchange(o.getRateExchange());
        		
                CurrencyExchange currency = this.setCalculateExchange(exchange);
                if (currency==null)
                    singleSubscriber.onError(new EntityNotFoundException());
                else 
                {        		
            		exchangeRateRepository.setInsertUpdate(exchange);
            		
            		exchangeRateAuditService.setRegister(exchange);
            		
            		singleSubscriber.onSuccess(exchange.getId());
                }        		
        	}
        });
	}

	private CurrencyExchange setCalculateExchange(ExchangeRate exchange)
	{
		CurrencyExchange currency = currencyExchangeService.getSelectByCurrencyOriginExchange(exchange.getCurrencyOriginId(), exchange.getCurrencyExchangeId());
		
		if(currency==null) return null;
		
		Double amountExchange = 0.0;
		
		if(currency.getMathematicalOperator().equals("D"))
			amountExchange = exchange.getAmountOrigin() / exchange.getRateExchange();
		else
			amountExchange = exchange.getAmountOrigin() * exchange.getRateExchange();
		
		exchange.setAmountExchange(amountExchange);
		
		return currency;
	}

}