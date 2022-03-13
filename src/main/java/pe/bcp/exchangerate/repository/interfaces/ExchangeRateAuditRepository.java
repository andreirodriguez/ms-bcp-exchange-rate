package pe.bcp.exchangerate.repository.interfaces;

import java.util.List;
import java.util.Map;

import pe.bcp.exchangerate.application.domain.ExchangeRateAudit;
import pe.bcp.exchangerate.application.domain.Pagination;

public interface ExchangeRateAuditRepository {

	ExchangeRateAudit getSearch(Map<String,Object> parametersJson);

	List<ExchangeRateAudit> getSearch(Map<String,Object> parametersJson,Pagination pagination);

	List<ExchangeRateAudit> getFindAll(Map<String,Object> parametersJson,Pagination pagination);

	void setInsertUpdate(ExchangeRateAudit o);

}