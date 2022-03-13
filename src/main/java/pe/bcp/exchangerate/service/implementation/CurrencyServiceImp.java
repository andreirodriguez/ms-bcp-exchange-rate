package pe.bcp.exchangerate.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.Single;
import javax.persistence.EntityNotFoundException;

import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.application.domain.Pagination;
import pe.bcp.exchangerate.repository.interfaces.CurrencyRepository;
import pe.bcp.exchangerate.service.interfaces.CurrencyService;

@Service
public class CurrencyServiceImp implements CurrencyService {
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Override
	public Single<Currency> getSelect(int id) 
	{
        return Single.create(singleSubscriber -> {
            Currency o = this.getSearchById(id);
            
            if (o==null)
                singleSubscriber.onError(new EntityNotFoundException());
            else 
                singleSubscriber.onSuccess(o);
        });	
	}
	
	private Currency getSearchById(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id",id);

		return currencyRepository.getSearch(parameters);		
	}

	@Override
	public Single<List<Currency>> getSelectByActive(Pagination pagination) {
        return Single.create(singleSubscriber -> {
            List<Currency> l = this.getSearchByActive(pagination);
            singleSubscriber.onSuccess(l);
        });
	}

	private List<Currency> getSearchByActive(Pagination pagination) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("active",1);

		return currencyRepository.getSearch(parameters,pagination);		
	}
}
