package pe.bcp.exchangerate.service.implementation;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.reactivex.Single;
import pe.bcp.exchangerate.repository.interfaces.ExchangeRateAuditRepository;
import pe.bcp.exchangerate.service.interfaces.ExchangeRateAuditService;
import pe.bcp.exchangerate.application.domain.ExchangeRate;
import pe.bcp.exchangerate.application.domain.ExchangeRateAudit;
import pe.bcp.exchangerate.application.domain.Pagination;

@Service
public class ExchangeRateAuditServiceImp implements ExchangeRateAuditService {

	@Autowired
	private ExchangeRateAuditRepository exchangeRateAuditRepository;

	@Override
	public ExchangeRateAudit getSelect(int id) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("id",id);

		return exchangeRateAuditRepository.getSearch(parameters);
	}

	public List<ExchangeRateAudit> getSelect(int id, Pagination pagination) {
		Map<String, Object> parameters = new HashMap<String, Object>();

		if(id > 0) parameters.put("id",id);

		return exchangeRateAuditRepository.getSearch(parameters, pagination);
	}

	public Single<List<ExchangeRateAudit>> getListHistory(int exchangeRateId, Pagination pagination){
        return Single.create(singleSubscriber -> {
            List<ExchangeRateAudit> l = this.getFindAllHistory(exchangeRateId,pagination);
            
            singleSubscriber.onSuccess(l);
        });		
	}

	public List<ExchangeRateAudit> getFindAllHistory(int exchangeRateId, Pagination pagination){
		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("exchange_rate_id",exchangeRateId);

		return exchangeRateAuditRepository.getFindAll(parameters, pagination);
	}
	
	

	public void setRegister(ExchangeRateAudit o) {
		exchangeRateAuditRepository.setInsertUpdate(o);
	}

	public void setUpdate(int id,ExchangeRateAudit o) {
		o.setId(id);

		exchangeRateAuditRepository.setInsertUpdate(o);
	}

}