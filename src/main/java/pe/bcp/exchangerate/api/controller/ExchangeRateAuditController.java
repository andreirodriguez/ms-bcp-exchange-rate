package pe.bcp.exchangerate.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import pe.bcp.exchangerate.application.mapper.ExchangeRateAuditMapper;
import pe.bcp.exchangerate.dto.response.ExchangeRateAuditResponse;
import pe.bcp.exchangerate.service.interfaces.ExchangeRateAuditService;

@RestController
@RequestMapping("/exchange-rates-audit")
public class ExchangeRateAuditController {

	@Autowired
	private ExchangeRateAuditService exchangeRateAuditService;

	@GetMapping("/find-all/history")
	public Single<ResponseEntity<List<ExchangeRateAuditResponse>>> getList(pe.bcp.exchangerate.dto.query.ExchangeRateAuditQuery q) {
        return exchangeRateAuditService.getListHistory(q.getExchangeRateId(), q.getPagination())
                .subscribeOn(Schedulers.io())
                .map(list -> ResponseEntity.ok(ExchangeRateAuditMapper.ToArrayExchangeRateAuditResponse(list)));
	}

}