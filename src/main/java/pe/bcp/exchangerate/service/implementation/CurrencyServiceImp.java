package pe.bcp.exchangerate.service.implementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.bcp.exchangerate.application.domain.Currency;
import pe.bcp.exchangerate.application.domain.Pagination;
import pe.bcp.exchangerate.repository.interfaces.CurrencyRepository;
import pe.bcp.exchangerate.service.interfaces.CurrencyService;

@Service
public class CurrencyServiceImp implements CurrencyService {
	@Autowired
	private CurrencyRepository currencyRepository;
	
	@Override
	public Currency getSelect(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id",id);

		return currencyRepository.getSearch(parameters);
	}

	@Override
	public List<Currency> getSelectByActive(Boolean active, Pagination pagination) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("active",active);

		return currencyRepository.getSearch(parameters,pagination);
	}

}
