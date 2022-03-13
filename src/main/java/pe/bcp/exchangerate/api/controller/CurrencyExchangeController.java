package pe.bcp.exchangerate.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import pe.bcp.exchangerate.application.mapper.CurrencyExchangeMapper;
import pe.bcp.exchangerate.dto.query.CurrencyExchangeQuery;
import pe.bcp.exchangerate.dto.response.CurrencyExchangeResponse;
import pe.bcp.exchangerate.service.interfaces.CurrencyExchangeService;

@RestController
@RequestMapping("/currencies-exchange")
public class CurrencyExchangeController {
	@Autowired
	private CurrencyExchangeService currencyExchangeService;

	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<ResponseEntity<CurrencyExchangeResponse>> getSelect(@PathVariable int id) {
        return currencyExchangeService.getSelect(id)
                .subscribeOn(Schedulers.io())
                .map(currency -> ResponseEntity.ok(CurrencyExchangeMapper.ToCurrencyExchangeResponse(currency)));
	}
	
	@GetMapping(value = "/search/currency-origin",produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<ResponseEntity<List<CurrencyExchangeResponse>>> getSelectByActive(CurrencyExchangeQuery q) {
        return currencyExchangeService.getSelectByCurrencyOrigin(q.getCurrencyOriginId(),q.getPagination())
                .subscribeOn(Schedulers.io())
                .map(list -> ResponseEntity.ok(CurrencyExchangeMapper.ToArrayCurrencyExchangeResponse(list)));
	}	
}
