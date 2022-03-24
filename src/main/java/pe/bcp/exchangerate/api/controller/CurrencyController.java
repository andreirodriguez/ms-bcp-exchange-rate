package pe.bcp.exchangerate.api.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping(value = "/primes",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Integer>> getPrimes(@RequestBody List<Integer> numbers) {
		List<Integer> numbersPrimes = new ArrayList<Integer>();
		
		int count;
		for(Integer number:numbers)
		{
			count=0;
			for(int i=1;i<=number;i++)
				if(number%i==0)
					count++;
			
			if(count==2) numbersPrimes.add(number);
		}			
		
		Collections.sort(numbersPrimes);
		
		return new ResponseEntity<List<Integer>>(numbersPrimes,HttpStatus.OK);
				
	}		
	
}
