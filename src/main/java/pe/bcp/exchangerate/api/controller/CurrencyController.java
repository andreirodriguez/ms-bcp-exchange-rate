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
import pe.bcp.exchangerate.application.mapper.CurrencyMapper;
import pe.bcp.exchangerate.dto.query.CurrencyQuery;
import pe.bcp.exchangerate.dto.response.CurrencyResponse;
import pe.bcp.exchangerate.service.interfaces.CurrencyService;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {
	@Autowired
	private CurrencyService currencyService;

	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<ResponseEntity<CurrencyResponse>> getSelect(@PathVariable int id) {
        return currencyService.getSelect(id)
                .subscribeOn(Schedulers.io())
                .map(currency -> ResponseEntity.ok(CurrencyMapper.ToCurrencyResponse(currency)));
	}
	
	@GetMapping(value = "/search/active",produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<ResponseEntity<List<CurrencyResponse>>> getSelectByActive(CurrencyQuery q) {
        return currencyService.getSelectByActive(q.getPagination())
                .subscribeOn(Schedulers.io())
                .map(list -> ResponseEntity.ok(CurrencyMapper.ToArrayCurrencyResponse(list)));
	}	
	
	
}
