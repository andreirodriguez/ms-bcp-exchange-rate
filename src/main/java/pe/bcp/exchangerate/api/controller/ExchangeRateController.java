package pe.bcp.exchangerate.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import pe.bcp.exchangerate.dto.response.PaginationResponse;
import pe.bcp.exchangerate.dto.query.ExchangeRateQuery;
import pe.bcp.exchangerate.dto.request.ExchangeRateRequest;
import pe.bcp.exchangerate.dto.response.ExchangeRateResponse;
import pe.bcp.exchangerate.application.mapper.ExchangeRateMapper;
import pe.bcp.exchangerate.service.interfaces.ExchangeRateService;

@RestController
@RequestMapping("/exchange-rates")
public class ExchangeRateController {

	@Autowired
	private ExchangeRateService exchangeRateService;

	@GetMapping("/{id}")
	public Single<ResponseEntity<ExchangeRateResponse>> getSelect(@PathVariable int id) {
        return exchangeRateService.getSelect(id)
                .subscribeOn(Schedulers.io())
                .map(exchangeRate -> ResponseEntity.ok(ExchangeRateMapper.ToExchangeRateResponse(exchangeRate)));
	}

	@GetMapping("/find-all/inbox")
	public Single<ResponseEntity<PaginationResponse<ExchangeRateResponse>>> getList(ExchangeRateQuery q) {
		
        return exchangeRateService.getListInbox(q.getCurrencyOriginId(), q.getCurrencyExchangeId(), q.getRegisterDateUntil(), q.getRegisterDateTo(), q.getPagination())
                .subscribeOn(Schedulers.io())
                .map(list -> ResponseEntity.ok(new PaginationResponse<ExchangeRateResponse>(q.getPagination(),ExchangeRateMapper.ToArrayExchangeRateResponse(list))));
	}
	
	@PostMapping() 
    public Single<ResponseEntity<Integer>> setRegister(@RequestBody ExchangeRateRequest exchangeRateRequest) {
        return exchangeRateService.setRegister(ExchangeRateMapper.ToExchangeRate(exchangeRateRequest)).
        		subscribeOn(Schedulers.io()).
        		map(result -> ResponseEntity.created(URI.create("/exchange-rates/exchange-rates/" + result)).body(result));
    }	
    
	@PutMapping("/{id}") 
    public Single<ResponseEntity<Integer>> setUpdate(@PathVariable int id,@RequestBody ExchangeRateRequest exchangeRateRequest) {
		
        return exchangeRateService.setUpdate(id,ExchangeRateMapper.ToExchangeRate(exchangeRateRequest)).
                subscribeOn(Schedulers.io()).
                map(result -> ResponseEntity.ok(result));		
    }	    


}