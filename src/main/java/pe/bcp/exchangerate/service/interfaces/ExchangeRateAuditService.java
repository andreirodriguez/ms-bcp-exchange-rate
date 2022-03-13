package pe.bcp.exchangerate.service.interfaces;

import java.util.List;

import io.reactivex.Single;
import pe.bcp.exchangerate.application.domain.ExchangeRateAudit;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface ExchangeRateAuditService {

	ExchangeRateAudit getSelect(int id);

	List<ExchangeRateAudit> getSelect(int id, Pagination pagination);

	Single<List<ExchangeRateAudit>> getListHistory(int exchangeRateId, Pagination pagination);

	void setRegister(ExchangeRateAudit o);

	void setUpdate(int id,ExchangeRateAudit o);

}